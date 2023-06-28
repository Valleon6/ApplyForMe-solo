package com.valleon.applyforme.repository.jpa;

import com.valleon.applyforme.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberJpaRepository extends JpaRepository<Member, Long> {
   Member findMemberById(Long id);
   boolean existsByEmailAddress(String emailAddress);

}
