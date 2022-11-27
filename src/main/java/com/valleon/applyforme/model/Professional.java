package com.valleon.applyforme.model;

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
@Table(name = "Professional")
public class Professional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JoinColumn(name = "user_id")
    @OneToOne (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Member member;

    @Column(name = "available_for_interview", nullable = false)
    private boolean availableForInterview;

    @OneToOne(mappedBy = "professional")
    private Applier applier;

    @OneToOne(mappedBy = "professional")
    private ProfessionalAttachment professionalAttachment;

    @OneToOne(mappedBy = "professional")
    private ProfessionalMetadata professionalMetadata;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "professional")
    private Submission submission;

}
