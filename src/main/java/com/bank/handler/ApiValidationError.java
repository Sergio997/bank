package com.bank.handler;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiValidationError implements ApiSubError {

    private String field;
    private Object rejectedValue;
    private String message;

}
