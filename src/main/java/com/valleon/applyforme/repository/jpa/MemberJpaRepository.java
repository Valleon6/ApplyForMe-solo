package com.valleon.applyforme.repository.jpa;

import com.valleon.applyforme.model.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberJpaRepository extends JpaRepository<Member, Long> {
   Member findMemberById(Long id);
   boolean existsByEmailAddress(String emailAddress);

   Member findByEmailAddress(String emailAddress);



}
