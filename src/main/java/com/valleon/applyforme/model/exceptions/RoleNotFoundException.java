package com.valleon.applyforme.model.exceptions;

public class RoleNotFoundException extends ApplyForMeException{

    private static final long serialVersionUID =1L;

    private static final String ENTITY_NAME = "Role";

    private Object entityId;

    public RoleNotFoundException(Object entityId) {
        this.entityId = entityId;
    }


    @Override
    public String getMessage() {
        return String.format("%s with an id %s cannot be found or does not exist in record.", ENTITY_NAME, entityId.toString());
    }

}
