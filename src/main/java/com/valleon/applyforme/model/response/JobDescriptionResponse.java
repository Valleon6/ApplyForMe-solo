package com.valleon.applyforme.model.response;

import com.valleon.applyforme.model.domain.Applier;
import lombok.*;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobDescriptionResponse {

    private Long id;
    private Applier applier;
    private String jobTitle;
    private String jobLink;
    private String jobLocation;
    private String jobCompany;
    private String jobLocationType;
    private String otherComment;
    private String jobSummary;
    private Date createdOn;
    private Date updatedOn;
}
