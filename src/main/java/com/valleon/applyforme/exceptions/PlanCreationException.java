package com.valleon.applyforme.exceptions;

public class PlanCreationException extends ApplyForMeException{
   private static final Long serialVersionUID  = 1L;

    private String entityId = null;

    public PlanCreationException(String entityId) {
        this.entityId = entityId;
    }

    @Override
    public String getMessage() {
        return String.format("Error occurred while trying to create a payment plan %s", entityId);
    }
}
