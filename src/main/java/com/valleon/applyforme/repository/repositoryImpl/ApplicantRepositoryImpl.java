package com.valleon.applyforme.repository.repositoryImpl;

import com.valleon.applyforme.model.exceptions.MemberNotFoundException;
import com.valleon.applyforme.model.domain.Member;
import com.valleon.applyforme.repository.ApplicantRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ApplicantRepositoryImpl implements ApplicantRepository {
   private static final int DEFAULT_PAGE_SIZE = 11;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Member getMyDetailsById(Long id) {
        Member member = entityManager.find(Member.class, id);

        if (member == null){
            throw new MemberNotFoundException(id);
        }
        return member;
    }
}
