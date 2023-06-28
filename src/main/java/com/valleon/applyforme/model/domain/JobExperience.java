package com.valleon.applyforme.model.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "job_experience")
public class JobExperience {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "company_name")
    private String company;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_state")
    private Date startDate;

    @Column(name = "ended_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
}
