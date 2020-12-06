package com.bank.mapper;

import com.bank.dto.request.CardRequest;
import com.bank.dto.response.AuthenticationResponse;
import com.bank.dto.response.CardResponse;
import com.bank.model.Card;
import com.bank.model.enums.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

public class CardMapperTest {

    private final CardMapper testObj = Mappers.getMapper(CardMapper.class);

    private Card card;
    private CardResponse cardResponse;
    private CardRequest cardRequest;
    private AuthenticationResponse authenticationResponse;

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

        cardRequest = new CardRequest(){{
            setNumberCard("123456");
            setPassword("password");
            setEmail("email");
            setFirstName("name");
            setSecondName("secondName");
        }};

        authenticationResponse = new AuthenticationResponse() {{
            setBalance(200.0);
            setNumberCard("123456");
            setFirstName("name");
            setSecondName("secondName");
        }};
    }

    @Test
    public void requestToEntity() {
        Card currentCard = testObj.requestToEntity(cardRequest);

        Assertions.assertNotNull(currentCard);
        Assertions.assertEquals(currentCard.getNumberCard(), card.getNumberCard());
        Assertions.assertEquals(currentCard.getEmail(), card.getEmail());
        Assertions.assertEquals(currentCard.getPassword(), card.getPassword());
        Assertions.assertEquals(currentCard.getFirstName(), card.getFirstName());
        Assertions.assertEquals(currentCard.getSecondName(), card.getSecondName());
    }

    @Test
    public void requestToEntityWith() {
        Card currentCard = testObj.requestToEntity(cardRequest, card);

        Assertions.assertNotNull(currentCard);
        Assertions.assertEquals(currentCard.getNumberCard(), card.getNumberCard());
        Assertions.assertEquals(currentCard.getEmail(), card.getEmail());
        Assertions.assertEquals(currentCard.getPassword(), card.getPassword());
        Assertions.assertEquals(currentCard.getFirstName(), card.getFirstName());
        Assertions.assertEquals(currentCard.getSecondName(), card.getSecondName());
    }

    @Test
    public void toDtoResponse() {
        CardResponse currentCardResponse = testObj.toDtoResponse(card);

        Assertions.assertNotNull(currentCardResponse);
        Assertions.assertEquals(currentCardResponse.getNumberCard(), cardResponse.getNumberCard());
        Assertions.assertEquals(currentCardResponse.getBalance(), cardResponse.getBalance());
    }

    @Test
    public void toDtoAuthenticationResponse() {
        AuthenticationResponse currentAuthenticationResponse = testObj.toDtoAuthenticationResponse(card);

        Assertions.assertNotNull(currentAuthenticationResponse);
        Assertions.assertEquals(currentAuthenticationResponse.getNumberCard(), authenticationResponse.getNumberCard());
        Assertions.assertEquals(currentAuthenticationResponse.getBalance(), authenticationResponse.getBalance());
        Assertions.assertEquals(currentAuthenticationResponse.getFirstName(), authenticationResponse.getFirstName());
        Assertions.assertEquals(currentAuthenticationResponse.getSecondName(), authenticationResponse.getSecondName());
    }
}
