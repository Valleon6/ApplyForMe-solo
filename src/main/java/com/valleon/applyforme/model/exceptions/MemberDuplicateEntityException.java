package com.valleon.applyforme.model.exceptions;

public class MemberDuplicateEntityException extends  ApplyForMeException{

    private static final long serialVersionUID = 1L;

    private static final String ENTITY_NAME = "Member";

    @Override
    public String getMessage() {
        return String.format("%s entry already exist in record.", ENTITY_NAME);
    }
}
