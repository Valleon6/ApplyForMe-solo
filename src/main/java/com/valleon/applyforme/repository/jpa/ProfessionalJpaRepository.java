package com.valleon.applyforme.repository.jpa;

import com.valleon.applyforme.model.domain.Professional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProfessionalJpaRepository extends JpaRepository<Professional, Long > {

    Professional getProfessionalByMemberId(Long memberId);

    @Query("select p from Professional p where p.member.id = :id")
    Professional getProfessional(@Param("id") Long memberId);

}
