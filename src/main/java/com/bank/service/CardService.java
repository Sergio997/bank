package com.bank.service;

import com.bank.dto.response.CardResponse;
import com.bank.dto.response.CardTransactionResponse;
import com.bank.dto.response.PageResponse;
import org.springframework.data.domain.Pageable;

public interface CardService {

    CardResponse getBalance(String token);

    CardResponse addMoney(String token, Double money);

    CardResponse withdrawFunds(String token, Double money);

    CardResponse addBalanceAnotherCardThroughMyCard(String token, String cardNumber, Double money);

    PageResponse<CardTransactionResponse> getCartTransaction(String token, Pageable pageable);
}
