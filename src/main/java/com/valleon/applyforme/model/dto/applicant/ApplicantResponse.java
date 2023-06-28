package com.valleon.applyforme.model.dto.applicant;

import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantResponse {

    private Long id;

    private String jobCompany;

    private String jobTitle;

    private String jobLocation;

    private String jobType;

    private String salaryRange;

    private Date date;
}
