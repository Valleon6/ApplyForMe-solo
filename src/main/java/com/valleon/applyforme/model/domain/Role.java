package com.valleon.applyforme.model.domain;

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
@Table(name = "roles", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"title", "code"}) })
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "code", nullable = false)
    private String code;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "created_on")
    private Date createdOn;

    @UpdateTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "updated_on")
    private Date updatedOn;
}
