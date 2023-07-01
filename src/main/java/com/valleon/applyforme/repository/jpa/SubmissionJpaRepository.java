package com.valleon.applyforme.repository.jpa;

import com.valleon.applyforme.model.domain.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubmissionJpaRepository extends JpaRepository<Submission, Long> {

    @Query("select s from Submission s where s.professional.member.id = :professionalId")
    List<Submission> findAllByProfessionalId(Long professionalId);
}
