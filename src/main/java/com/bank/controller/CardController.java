package com.bank.controller;

import com.bank.dto.response.CardResponse;
import com.bank.dto.response.CardTransactionResponse;
import com.bank.dto.response.PageResponse;
import com.bank.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("card")
@AllArgsConstructor
public class CardController {

    private final CardService cardService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/balance", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CardResponse confirmToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        return cardService.getBalance(token);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/add/money", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public CardResponse addMoney(HttpServletRequest request, @RequestParam Double money) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        return cardService.addMoney(token, money);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/withdraw/money", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public CardResponse withdrawMoney(HttpServletRequest request, @RequestParam Double money) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        return cardService.withdrawFunds(token, money);
    }

    // TODO: 05.12.20 валідація, Junit, constantClass
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/withdraw/another/card/money", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public CardResponse addBalanceAnotherCardThroughMyCard(HttpServletRequest request, @RequestParam String card, @RequestParam Double money) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        return cardService.addBalanceAnotherCardThroughMyCard(token, card, money);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/transaction/history", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PageResponse<CardTransactionResponse> getAllTransaction(HttpServletRequest request, @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC, page = 0,  size = 5) Pageable pageable) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        return cardService.getCartTransaction(token, pageable);
    }
}
