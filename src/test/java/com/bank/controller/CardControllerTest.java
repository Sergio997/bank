package com.bank.controller;

import com.bank.dto.request.CardRequest;
import com.bank.dto.response.AuthenticationResponse;
import com.bank.dto.response.CardResponse;
import com.bank.model.Card;
import com.bank.model.enums.Role;
import com.bank.service.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = CardController.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("default")
public class CardControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CardService cardService;

    private Card card;
    private CardResponse cardResponse;
    private CardRequest cardRequest;
    private AuthenticationResponse authenticationResponse;

    private final String TOKEN = "token";
    private final String PASSWORD = "password";
    private final String NUMBER_CARD = "123456";
    private final String EMAIL = "email";
    private final String FIRST_NAME = "name";
    private final String SECOND_NAME = "second name";
    private final Double BALANCE = 400.0;
    private final Double MONEY = 200.0;
    private final Boolean ACTIVITY = true;

    @BeforeEach
    public void setUp() {
        card = new Card() {{
            setId(1L);
            setBalance(BALANCE);
            setActive(true);
            setPassword(PASSWORD);
            setNumberCard(NUMBER_CARD);
            setEmail(EMAIL);
            setFirstName(FIRST_NAME);
            setSecondName(SECOND_NAME);
            setRole(Role.USER);
        }};

        cardResponse = new CardResponse() {{
            setBalance(BALANCE);
            setNumberCard(NUMBER_CARD);
        }};

        cardRequest = new CardRequest() {{
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
    @WithMockUser(authorities = "USER")
    public void showBalance() throws Exception {
        Mockito.when(cardService.getBalance(TOKEN))
                .thenReturn(cardResponse);

        mockMvc.perform(
                get("/card/balance")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(".balance").value(cardResponse.getBalance()))
                .andExpect(jsonPath(".cardNumber").value(cardResponse.getNumberCard()));
    }
}
