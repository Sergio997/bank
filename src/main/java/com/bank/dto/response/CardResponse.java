package com.bank.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardResponse {

    private String numberCard;
    private Double balance;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardResponse that = (CardResponse) o;
        return Objects.equals(numberCard, that.numberCard) && Objects.equals(balance, that.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberCard, balance);
    }

    @Override
    public String toString() {
        return "CardResponse{" +
                "numberCard='" + numberCard + '\'' +
                ", balance=" + balance +
                '}';
    }
}
