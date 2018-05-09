package com.vaznoe.model;

import lombok.Data;

import java.util.Date;

@Data
public class ShippingCostCalculationRequest {

    private Date shipmentDate;
    private int distance;
    private int weight;
}
