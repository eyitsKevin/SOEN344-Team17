package com.soen344.ubersante.services;

import com.soen344.ubersante.dto.AvailabilityWrapper.Payment;

public class PaymentSystem implements IPaymentService {

    @Override
    public void processPayment(Payment payment) {
        System.out.println("Payment made for "+ payment.getPrice());
    }

    

}
