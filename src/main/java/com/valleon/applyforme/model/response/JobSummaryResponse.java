package com.valleon.applyforme.model.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class JobSummaryResponse {

    private Long submissionId;
    private String jobTitle;
    private String jobLocation;
    private String jobCompany;
    private String jobLocationType;
    private Date createdOn;
    private String salaryRange = " ";

}
