package com.valleon.applyforme.model.dto.professional;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProfessionalDto {
    @JsonProperty(namespace = "available_for_interview")
    private boolean availableForInterview = false;

    @JsonProperty(namespace = "linkedin_link")
    private String linkedInLink;

    @JsonProperty(value = "twitter_link")
    private String twitterLink;

    @JsonProperty(namespace = "facebook")
    private String facebook;

    @JsonProperty(namespace = "instagram")
    private String instagram;

    @JsonProperty(namespace = "hobbies")
    private String hobbies;

    @JsonProperty(namespace = "other_link")
    private String otherLink;

    @JsonProperty(namespace = "other_link_2")
    private String otherLink2;

    @JsonProperty(namespace = "other_link_3")
    private String otherLink3;


}
