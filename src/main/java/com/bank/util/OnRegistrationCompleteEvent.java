package com.bank.util;

import com.bank.model.Card;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@Getter
@Setter
public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private String appUrl;
    private Locale locale;
    private Card card;

    public OnRegistrationCompleteEvent(
            Card card, Locale locale, String appUrl) {
        super(card);
        this.card = card;
        this.locale = locale;
        this.appUrl = appUrl;
    }
}
