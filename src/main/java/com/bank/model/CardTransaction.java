package com.bank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "card_transaction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardTransaction extends AbstractEntity{

    @Column(name = "current_balance")
    private Double currentBalance;
    @Column(name = "withdrawn_cash")
    private Double withdrawCash;
    @ManyToOne
    private Card card;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CardTransaction that = (CardTransaction) o;
        return Objects.equals(currentBalance, that.currentBalance)
                && Objects.equals(withdrawCash, that.withdrawCash)
                && Objects.equals(card, that.card);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), currentBalance, withdrawCash, card);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "currentBalance=" + currentBalance +
                ", withdrawCash=" + withdrawCash +
                ", card=" + card +
                '}';
    }
}
