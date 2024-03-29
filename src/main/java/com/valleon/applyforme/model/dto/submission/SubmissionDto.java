package com.valleon.applyforme.model.dto.submission;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.valleon.applyforme.model.enums.JobLocationType;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubmissionDto {

   @JsonProperty("id")
   private Long id;

   @JsonProperty("professional_id")
   private Long professionalId;

   @JsonProperty("applier_id")
   private Long applierId;

   @JsonProperty("professional_profile_id")
   private Long professionalProfileId;

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

   @JsonProperty("created_on")
   private Date createdOn;

   @JsonProperty("updated_on")
   private Date updatedOn;


}
