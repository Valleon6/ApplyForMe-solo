package com.valleon.applyforme.model.exceptions;

public class EmailAlreadyExistException extends ApplyForMeException{

    private static final long serialVersionUID = 1L;
    private static final String ENTITY_NAME = "member";

    @Override
    public String getMessage() {
        return String.format("%s already exist with this email in the record and is not available for use by another user.", ENTITY_NAME);
    }
}
