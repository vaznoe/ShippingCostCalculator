package com.vaznoe.service.impl;

import com.vaznoe.model.Discount;
import com.vaznoe.repository.DiscountRepository;
import com.vaznoe.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private DiscountRepository repository;

    @Override
    public Discount addDiscount(Discount discount) {
        return repository.save(discount);
    }

    @Override
    public void deleteDiscount(long id) {
        repository.deleteById(id);
    }
}
