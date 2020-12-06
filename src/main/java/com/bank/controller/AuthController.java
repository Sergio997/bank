package com.bank.controller;

import com.bank.dto.request.AuthenticationRequest;
import com.bank.dto.request.CardRequest;
import com.bank.dto.response.AuthenticationResponse;
import com.bank.dto.response.CardResponse;
import com.bank.model.Card;
import com.bank.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("bank/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthenticationResponse login(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) {
        return authService.login(authenticationRequest, response);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/logout", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void logout(HttpServletResponse response, HttpServletRequest request) {
        authService.logout(response, request);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/registration", method = RequestMethod.POST)
    public void registration(@RequestBody @Validated CardRequest request) {
        authService.registerNewUserAccount(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/confirm-account/{token}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void confirmToken(@PathVariable String token) {
        authService.getVerificationToken(token);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/forgot-password", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void forgotPassword(@RequestParam String card) {
        authService.forgotPassword(card);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/change-password/{token}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void changePassword(@PathVariable String token, @RequestParam String password) {
        authService.changePassword(token, password);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/current-card", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CardResponse getCurrentCard(HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        Card currentCard = authService.getCurrentCard(token);
        return new CardResponse((currentCard.getNumberCard()), currentCard.getBalance());
    }
}
