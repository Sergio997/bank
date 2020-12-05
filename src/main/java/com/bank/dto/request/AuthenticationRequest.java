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
public class AuthenticationRequest {
    @NotBlank(message = "numberCard can not be empty!")
    @Pattern(regexp = "^([0-9]{16})$",
            message = "Wrong pattern for email!")
    private String numberCard;
    @NotBlank(message = "Password can not be empty!")
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthenticationRequest that = (AuthenticationRequest) o;
        return Objects.equals(numberCard, that.numberCard) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberCard, password);
    }

    @Override
    public String toString() {
        return "AuthenticationRequest{" +
                "email='" + numberCard + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
