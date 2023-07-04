package com.valleon.applyforme.model.exceptions;

public class ProfessionalDuplicateEntityException extends ApplyForMeException{
    private static final long serialVersionUID = 1L;
    private static final String ENTITY_NAME = "Professional";

    @Override
    public String getMessage() {
        return String.format("%s entity already exist in the record", ENTITY_NAME);
    }
}
