package com.bank.service;

import com.bank.dto.request.AuthenticationRequest;
import com.bank.dto.request.CardRequest;
import com.bank.dto.response.AuthenticationResponse;
import com.bank.dto.response.CardResponse;
import com.bank.model.Card;
import com.bank.model.VerificationToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthService {

    AuthenticationResponse login(AuthenticationRequest authenticationRequest, HttpServletResponse response);

    void logout(HttpServletResponse response, HttpServletRequest request);

    CardResponse registerNewUserAccount(CardRequest request);

    void createVerificationToken(Card card, String token);

    VerificationToken getVerificationToken(String VerificationToken);

    void forgotPassword(String email);

    void changePassword(String token, String password);

    Card getCurrentCard(String token);

}
