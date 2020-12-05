package com.bank.service;

import com.bank.dto.response.CardResponse;

public interface CardService {

    CardResponse getBalance(String token);

    CardResponse addMoney(String token, Double money);

    CardResponse withdrawFunds(String token, Double money);

    CardResponse addBalanceAnotherCardThroughMyCard(String token, String cardNumber, Double money);
}
