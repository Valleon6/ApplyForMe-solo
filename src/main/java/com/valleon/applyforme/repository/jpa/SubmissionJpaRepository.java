package com.valleon.applyforme.repository.jpa;

import com.valleon.applyforme.model.domain.Submission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubmissionJpaRepository extends JpaRepository<Submission, Long> {

    @Query("select s from Submission s where s.professional.member.id = :professionalId")
    List<Submission> findAllByProfessionalId(Long professionalId);

    @Query("select count (jb) from Submission jb where jb.applier.member.id = :applierId")
    Long countSubmissionByApplierId(Long applierId);

   @Query(value = "select * from Submission where (job_title like %:q% or created_on like %:q% or job_location_type like %:q%)", nativeQuery = true)
   Page<Submission> findSubmissionsBySearch(Pageable pageable, String q);

   @Query(value = "select s from Submission s where s.applier.member = :id")
   Page<Submission> findSubmissionsByApplier_Id ( Long ApplierId);
}
