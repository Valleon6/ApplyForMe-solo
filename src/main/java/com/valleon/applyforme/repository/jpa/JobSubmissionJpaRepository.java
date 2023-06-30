package com.valleon.applyforme.repository.jpa;

import com.valleon.applyforme.model.domain.Submission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JobSubmissionJpaRepository extends JpaRepository<Submission, Long> {

    @Query(value = "select s from Submission s inner join ProfessionalProfile pp on s.professional.id = pp.professional.id where s.professional.id = :id")
    Page<Submission> getSubmission(Long id, Pageable pageable);
}
