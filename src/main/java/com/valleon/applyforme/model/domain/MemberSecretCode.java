package com.valleon.applyforme.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member_secret_code")
public class MemberSecretCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sign_up_verification_code")
    private String signUpVerificationCode;

    @Column(name = "forgot_password_code")
    private String forgotPasswordCode;
}
