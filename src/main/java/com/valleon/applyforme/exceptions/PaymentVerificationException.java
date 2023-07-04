package com.valleon.applyforme.exceptions;


public class PaymentVerificationException extends ApplyForMeException{
    public static final Long serialVersionUID = 1L;
    private String entityId = null;

    public PaymentVerificationException(String entityId) {
        this.entityId = entityId;
    }

    @Override
    public String getMessage() {
        return String.format("Error occurred while trying to verify payment %s", entityId);

    }
}
