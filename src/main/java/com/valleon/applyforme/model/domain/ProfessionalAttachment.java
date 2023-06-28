package com.valleon.applyforme.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PROFESSIONAL_ATTACHMENT")
public class ProfessionalAttachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "passport_link", nullable = false)
    private String passportLink;

    @Column(name = "resume_link", nullable = false)
    private String resumeLink;

    @Column(name = "cover_letter_link", nullable = false)
    private String coverLetterLink;

    @JoinColumn(name = "professional_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Professional professional;

}
