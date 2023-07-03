package com.valleon.applyforme.exceptions;

public class PaymentInitializationException extends ApplyForMeException {
    private static final Long serialVersionUID = 1L;

    private static final String ENTITY_ID = "Paystack";

    private String entityId;

    public PaymentInitializationException(String entityId) {
        this.entityId = entityId;
    }

    @Override
    public String getMessage() {
        return String.format("Error occured while trying to initialize payment %s", entityId);
    }
}
