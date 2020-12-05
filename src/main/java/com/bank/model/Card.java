package com.bank.model;

import com.bank.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "card")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Card extends AbstractEntity implements Serializable {

    @Column(name = "number_card")
    private String numberCard;
    @Column(name = "email")
    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "second_name")
    private String secondName;
    @Column(name = "password")
    private String password;
    @Column(name = "balance")
    private Double balance;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private List<CardTransaction> transactions = new ArrayList<>();

    public String getNumberCard() {
        return numberCard;
    }

    public void setNumberCard(String numberCard) {
        this.numberCard = numberCard;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<CardTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<CardTransaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Card card = (Card) o;
        return Objects.equals(numberCard, card.numberCard)
                && Objects.equals(email, card.email)
                && Objects.equals(firstName, card.firstName)
                && Objects.equals(secondName, card.secondName)
                && Objects.equals(password, card.password)
                && Objects.equals(balance, card.balance)
                && Objects.equals(active, card.active)
                && role == card.role
                && Objects.equals(transactions, card.transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numberCard, email, firstName, secondName, password, balance,
                active, role, transactions);
    }

    @Override
    public String toString() {
        return "Card{" +
                "numberCard='" + numberCard + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", active=" + active +
                ", role=" + role +
                ", trainerRequests=" + transactions +
                '}';
    }
}
