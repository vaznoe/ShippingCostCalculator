package com.vaznoe.controller;

import com.vaznoe.exception.ShippingCalculationException;
import com.vaznoe.model.ErrorMessage;
import com.vaznoe.model.ShippingCostCalculationRequest;
import com.vaznoe.service.ShippingCostCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shippingCost")
public class ShippingCalculationController {

    @Autowired
    private ShippingCostCalculationService service;

    @PostMapping("/calculate")
    public ResponseEntity calculateShippingCost(@RequestBody ShippingCostCalculationRequest request) {
        return ResponseEntity.ok().body(service.calculateShippingCost(request));
    }

    @ExceptionHandler(ShippingCalculationException.class)
    public ResponseEntity handleShippingCalculationException(ShippingCalculationException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorMessage.builder()
                .message("internal server error occurred while calculating shipping cost."));
    }
}
