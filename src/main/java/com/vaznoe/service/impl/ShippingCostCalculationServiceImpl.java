package com.vaznoe.service.impl;

import com.vaznoe.exception.ShippingCalculationException;
import com.vaznoe.model.Discount;
import com.vaznoe.model.ShippingCostCalculationRequest;
import com.vaznoe.model.ShippingCostCalculationResponse;
import com.vaznoe.model.ShippingRate;
import com.vaznoe.repository.DiscountRepository;
import com.vaznoe.repository.ShippingRateRepository;
import com.vaznoe.service.ShippingCostCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShippingCostCalculationServiceImpl implements ShippingCostCalculationService {

    @Autowired
    private ShippingRateRepository rateRepository;

    @Autowired
    private DiscountRepository discountRepository;

    @Value("${seed.database}")
    private boolean seedDatabase;

    @Override
    public ShippingCostCalculationResponse calculateShippingCost(ShippingCostCalculationRequest request) {
        ShippingRate rate = getShippingRate(request);

        ShippingCostCalculationResponse response = new ShippingCostCalculationResponse();
        int shippingCost = rate.getCostPerMile() * request.getDistance();
        response.setShippingCost(applyDiscount(new BigDecimal(shippingCost), request));
        response.setRateName(rate.getName());
        return response;
    }

    private ShippingRate getShippingRate(ShippingCostCalculationRequest request) {
        List<ShippingRate> rates = (List<ShippingRate>) rateRepository.findAll();
        if (rates.size() == 0) {
            throw new RuntimeException();
        }

        for (ShippingRate rate : rates) {
            if (request.getWeight() >= rate.getMinimumWeight() && request.getWeight() <= rate.getMaximumWeight()) {
                return rate;
            }
        }
        throw new ShippingCalculationException("Shipment weight is greater than maximum weight.");
    }

    private BigDecimal applyDiscount(BigDecimal shippingCost, ShippingCostCalculationRequest request) {
        Discount discount = getDiscount(request);
        if (discount == null) {
            return shippingCost;
        }
        return shippingCost.subtract(shippingCost.divide(new BigDecimal("100")).multiply(new BigDecimal(discount.getDiscountPercent())));
    }

    private Discount getDiscount(ShippingCostCalculationRequest request) {
        List<Discount> discounts = (List<Discount>) discountRepository.findAll();
        for (Discount discount : discounts) {
            if (request.getShipmentDate().after(discount.getFromDate()) && request.getShipmentDate().before(discount.getToDate())) {
                return discount;
            }
        }
        return null;
    }

    @PostConstruct
    public void seedDatabase() throws ParseException {
        if (seedDatabase) {
            rateRepository.saveAll(getSeedRates());
            discountRepository.saveAll(getSeedDiscounts());
        }
        rateRepository.findAll().forEach(e -> System.out.println(e.toString()));
        discountRepository.findAll().forEach(e -> System.out.println(e.toString()));
    }

    private List<ShippingRate> getSeedRates() {
        List<ShippingRate> seedRates = new ArrayList<>();
        ShippingRate small = new ShippingRate();
        small.setMinimumWeight(1);
        small.setMaximumWeight(3);
        small.setName("small");
        small.setCostPerMile(2);
        seedRates.add(small);

        ShippingRate medium = new ShippingRate();
        medium.setName("medium");
        medium.setMinimumWeight(4);
        medium.setMaximumWeight(10);
        medium.setCostPerMile(5);
        seedRates.add(medium);
        return seedRates;
    }

    private List<Discount> getSeedDiscounts() throws ParseException {
        List<Discount> discounts = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Discount discount = new Discount();
        discount.setDiscountPercent(15);
        discount.setFromDate(format.parse("1/1/2018"));
        discount.setToDate(format.parse("1/2/2018"));
        discounts.add(discount);
        return discounts;
    }
}
