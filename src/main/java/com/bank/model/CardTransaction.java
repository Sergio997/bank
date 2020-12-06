package com.bank.model;

import com.bank.model.enums.TypeTransaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import java.util.Objects;

@Entity
@Table(name = "card_transaction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardTransaction extends AbstractEntity{

    @Column(name = "money")
    private Double money;
    @Column(name = "balance")
    private Double balance;
    @Column(name = "type_transaction")
    @Enumerated(value = EnumType.STRING)
    private TypeTransaction typeTransaction;
    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.DETACH)
    @JoinColumn(name = "card_id")
    private Card card;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CardTransaction that = (CardTransaction) o;
        return Objects.equals(money, that.money)
                && Objects.equals(balance, that.balance)
                && typeTransaction == that.typeTransaction
                && Objects.equals(card, that.card);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), money, balance, typeTransaction, card);
    }

    @Override
    public String toString() {
        return "CardTransaction{" +
                "money=" + money +
                ", balance=" + balance +
                ", typeTransaction=" + typeTransaction +
                ", card=" + card +
                '}';
    }
}
