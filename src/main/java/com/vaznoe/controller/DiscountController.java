package com.vaznoe.controller;

import com.vaznoe.model.Discount;
import com.vaznoe.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/discount")
public class DiscountController {

    @Autowired
    private DiscountService service;

    @PostMapping("/addDiscount")
    public ResponseEntity addDiscount(@RequestBody Discount discount) {
        return ResponseEntity.ok().body(service.addDiscount(discount));
    }

    @DeleteMapping
    public void deleteDiscount(@PathVariable int id) {
        service.deleteDiscount(id);
    }
}
