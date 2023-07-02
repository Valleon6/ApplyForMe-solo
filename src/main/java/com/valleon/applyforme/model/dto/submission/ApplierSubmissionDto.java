package com.valleon.applyforme.model.dto.submission;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplierSubmissionDto {

    private String jobTitle;

    private String jobLink;
    private String jobLocation;
    private String jobCompany;
    private String jobLocationType;
    private String summary;
    private String otherComment;
    private Date createdOn;

}
