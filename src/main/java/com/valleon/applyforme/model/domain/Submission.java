package com.valleon.applyforme.model.domain;

import com.valleon.applyforme.model.enums.JobLocationType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "job_submission")
public class Submission {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "professional_id")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Professional professional;

    @JoinColumn(name = "applier_id")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Applier applier;

    @Column(name = "job_title", nullable = false)
    private String jobTitle;

    @Column(name = "job_link", nullable = false)
    private String jobLink;

    @Column(name = "job_location")
    private String jobLocation;

    @Column(name = "job_company")
    private String jobCompany;

    @Column(name = "job_location_type")
    @Enumerated(EnumType.STRING)
    private JobLocationType jobLocationType = JobLocationType.ONSITE;

    /**
     * summary or description of the job the applier or reverse recruiter submitted on behalf of the developer.
     */

    @Column(name = "summary")
    private String summary;

    @Column(name = "other_comment", nullable = false)
    private String otherComment;

   @JoinColumn(name = "professional_profile")
   @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   private ProfessionalProfile professionalProfile;

    @Column(name = "created_on", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdOn;

    @Column(name = "updated_on", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updatedOn;

}
