package com.valleon.applyforme.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "JOB_SUBMISSION")
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

    @Column(name = "other_comment", nullable = false)
    private String otherComment;

    @Column(name = "created_on", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdOn;

    @Column(name = "updated_on", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updatedOn;

}
