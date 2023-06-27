package com.valleon.applyforme.exceptions;

import lombok.AllArgsConstructor;


public class MemberNotFoundException extends ApplyForMeException {
    private static final long serialVersionUID = 1L;

    private static final String ENTITY_NAME ="Member";
    private Object entityId = null;

    public MemberNotFoundException(Object entityId) {
        this.entityId = entityId;
    }

    public Object getEntityId() {
        return String.format("%s with an id %s cannot be found or does not exist in record.", ENTITY_NAME, entityId.toString());
    }
}
