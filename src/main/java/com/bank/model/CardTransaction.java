package com.bank.model;

import com.bank.model.enums.Role;
import com.bank.model.enums.TypeTransaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    @Column(name = "money")
    private Double money;
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
        return Objects.equals(currentBalance, that.currentBalance)
                && Objects.equals(money, that.money)
                && typeTransaction == that.typeTransaction
                && Objects.equals(card, that.card);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), currentBalance, money, typeTransaction, card);
    }

    @Override
    public String toString() {
        return "CardTransaction{" +
                "currentBalance=" + currentBalance +
                ", money=" + money +
                ", typeTransaction=" + typeTransaction +
                ", card=" + card +
                '}';
    }
}
