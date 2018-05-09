package com.vaznoe.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class ShippingRate {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private int minimumWeight;
    private int maximumWeight;
    private int costPerMile;
}
