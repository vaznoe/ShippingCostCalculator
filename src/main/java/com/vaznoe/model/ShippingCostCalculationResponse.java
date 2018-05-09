package com.vaznoe.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ShippingCostCalculationResponse {

    private BigDecimal shippingCost;
    private String rateName;
}
