package com.vaznoe.service.impl;

import com.vaznoe.model.ShippingRate;
import com.vaznoe.repository.ShippingRateRepository;
import com.vaznoe.service.ShippingRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShippingRateServiceImpl implements ShippingRateService {

    @Autowired
    private ShippingRateRepository repository;

    @Override
    public ShippingRate addShippingRate(ShippingRate rate) {
        return repository.save(rate);
    }

    @Override
    public void deleteShippingRate(long id) {
        repository.deleteById(id);
    }
}
