package com.valleon.applyforme.model.exceptions;

public class PaymentDetailsNotSaveException extends ApplyForMeException {

    private static final Long serialVersionUID = 1L;

    private String entityId = null;

    public PaymentDetailsNotSaveException(String entityId) {
        this.entityId = entityId;
    }

    @Override
    public String getMessage() {
        return String.format("Error occured while trying to save payment details %s", entityId);
    }
}
