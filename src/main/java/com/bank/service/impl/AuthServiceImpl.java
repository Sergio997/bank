package com.bank.service.impl;

import com.bank.dto.request.AuthenticationRequest;
import com.bank.dto.request.CardRequest;
import com.bank.dto.response.AuthenticationResponse;
import com.bank.dto.response.CardResponse;
import com.bank.exception.CardAlreadyExistException;
import com.bank.exception.JwtAuthenticationException;
import com.bank.mapper.CardMapper;
import com.bank.model.Card;
import com.bank.model.VerificationToken;
import com.bank.model.enums.Role;
import com.bank.repo.CardRepo;
import com.bank.repo.VerificationTokenRepo;
import com.bank.security.JwtTokenProvider;
import com.bank.service.AuthService;
import com.bank.util.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final CardRepo cardRepo;
    private final VerificationTokenRepo verificationTokenRepo;
    private final JwtTokenProvider tokenProvider;
    private final CardMapper cardMapper;
    private final JavaMailSender javaMailSender;
    private final PasswordEncoder bCryptPasswordEncoder;

    @Override
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest, HttpServletResponse response) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getNumberCard(), authenticationRequest.getPassword()));
        Card card = cardRepo.findByNumberCard(authenticationRequest.getNumberCard())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        String token = tokenProvider.createToken(authenticationRequest.getNumberCard(), card.getRole().name());
        response.addHeader("token", token);
        return cardMapper.toDtoAuthenticationResponse(card);
    }

    @Override
    public void logout(HttpServletResponse response, HttpServletRequest request) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }

    @Override
    public CardResponse registerNewUserAccount(CardRequest request) {
        Card card = cardRepo.findByNumberCard(request.getNumberCard())
                .orElse(null);
        if (card == null) {
            Card newCard = cardMapper.requestToEntity(request);
            newCard.setRole(Role.USER);
            newCard.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
            newCard.setBalance(0.0);
            String token = tokenProvider.createToken(newCard.getNumberCard(), newCard.getRole().name());
            Card savedCard = cardRepo.save(newCard);
            createVerificationToken(savedCard, token);
            sendEmail(newCard, token, Constant.Message.COMPLETE_SUBJECT, Constant.Message.CONFIRM_TEXT, Constant.Endpoint.CONFIRM_ENDPOINT);
            return cardMapper.toDtoResponse(savedCard);
        }
        throw new CardAlreadyExistException("There is an account with that email address: " + card.getNumberCard());
    }

    @Override
    public void createVerificationToken(Card card, String token) {
        VerificationToken verificationToken = new VerificationToken(token, card);
        verificationTokenRepo.save(verificationToken);
    }

    @Override
    public VerificationToken getVerificationToken(String token) {
        tokenProvider.validateToken(token);
        VerificationToken verificationToken  = verificationTokenRepo.findByToken(token)
                .orElseThrow(() -> new JwtAuthenticationException("JWT is expired or invalid", HttpStatus.UNAUTHORIZED));
        Card card = verificationToken.getCard();
        card.setActive(true);
        cardRepo.save(card);
        verificationTokenRepo.deleteById(verificationToken.getId());
        return verificationToken;
    }

    @Override
    public void forgotPassword(String numberCard) {
        Card card = cardRepo.findByNumberCard(numberCard)
                .orElseThrow(() -> new UsernameNotFoundException("Card not found"));
        String token = tokenProvider.createToken(card.getNumberCard(), card.getRole().name());
        createVerificationToken(card, token);
        sendEmail(card, token, Constant.Message.FORGOT_PASSWORD_SUBJECT, Constant.Message.FORGOT_PASSWORD_TEXT, Constant.Endpoint.FORGOT_PASSWORD_ENDPOINT);
    }

    @Override
    public void changePassword(String token, String password) {
        tokenProvider.validateToken(token);
        VerificationToken verificationToken  = verificationTokenRepo.findByToken(token)
                .orElseThrow(() -> new JwtAuthenticationException("JWT is expired or invalid", HttpStatus.UNAUTHORIZED));
        Card card = verificationToken.getCard();
        card.setPassword(bCryptPasswordEncoder.encode(password));
        verificationTokenRepo.deleteById(verificationToken.getId());
        cardMapper.toDtoResponse(cardRepo.save(card));
    }

    @Override
    public Card getCurrentCard(String token) {
        Object principal = tokenProvider.getAuthentication(token).getPrincipal();
        if (principal instanceof UserDetails) {
            return cardRepo.findByNumberCard(((UserDetails) principal).getUsername())
                    .orElseThrow(() -> new JwtAuthenticationException("JWT is expired or invalid", HttpStatus.UNAUTHORIZED));
        } else {
            throw new UsernameNotFoundException("User not found exception. Please sign in first.");
        }
    }

    private void sendEmail(Card card, String token, String subject, String text, String endpoint) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(card.getEmail());
        mailMessage.setSubject(subject);
        mailMessage.setText(text + endpoint + token);
        javaMailSender.send(mailMessage);
    }

}
