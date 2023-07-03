package com.valleon.applyforme.repository.jpa;

import com.valleon.applyforme.model.domain.Submission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface JobSubmissionJpaRepository extends JpaRepository<Submission, Long> {

    @Query(value = "select s from Submission s inner join ProfessionalProfile pp on s.professional.id = pp.professional.id where s.professional.id = :id")
    Page<Submission> getSubmission(Long id, Pageable pageable);

    @Query(value = "select jb from Submission jb where" +
            "(jb.createdOn between :from and :to) and " +
            "jb.jobTitle like '%' || :q || '%' or jb.jobLocation like '%' || :q || '%' or " +
            "jb.jobCompany like '%' || :q || '%' or jb.jobLink like '%' || :q || '%' ")
    Page<Submission> getEntries(Date from, Date to, String q, Pageable pageable);


    @Query(value = "select jb from Submission jb where (jb.createdOn between :from and :to)")
    Page<Submission> getEntries(Date from, Date to, Pageable pageable);

    @Query(value = "select jb from Submission jb where " +
            "jb.jobTitle like '%' || :q || '%' or jb.jobLocation like '%' || :q || '%' or " +
            "jb.jobCompany like '%' || :q || '%' or jb.jobLink like '%' || :q || '%' ")
    Page<Submission> getEntries(String q, Pageable pageable);


    @Query(value = "select jb from Submission jb")
    Page<Submission> getEntries(Pageable pageable);
}
