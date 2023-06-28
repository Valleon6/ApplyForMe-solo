package com.valleon.applyforme.model.domain;

import com.valleon.applyforme.enums.EmploymentType;
import com.valleon.applyforme.enums.JobLocationType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "professional_profile")
public class ProfessionalProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * This is used to identify the profile that an applier can use to apply and submit a job application that is tailored or suitable to the professional.
     */
    @Column(name = "profile_title", nullable = false)
    private String profileTitle;

    /**
     * This attribute is useful when a developer or designer or any other professional wants to create another profile.
     * If this profile is set as the main profile, anytime a new profile is about to be created; the details or data in the profile
     * record set as main will be used to prepopulate the fields to be used for the new profile to be persisted or saved or created.
     */
    @Column (name = "main_profile")
    private Boolean mainProfile = false;

    @Column(name = "passport_link")
    private String passportLink;

    @Column(name = "resume_link")
    private String resumeLink;

    @Column(name = "cover_letter_link")
    private String coverLetterLink;

    @Column(name = "cover_letter_subject")
    private String coverLetterSubject;

    @Column(name = "salary_range")
    private String salaryRange;

    @Column(name = "employment_type")
    @Enumerated(EnumType.STRING)
    private EmploymentType employmentType = EmploymentType.FULL_TIME;

    @Column(name = "job_location")
    private String jobLocation;

    @Column(name = "preferred_job_location_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private JobLocationType preferredJobLocationType = JobLocationType.ONSITE;


    @Column(name = "job_seniority", nullable = false)
    private String jobSeniority;

    @Column(name = "desired_job_title", nullable = false)
    private String desiredJobTitle;

    @Column(name = "industry", nullable = false)
    private String industry;

    @Column(name = "year_of_experience", nullable = false)
    private float yearOfExperience;

    @Column(name = "other_skills")
    private String otherSkills;

    @Column(name = "other_comment")
    private String otherComment;

    @Column(name = "included_keywords")
    private String includedKeywords;

    @ManyToOne (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "professional_id")
    private Professional professional;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "created_on", nullable = false, updatable = false)
    private Date createdOn;

    @Column(name = "updated_on", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updatedOn;


}
