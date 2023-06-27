package com.valleon.applyforme.enums;

import lombok.Getter;

@Getter
public enum JobSeniority {
    TRAINEE("Trainee"),
    INTERN("Intern"),
    JUNIOR("Junior"),
    MID_LEVEL("Mid_Level"),
    SENIOR("Senior");

    private final String value;

    JobSeniority(String value) {
        this.value = value;
    }
}
