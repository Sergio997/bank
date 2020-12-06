package com.bank.service.impl;

import com.bank.dto.response.CardResponse;
import com.bank.dto.response.CardTransactionResponse;
import com.bank.dto.response.PageResponse;
import com.bank.exception.NotBalanceException;
import com.bank.mapper.CardMapper;
import com.bank.mapper.CardTransactionMapper;
import com.bank.model.Card;
import com.bank.model.CardTransaction;
import com.bank.model.enums.TypeTransaction;
import com.bank.repo.CardRepo;
import com.bank.repo.CardTransactionRepo;
import com.bank.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepo cardRepo;
    private final CardTransactionRepo cardTransactionRepo;
    private final AuthServiceImpl authService;
    private final CardMapper cardMapper;
    private final CardTransactionMapper cardTransactionMapper;

    @Override
    public CardResponse getBalance(String token) {
        return cardMapper.toDtoResponse(authService.getCurrentCard(token));
    }

    @Override
    public CardResponse addMoney(String token, Double money) {
        Card currentCard = authService.getCurrentCard(token);
        currentCard.setBalance(currentCard.getBalance() + money);
        addTransaction(currentCard, money, TypeTransaction.ADD_MONEY);
        return cardMapper.toDtoResponse(cardRepo.save(currentCard));
    }

    @Override
    public CardResponse withdrawFunds(String token, Double money) {
        Card currentCard = authService.getCurrentCard(token);
        Double currentBalance = subtractMoney(currentCard, money);
        currentCard.setBalance(currentBalance);
        addTransaction(currentCard, money, TypeTransaction.WITHDRAW_MONEY);
        return cardMapper.toDtoResponse(cardRepo.save(currentCard));
    }

    @Override
    @Transactional
    public CardResponse addBalanceAnotherCardThroughMyCard(String token, String cardNumber, Double money) {
        Card anotherCard = cardRepo.findByNumberCard(cardNumber)
                .orElseThrow(() -> new UsernameNotFoundException("Card not found"));
        Card myCard = authService.getCurrentCard(token);
        Double myBalance = subtractMoney(myCard, money);
        myCard.setBalance(myBalance);
        Double anotherBalance = addMoney(anotherCard, money);
        anotherCard.setBalance(anotherBalance);
        addTransaction(myCard, money, TypeTransaction.GET_MONEY);
        addTransaction(anotherCard, money, TypeTransaction.ADD_MONEY);
        return cardMapper.toDtoResponse(myCard);
    }

    @Override
    public PageResponse<CardTransactionResponse> getCartTransaction(String token, Pageable pageable) {
        Card currentCard = authService.getCurrentCard(token);
        Page<CardTransaction> pageCardTransaction = cardTransactionRepo.findAllByCard_Id(currentCard.getId(), pageable);
        return cardTransactionMapper.toDtoPageResponse(pageCardTransaction);
    }

    private void addTransaction(Card card, Double money, TypeTransaction typeTransaction) {
        CardTransaction cardTransaction = new CardTransaction();
        cardTransaction.setCard(card);
        cardTransaction.setMoney(money);
        cardTransaction.setBalance(card.getBalance());
        cardTransaction.setTypeTransaction(typeTransaction);
        cardTransactionRepo.save(cardTransaction);
    }

    private Double addMoney(Card currentCard, Double money) {
        return currentCard.getBalance() + money;
    }

    private Double subtractMoney(Card currentCard, Double money) {
        Double balance = currentCard.getBalance();
        if (balance >= money) {
            return balance - money;
        }
        throw new NotBalanceException("User dont have money.");
    }
}
