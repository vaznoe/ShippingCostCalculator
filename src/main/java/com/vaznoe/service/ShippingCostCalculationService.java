package com.vaznoe.service;

import com.vaznoe.model.ShippingCostCalculationRequest;
import com.vaznoe.model.ShippingCostCalculationResponse;

public interface ShippingCostCalculationService {

   ShippingCostCalculationResponse calculateShippingCost(ShippingCostCalculationRequest request);
}
