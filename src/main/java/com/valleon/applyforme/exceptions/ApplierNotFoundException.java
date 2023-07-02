package com.valleon.applyforme.exceptions;

public class ApplierNotFoundException extends ApplyForMeException {

    private static final long serialVersionUID = 1L;
    private static final String ENTITY_NAME = "Applier";

    private Object entity_id = null;

    public ApplierNotFoundException(Object entity_id) {
        this.entity_id = entity_id;
    }

    @Override
    public String getMessage() {
        return String.format("%s with an id %s cannot be found or does not exist int the record.", ENTITY_NAME, entity_id);
    }
}
