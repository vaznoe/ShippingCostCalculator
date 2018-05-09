package com.vaznoe.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class Discount {

    @Id
    @GeneratedValue
    private long id;
    private Date fromDate;
    private Date toDate;
    private int discountPercent;


}
