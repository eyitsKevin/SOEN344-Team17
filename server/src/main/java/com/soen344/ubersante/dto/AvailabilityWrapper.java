package com.soen344.ubersante.dto;

import com.soen344.ubersante.models.Patient;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;

/**
 * Wrapper class for Availability from checkout
 * to backend
 */
public class AvailabilityWrapper {

    @NotEmpty
    private Patient patient;

    @NotEmpty
    private List<AvailabilityDetails> cart;

    @NotEmpty
    private Payment payment;


    private class Payment {
        private String ccNumber;
        private String cvv;
        private String exprMonth;
        private String exprYear;
        private int price;

        public Payment() {}

        public Payment(String ccNumber, String cvv, String exprMonth, String exprYear, int price) {
            this.ccNumber = ccNumber;
            this.cvv = cvv;
            this.exprMonth = exprMonth;
            this.exprYear = exprYear;
            this.price = price;
        }

        public String getCcNumber() {
            return ccNumber;
        }

        public String getCvv() {
            return cvv;
        }

        public String getExprMonth() {
            return exprMonth;
        }

        public String getExprYear() {
            return exprYear;
        }

        public int getPrice() {
            return price;
        }
    }

    public Patient getPatient() {
        return patient;
    }

    public List<AvailabilityDetails> getCart() {
        return cart;
    }

    public Payment getPayment() {
        return payment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AvailabilityWrapper)) return false;
        AvailabilityWrapper that = (AvailabilityWrapper) o;
        return Objects.equals(getPatient(), that.getPatient()) &&
                Objects.equals(getCart(), that.getCart()) &&
                Objects.equals(getPayment(), that.getPayment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPatient(), getCart(), getPayment());
    }

    @Override
    public String toString() {
        return "AvailabilityWrapper{" +
                "patient=" + patient +
                ", cart=" + cart +
                ", payment=" + payment +
                '}';
    }
}
