package com.valleon.applyforme.model;

import lombok.Getter;

@Getter
public enum JobLocationType {

    REMOTE("REMOTE"),
    ONSITE("ONSITE"),
    HYBRID("HYBRID");

    private String value;

    JobLocationType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.toString();
    }
}
