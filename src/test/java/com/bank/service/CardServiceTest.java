package com.bank.service;

import com.bank.dto.request.CardRequest;
import com.bank.dto.response.AuthenticationResponse;
import com.bank.dto.response.CardResponse;
import com.bank.exception.NotBalanceException;
import com.bank.mapper.CardMapper;
import com.bank.mapper.CardTransactionMapper;
import com.bank.model.Card;
import com.bank.model.enums.Role;
import com.bank.repo.CardRepo;
import com.bank.repo.CardTransactionRepo;
import com.bank.service.impl.AuthServiceImpl;
import com.bank.service.impl.CardServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class CardServiceTest {

    @InjectMocks
    private CardServiceImpl testObj;

    @Mock
    private CardRepo cardRepo;
    @Mock
    private AuthServiceImpl authService;
    @Mock
    private CardMapper cardMapper;

    private Card card;
    private CardResponse cardResponse;
    private CardRequest cardRequest;
    private AuthenticationResponse authenticationResponse;

    private final String TOKEN = "token";
    private final String CARD_NUMBER = "number";
    private final String BAD_CARD_NUMBER = "number1";
    private final Double MONEY = 200.0;
    private final Double LOT_MONEY = 800.0;

    @BeforeEach
    public void setUp() {
        card = new Card() {{
            setId(1L);
            setBalance(400.0);
            setActive(true);
            setPassword("password");
            setNumberCard("123456");
            setEmail("email");
            setFirstName("name");
            setSecondName("secondName");
            setRole(Role.USER);
        }};

        cardResponse = new CardResponse() {{
            setBalance(400.0);
            setNumberCard("123456");
        }};

        cardRequest = new CardRequest(){{
            setNumberCard("123456");
            setPassword("password");
            setEmail("email");
            setFirstName("name");
            setSecondName("secondName");
        }};

        authenticationResponse = new AuthenticationResponse() {{
            setBalance(400.0);
            setNumberCard("123456");
            setFirstName("name");
            setSecondName("secondName");
        }};

    }

    @Test
    public void getBalance() {
        Mockito.when(authService.getCurrentCard(TOKEN))
                .thenReturn(card);

        CardResponse balance = testObj.getBalance(TOKEN);

        Mockito.verify(authService)
                .getCurrentCard(TOKEN);

        Assertions.assertNotNull(balance);
    }

    @Test
    public void addMoney() {
        Mockito.when(authService.getCurrentCard(TOKEN))
                .thenReturn(card);
        Mockito.when(cardRepo.save(card))
                .thenReturn(card);
        Mockito.when(cardMapper.toDtoResponse(card))
                .thenReturn(cardResponse);

        CardResponse cardResponse = testObj.addMoney(TOKEN, MONEY);

        Mockito.verify(authService)
                .getCurrentCard(TOKEN);
        Mockito.verify(cardRepo)
                .save(card);
        Mockito.verify(cardMapper)
                .toDtoResponse(card);

        Assertions.assertNotNull(cardResponse);
    }

    @Test
    public void withdrawFunds() {
        Mockito.when(authService.getCurrentCard(TOKEN))
                .thenReturn(card);
        Mockito.when(cardRepo.save(card))
                .thenReturn(card);
        Mockito.when(cardMapper.toDtoResponse(card))
                .thenReturn(cardResponse);

        CardResponse currentCardResponse = testObj.withdrawFunds(TOKEN, MONEY);

        Mockito.verify(authService)
                .getCurrentCard(TOKEN);
        Mockito.verify(cardRepo)
                .save(card);
        Mockito.verify(cardMapper)
                .toDtoResponse(card);

        Assertions.assertNotNull(currentCardResponse);
    }

    @Test
    public void withdrawFundsWithNotBalanceException() {
        Mockito.when(authService.getCurrentCard(TOKEN))
                .thenReturn(card);
        Mockito.when(cardRepo.save(card))
                .thenReturn(card);
        Mockito.when(cardMapper.toDtoResponse(card))
                .thenReturn(cardResponse);

        Assertions.assertThrows(NotBalanceException.class, () ->
                testObj.withdrawFunds(TOKEN, LOT_MONEY));
    }

    @Test
    public void addBalanceAnotherCardThroughMyCard() {
        Mockito.when(cardRepo.findByNumberCard(CARD_NUMBER))
                .thenReturn(java.util.Optional.ofNullable(card));
        Mockito.when(authService.getCurrentCard(TOKEN))
                .thenReturn(card);
        Mockito.when(cardMapper.toDtoResponse(card))
                .thenReturn(cardResponse);

        CardResponse currentCardResponse = testObj.addBalanceAnotherCardThroughMyCard(TOKEN, CARD_NUMBER, MONEY);

        Mockito.verify(cardRepo)
                .findByNumberCard(CARD_NUMBER);
        Mockito.verify(authService)
                .getCurrentCard(TOKEN);
        Mockito.verify(cardMapper)
                .toDtoResponse(card);

        Assertions.assertNotNull(currentCardResponse);
    }

    @Test
    public void addBalanceAnotherCardThroughMyCardWithException() {
        Mockito.when(cardRepo.findByNumberCard(CARD_NUMBER))
                .thenReturn(java.util.Optional.ofNullable(card));
        Mockito.when(authService.getCurrentCard(TOKEN))
                .thenReturn(card);
        Mockito.when(cardMapper.toDtoResponse(card))
                .thenReturn(cardResponse);

        Assertions.assertThrows(NotBalanceException.class, () ->
                testObj.addBalanceAnotherCardThroughMyCard(TOKEN, CARD_NUMBER, LOT_MONEY));
    }

    @Test
    public void addBalanceAnotherCardThroughMyCardWithNotBalanceException() {
        Mockito.when(cardRepo.findByNumberCard(CARD_NUMBER))
                .thenReturn(java.util.Optional.ofNullable(card));
        Mockito.when(authService.getCurrentCard(TOKEN))
                .thenReturn(card);
        Mockito.when(cardMapper.toDtoResponse(card))
                .thenReturn(cardResponse);

        Assertions.assertThrows(UsernameNotFoundException.class, () ->
                testObj.addBalanceAnotherCardThroughMyCard(TOKEN, BAD_CARD_NUMBER, MONEY));
    }

}
