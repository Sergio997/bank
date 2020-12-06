package com.bank.mapper;

import com.bank.dto.response.CardResponse;
import com.bank.dto.response.CardTransactionResponse;
import com.bank.model.Card;
import com.bank.model.CardTransaction;
import com.bank.model.enums.Role;
import com.bank.model.enums.TypeTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

public class CardTransactionMapperTest {

    private final CardTransactionMapper testObj = Mappers.getMapper(CardTransactionMapper.class);

    private CardTransaction cardTransaction;
    private CardTransactionResponse cardTransactionResponse;
    private Card card;
    private CardResponse cardResponse;

    @BeforeEach
    public void setUp() {

        card = new Card() {{
            setId(1L);
            setBalance(200.0);
            setActive(true);
            setPassword("password");
            setNumberCard("123456");
            setEmail("email");
            setFirstName("name");
            setSecondName("secondName");
            setRole(Role.USER);
        }};

        cardResponse = new CardResponse() {{
            setBalance(200.0);
            setNumberCard("123456");
            setBalance(200.0);
        }};

        cardTransaction = new CardTransaction() {{
            setTypeTransaction(TypeTransaction.ADD_MONEY);
            setMoney(200.0);
            setCard(card);
        }};

        cardTransactionResponse = new CardTransactionResponse() {{
            setTypeTransaction(TypeTransaction.ADD_MONEY);
            setMoney(200.0);
            setCard(cardResponse);
        }};
    }

    @Test
    public void toDtoResponse() {
        CardTransactionResponse currentCardTransactionResponse = testObj.toDtoResponse(cardTransaction);

        Assertions.assertNotNull(currentCardTransactionResponse);
        Assertions.assertEquals(currentCardTransactionResponse.getTypeTransaction(), cardTransactionResponse.getTypeTransaction());
        Assertions.assertEquals(currentCardTransactionResponse.getMoney(), cardTransactionResponse.getMoney());
    }

}
