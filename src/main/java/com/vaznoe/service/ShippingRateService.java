package com.vaznoe.service;

import com.vaznoe.model.ShippingRate;

public interface ShippingRateService {

    ShippingRate addShippingRate(ShippingRate shippingRate);

    void deleteShippingRate(long id);
}
