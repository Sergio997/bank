//package com.bank.producer;
//
//import com.bank.dto.request.AuthenticationRequest;
//import com.bank.dto.request.CardRequest;
//import com.bank.dto.response.AuthenticationResponse;
//import com.bank.dto.response.CardResponse;
//import com.bank.dto.response.CardTransactionResponse;
//import com.bank.model.Card;
//import com.bank.model.CardTransaction;
//import com.bank.model.enums.Role;
//import com.bank.model.enums.TypeTransaction;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//public class TestUtil {
//    private Card card;
//    private CardResponse cardResponse;
//    private CardRequest cardRequest;
//    private AuthenticationResponse authenticationResponse;
//
//    private final String PASSWORD = "password";
//    private final String NUMBER_CARD = "123456";
//    private final String EMAIL = "email";
//    private final String FIRST_NAME = "name";
//    private final String SECOND_NAME = "second name";
//    private final Double BALANCE = 400.0;
//    private final Double MONEY = 200.0;
//    private final Boolean ACTIVITY = true;
//
//    public CardTransaction createTransaction() {
//        return new CardTransaction() {{
//            setId(2L);
//            setCard(createCard());
//            setTypeTransaction(TypeTransaction.ADD_MONEY);
//            setMoney(MONEY);
//            setCreateDate(LocalDateTime.of(2022, 1, 8, 8,8));
//        }};
//    }
//
//    public CardTransactionResponse createCardTransactionResponse() {
//        return new CardTransactionResponse() {{
//            setCard(createCardResponse());
//            setCreateDate(LocalDateTime.of(2022, 1, 8, 8,8));
//            setTypeTransaction(TypeTransaction.ADD_MONEY);
//            setMoney(MONEY);
//        }};
//    }
//
//    public Card createCard() {
//        return new Card() {{
//            setId(1L);
//            setBalance(BALANCE);
//            setActive(true);
//            setPassword(PASSWORD);
//            setNumberCard(NUMBER_CARD);
//            setEmail(EMAIL);
//            setFirstName(FIRST_NAME);
//            setSecondName(SECOND_NAME);
//            setRole(Role.USER);
//            setTransactions(List.of(createTransaction()));
//            setCreateDate(LocalDateTime.of(2022, 1, 8, 8,8));
//            setUpdateDate(LocalDateTime.of(2022, 1, 8, 8,9));
//        }};
//    }
//
//    public CardResponse createCardResponse() {
//        return new CardResponse() {{
//            setBalance(400.0);
//            setNumberCard(NUMBER_CARD);
//        }};
//    }
//
//    public CardRequest createCardRequest() {
//        return new CardRequest() {{
//            setNumberCard(NUMBER_CARD);
//            setPassword(PASSWORD);
//            setEmail(EMAIL);
//            setFirstName(FIRST_NAME);
//            setSecondName(SECOND_NAME);
//        }};
//    }
//
//    public AuthenticationResponse createAuthenticationResponse() {
//        return new AuthenticationResponse() {{
//            setBalance(BALANCE);
//            setNumberCard(NUMBER_CARD);
//            setFirstName(FIRST_NAME);
//            setSecondName(SECOND_NAME);
//        }};
//    }
//
//    public AuthenticationRequest createAuthenticationRequest() {
//        return new AuthenticationRequest() {{
//           setNumberCard(NUMBER_CARD);
//           setPassword(PASSWORD);
//        }};
//    }
//
//}
