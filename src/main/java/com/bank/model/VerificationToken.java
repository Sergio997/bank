package com.bank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.PrePersist;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "verification_token")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String token;
    @Column(name = "created_date")
    private Date createdDate;
    @OneToOne(targetEntity = Card.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "card_id")
    private Card card;


    @PrePersist
    public void prePersist() {
        createdDate = new Date();
    }

    public VerificationToken(String token, Card card) {
        this.token = token;
        this.card = card;
    }

    public VerificationToken(Card card) {
        this.card = card;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VerificationToken that = (VerificationToken) o;
        return Objects.equals(id, that.id)
                && Objects.equals(token, that.token)
                && Objects.equals(card, that.card)
                && Objects.equals(createdDate, that.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, token, card, createdDate);
    }

    @Override
    public String toString() {
        return "VerificationToken{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", card=" + card +
                ", createdDate=" + createdDate +
                '}';
    }
}
