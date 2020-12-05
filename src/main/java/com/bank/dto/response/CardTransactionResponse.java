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
    private LocalDateTime updateDate;
    private Double currentBalance;
    private Double money;
    private TypeTransaction typeTransaction;
    private CardResponse card;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardTransactionResponse that = (CardTransactionResponse) o;
        return Objects.equals(createDate, that.createDate) && Objects.equals(updateDate, that.updateDate) && Objects.equals(currentBalance, that.currentBalance) && Objects.equals(money, that.money) && typeTransaction == that.typeTransaction && Objects.equals(card, that.card);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createDate, updateDate, currentBalance, money, typeTransaction, card);
    }

    @Override
    public String toString() {
        return "CardTransactionResponse{" +
                "createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", currentBalance=" + currentBalance +
                ", money=" + money +
                ", typeTransaction=" + typeTransaction +
                ", card=" + card +
                '}';
    }
}
