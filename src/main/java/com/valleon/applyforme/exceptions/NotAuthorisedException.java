package com.valleon.applyforme.exceptions;

public class NotAuthorisedException extends  ApplyForMeException{

    private static final Long serialVersionUID = 1L;

    private String entity_Id = null;

    public NotAuthorisedException(String entity_Id) {
        this.entity_Id = entity_Id;
    }

    @Override
    public String getMessage() {
        return String.format("Error occurred while trying to perform action %s", entity_Id);
    }
}
