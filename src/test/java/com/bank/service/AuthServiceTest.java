package com.bank.service;

import com.bank.dto.request.AuthenticationRequest;
import com.bank.dto.request.CardRequest;
import com.bank.dto.response.AuthenticationResponse;
import com.bank.dto.response.CardResponse;
import com.bank.exception.CardAlreadyExistException;
import com.bank.mapper.CardMapper;
import com.bank.model.Card;
import com.bank.model.VerificationToken;
import com.bank.model.enums.Role;
import com.bank.repo.CardRepo;
import com.bank.repo.VerificationTokenRepo;
import com.bank.security.JwtTokenProvider;
import com.bank.service.impl.AuthServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.servlet.http.HttpServletResponse;


@ExtendWith(SpringExtension.class)
public class AuthServiceTest {

    @InjectMocks
    private AuthServiceImpl testObj;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private CardRepo cardRepo;
    @Mock
    private VerificationTokenRepo verificationTokenRepo;
    @Mock
    private JwtTokenProvider tokenProvider;
    @Mock
    private CardMapper cardMapper;

    private Card card;
    private CardResponse cardResponse;
    private CardRequest cardRequest;
    private Authentication authentication;
    private AuthenticationResponse authenticationResponse;
    private AuthenticationRequest authenticationRequest;
    private HttpServletResponse response;
    private VerificationToken verificationToken;

    private final String NUMBER_CARD = "123456";
    private final String BAD_NUMBER_CARD = "12356";
    private final String TOKEN = "token";
    private final String PASSWORD = "password";
    private final Long TOKEN_ID = 3L;

    @BeforeEach
    public void setUp() {

        response = Mockito.mock(HttpServletResponse.class);
        authentication = Mockito.mock(Authentication.class);

        card = new Card() {{
            setId(1L);
            setBalance(400.0);
            setActive(true);
            setPassword(PASSWORD);
            setNumberCard(NUMBER_CARD);
            setEmail("email");
            setFirstName("name");
            setSecondName("secondName");
            setRole(Role.USER);
        }};

        cardResponse = new CardResponse() {{
            setBalance(400.0);
            setNumberCard(NUMBER_CARD);
        }};

        cardRequest = new CardRequest() {{
            setNumberCard(NUMBER_CARD);
            setPassword(PASSWORD);
            setEmail("email");
            setFirstName("name");
            setSecondName("secondName");
        }};

        authenticationResponse = new AuthenticationResponse() {{
            setBalance(400.0);
            setNumberCard(NUMBER_CARD);
            setFirstName("name");
            setSecondName("secondName");
        }};

        authenticationRequest = new AuthenticationRequest() {{
            setNumberCard(NUMBER_CARD);
            setPassword(PASSWORD);
        }};

        verificationToken = new VerificationToken() {{
            setId(TOKEN_ID);
            setCard(card);
            setToken(TOKEN);
        }};
    }

    @Test
    public void login() {
        Mockito.when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getNumberCard(), authenticationRequest.getPassword())))
                .thenReturn(authentication);
        Mockito.when(cardRepo.findByNumberCard(NUMBER_CARD))
                .thenReturn(java.util.Optional.ofNullable(card));
        Mockito.when(tokenProvider.createToken(NUMBER_CARD, card.getRole().name()))
                .thenReturn(TOKEN);
        Mockito.when(cardMapper.toDtoAuthenticationResponse(card))
                .thenReturn(authenticationResponse);

        AuthenticationResponse currentAuthResponse = testObj.login(authenticationRequest, response);

        Mockito.verify(authenticationManager)
                .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getNumberCard(), authenticationRequest.getPassword()));
        Mockito.verify(cardRepo)
                .findByNumberCard(NUMBER_CARD);
        Mockito.verify(tokenProvider)
                .createToken(NUMBER_CARD, card.getRole().name());
        Mockito.verify(cardMapper)
                .toDtoAuthenticationResponse(card);

        Assertions.assertNotNull(currentAuthResponse);
    }

    @Test
    public void registerNewUserAccountWithException() {
        Mockito.when(cardRepo.findByNumberCard(NUMBER_CARD))
                .thenReturn(java.util.Optional.ofNullable(card));
        Mockito.when(cardMapper.requestToEntity(cardRequest))
                .thenReturn(card);
        Mockito.when(tokenProvider.createToken(NUMBER_CARD,card.getRole().name()))
                .thenReturn(Mockito.anyString());
        Mockito.when(cardRepo.save(card))
                .thenReturn(card);

        Assertions.assertThrows(CardAlreadyExistException.class, () ->
                testObj.registerNewUserAccount(cardRequest));
    }

    @Test
    public void createVerificationToken() {
        Mockito.when(verificationTokenRepo.save(verificationToken))
                .thenReturn(verificationToken);

        testObj.createVerificationToken(card, TOKEN);

    }

    @Test
    public void forgotPassword() {
        Mockito.when(cardRepo.findByNumberCard(NUMBER_CARD))
                .thenReturn(java.util.Optional.ofNullable(card));
        Mockito.when(tokenProvider.createToken(NUMBER_CARD, card.getRole().name()))
                .thenReturn(TOKEN);

        testObj.forgotPassword(NUMBER_CARD);

        Mockito.verify(cardRepo)
                .findByNumberCard(NUMBER_CARD);
        Mockito.verify(tokenProvider)
                .createToken(NUMBER_CARD, card.getRole().name());
    }

    @Test
    public void forgotPasswordWthException() {
        Mockito.when(cardRepo.findByNumberCard(NUMBER_CARD))
                .thenReturn(java.util.Optional.ofNullable(card));
        Mockito.when(tokenProvider.createToken(NUMBER_CARD, card.getRole().name()))
                .thenReturn(TOKEN);

        Assertions.assertThrows(UsernameNotFoundException.class, () ->
                testObj.forgotPassword(BAD_NUMBER_CARD));
    }

    @Test
    public void changePassword() {
        Mockito.when(verificationTokenRepo.findByToken(TOKEN))
                .thenReturn(java.util.Optional.ofNullable(verificationToken));
        Mockito.doNothing()
                .when(verificationTokenRepo)
                .deleteById(TOKEN_ID);
        Mockito.when(cardRepo.save(card))
                .thenReturn(card);
        Mockito.when(cardMapper.toDtoResponse(card))
                .thenReturn(cardResponse);

        testObj.changePassword(TOKEN, PASSWORD);

        Mockito.verify(verificationTokenRepo)
                .findByToken(TOKEN);
        Mockito.verify(verificationTokenRepo)
                .deleteById(TOKEN_ID);
        Mockito.verify(cardRepo)
                .save(card);
        Mockito.verify(cardMapper)
                .toDtoResponse(card);

    }

}
