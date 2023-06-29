package com.valleon.applyforme.exceptions;

public class ProfessionalNotFoundException extends ApplyForMeException{

    private static final long serialVersionUID = 1L;

    private static final String ENTITY_NAME = "Professional";

    private Object entityId = "id";

    public ProfessionalNotFoundException(Object entityId) {
        this.entityId = entityId;
    }

    @Override
    public String getMessage() {
        return String.format("%s with an id %s  cannot be found or does not exist in the record.", ENTITY_NAME, entityId );
    }
}
