package com.valleon.applyforme.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.valleon.applyforme.annotations.PhoneNumber;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "{memberName} First name {notNullPhrase}")
    @JsonProperty(namespace = "first_name")
    private String firstName;

    @NotNull(message = "{memberName} First name {notNullPhrase}")
    @JsonProperty(namespace = "last_name")
    private String lastName;

    @NotNull(message = "{memberName} First name {notNullPhrase}")
    @NotBlank(message = "{memberName} Email Address {notBlankPhrase}")
    @Email(message = "{memberName} Please enter a valid Email address")
    @JsonProperty(namespace = "email_address")
    private String emailAddress;

    @NotNull(message = "{memberName} Password {notNullPhrase}")
    @NotBlank(message = "{memberName} Password {notBlankPhrase}")
    @Size(min = 8, message = "{memberName} Password should be at least {min} characters ...")
    @JsonProperty(namespace = "password")
    private String password;

    @NotNull(message = "{memberName} First name {notNullPhrase}")
    @PhoneNumber
    @JsonProperty(namespace = "phone_number")
    private String phoneNumber;

}
