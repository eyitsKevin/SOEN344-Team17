package com.soen344.ubersante.services;

import com.soen344.ubersante.dto.AvailabilityWrapper.Payment;

public class PaymentAdapter implements IPaymentService {

    public PaymentPrototype system;

    public PaymentAdapter(PaymentPrototype system){
        this.system = system;
    }

	@Override
	public void processPayment(Payment payment) {
		System.out.println("Payment made for "+ payment.getPrice());
	}
}
