package com.bank.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardRequest {

    @NotBlank(message = "numberCard can not be empty!")
    @Pattern(regexp = "^([0-9]{16})$",
            message = "Wrong pattern for email!")
    private String numberCard;
    private String firstName;
    private String secondName;
    private String password;
    private String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardRequest that = (CardRequest) o;
        return Objects.equals(numberCard, that.numberCard)
                && Objects.equals(firstName, that.firstName)
                && Objects.equals(secondName, that.secondName)
                && Objects.equals(password, that.password)
                && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberCard, firstName, secondName, password, email);
    }

    @Override
    public String toString() {
        return "CardRequest{" +
                "numberCard='" + numberCard + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
