package com.valleon.applyforme.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "salary-range")
public class SalaryRange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   @Column(name = "salary_range", nullable = false)
   @JsonProperty("salary_range")
   private String salaryRange;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "created_on")
    private Date createdOn;

    @UpdateTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "updated_on")
    private Date updatedOn;

}
