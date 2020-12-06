package com.bank.dto.response;

import com.bank.model.enums.TypeTransaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardTransactionResponse {

    private LocalDateTime createDate;
    private Double money;
    private TypeTransaction typeTransaction;
    private CardResponse card;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardTransactionResponse that = (CardTransactionResponse) o;
        return Objects.equals(createDate, that.createDate)
                && Objects.equals(money, that.money)
                && typeTransaction == that.typeTransaction
                && Objects.equals(card, that.card);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createDate, money, typeTransaction, card);
    }

    @Override
    public String toString() {
        return "CardTransactionResponse{" +
                "createDate=" + createDate +
                ", money=" + money +
                ", typeTransaction=" + typeTransaction +
                ", card=" + card +
                '}';
    }
}
