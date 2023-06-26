package com.valleon.applyforme.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "applier")
@Builder
public class Applier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

//    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(name = "professional_id")
//    private Professional professional;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "applier", cascade = CascadeType.ALL)
    private Set<Submission> submission;

}
