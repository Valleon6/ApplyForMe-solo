package com.valleon.applyforme.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.valleon.applyforme.enums.JobLocationType;
import com.valleon.applyforme.model.domain.Applier;
import com.valleon.applyforme.model.domain.Professional;
import com.valleon.applyforme.model.domain.ProfessionalProfile;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ApplicantJobResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("professional_id")
    private Professional professional;

    @JsonProperty("applier_id")
    private Applier applier;

    @JsonProperty("job_title")
    private String jobTitle;

    @JsonProperty("job_link")
    private String jobLink;

    @JsonProperty("job_location")
    private String jobLocation;

    @JsonProperty("job_company")
    private String jobCompany;

    @JsonProperty("job_location_type")
    private JobLocationType jobLocationType;

    @JsonProperty("summary")
    private String summary;

    @JsonProperty("other_comment")
    private String otherComment;

    @JsonProperty("professional_profile_id")
    private ProfessionalProfile professionalProfileId;

    @JsonProperty("created_on")
    private Date createdOn;

    @JsonProperty("updated_on")
    private Date updatedOn;

}
