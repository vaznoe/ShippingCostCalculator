package com.vaznoe.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class ShippingCalculationException extends RuntimeException {

    @Getter
    @Setter
    private String message;
}
