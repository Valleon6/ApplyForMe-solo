package com.valleon.applyforme.repository.jpa;

import com.valleon.applyforme.model.domain.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobSubmissionJpaRepository extends JpaRepository<Submission, Long> {

}
