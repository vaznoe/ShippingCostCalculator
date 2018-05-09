package com.vaznoe.service;

import com.vaznoe.model.Discount;

public interface DiscountService {

    Discount addDiscount(Discount discount);

    void deleteDiscount(long id);
}
