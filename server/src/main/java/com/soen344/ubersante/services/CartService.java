package com.soen344.ubersante.services;

import java.util.HashMap;
import java.util.List;

import com.soen344.ubersante.dto.AvailabilityDetails;

import org.springframework.stereotype.Service;

@Service
public class CartService {

    private HashMap<String, List<AvailabilityDetails>> patientsCart = new HashMap();

    public boolean saveAvailability(String healthCard, List<AvailabilityDetails> availabilities) {
        patientsCart.put(healthCard, availabilities);
        return true;
    }

    public List<AvailabilityDetails> retrieveCartAvailability(String healthCard) {
        return patientsCart.get(healthCard);
    }

    public boolean emptyCart(String healthCard) {
        patientsCart.remove(healthCard);
        return true;
    }
}