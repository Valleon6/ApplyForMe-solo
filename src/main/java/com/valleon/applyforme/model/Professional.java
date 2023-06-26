package com.valleon.applyforme.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "Professional")
public class Professional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JoinColumn(name = "user_id")
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Member member;

    @Column(name = "available_for_interview", nullable = false)
    private boolean availableForInterview;

    @Column(name = "linkedin_link")
    private String linkedinLink;

    @Column(name = "twitter_link")
    private String twitterLink;

    @Column(name = "facebook_link")
    private String facebookLink;

    @Column(name = "instagram_link")
    private String instagramLink;

    @Column(name = "hobbies")
    private String hobbies;

    @Column(name = "other_link")
    private String otherLink;

    @Column(name = "other_link_2")
    private String otherLink2;

    @Column(name = "other_link_3")
    private String otherLink3;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ProfessionalProfile> professionalProfiles = new HashSet<>();


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "professional")
    private Set<Submission> submissions = new HashSet<>();

}
