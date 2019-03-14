package com.soen344.ubersante.services;

import com.soen344.ubersante.dto.AvailabilityWrapper.Payment;

public interface IPaymentService {

    void processPayment(Payment payment);

}
