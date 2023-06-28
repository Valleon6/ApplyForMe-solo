package com.valleon.applyforme.model.dto.applicant;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty(namespace = "first_name")
    private String firstName;

    @JsonProperty(namespace = "last_name")
    private String lastName;

    @JsonProperty(namespace = "username")
    private String userName;

    @JsonProperty(namespace = "email_address")
    private String emailAddress;

    @JsonProperty(namespace = "phone_number")
    private String phoneNumber;
}
