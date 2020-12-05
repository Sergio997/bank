package com.bank.service.impl;

import com.bank.dto.response.CardResponse;
import com.bank.exception.JwtAuthenticationException;
import com.bank.exception.NotBalanceException;
import com.bank.mapper.CardMapper;
import com.bank.model.Card;
import com.bank.repo.CardRepo;
import com.bank.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepo cardRepo;
    private final AuthServiceImpl authService;
    private final CardMapper cardMapper;

    @Override
    public CardResponse getBalance(String token) {
        return cardMapper.toDtoResponse(authService.getCurrentCard(token));
    }

    @Override
    public CardResponse addMoney(String token, Double money) {
        Card currentCard = authService.getCurrentCard(token);
        currentCard.setBalance(currentCard.getBalance() + money);
        return cardMapper.toDtoResponse(cardRepo.save(currentCard));
    }

    @Override
    public CardResponse withdrawFunds(String token, Double money) {
        Card currentCard = authService.getCurrentCard(token);
        Double currentBalance = subtractMoney(currentCard, money);
        currentCard.setBalance(currentBalance);
        return cardMapper.toDtoResponse(cardRepo.save(currentCard));
    }

    @Override
    @Transactional
    public CardResponse addBalanceAnotherCardThroughMyCard(String token, String cardNumber, Double money) {
        Card anotherCard = cardRepo.findByNumberCard(cardNumber)
                .orElseThrow(() -> new JwtAuthenticationException("JWT is expired or invalid", HttpStatus.UNAUTHORIZED));
        Card myCard = authService.getCurrentCard(token);
        Double myBalance = subtractMoney(myCard, money);
        myCard.setBalance(myBalance);
        Double anotherBalance = addMoney(anotherCard, money);
        anotherCard.setBalance(anotherBalance);
        return cardMapper.toDtoResponse(myCard);
    }

    private Double addMoney(Card currentCard, Double money) {
        return currentCard.getBalance() + money;
    }

    private Double subtractMoney(Card currentCard, Double money) {
        Double balance = currentCard.getBalance();
        if (balance >= money) {
            return balance - money;
        }
        throw new NotBalanceException("User not found exception. Please sign in first.");
    }
}
