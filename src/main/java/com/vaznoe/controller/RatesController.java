package com.vaznoe.controller;

import com.vaznoe.model.ShippingRate;
import com.vaznoe.service.ShippingRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rates")
public class RatesController {

    @Autowired
    private ShippingRateService service;

    @PostMapping("/add")
    public ResponseEntity addRate(@RequestBody ShippingRate rate) {
        return ResponseEntity.ok().body(service.addShippingRate(rate));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRate(@PathVariable int id) {
        service.deleteShippingRate(id);
    }
}