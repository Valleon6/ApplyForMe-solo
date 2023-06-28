package com.valleon.applyforme.model.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "apply for me")
public class ApplyForMe {

    public ApplyForMe(){
        updatedOn = new Date();
        createdOn = new Date();
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title = "Apply For Me";

    @Column
    private String version = "1.0";

    @Column
    private String description = "You work at a remote company and you earn $1k a month. You are vert happy. But you sometimes think , what if there are better jobs out there for me? But its too much work to apply. In comes ApplyForMe. A service where you tell us your skills and what you are looking for, and people apply to 100's of jobs you on your behalf. All you need to do is attend interviews";

    @Column
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    @Column
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;


}
