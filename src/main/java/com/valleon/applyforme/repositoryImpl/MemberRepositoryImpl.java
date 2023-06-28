package com.valleon.applyforme.repositoryImpl;

import com.valleon.applyforme.exceptions.MemberDuplicateEntityException;
import com.valleon.applyforme.model.domain.Member;
import com.valleon.applyforme.repository.MemberRepository;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


public class MemberRepositoryImpl implements MemberRepository {
 @PersistenceContext
 private EntityManager entityManager;
    @Override
    public Member fetchOne(Long id) {
        String q = "select m from Applier m where m.member.id = :id";
        TypedQuery<Member> query = entityManager.createQuery(q, Member.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public Member saveOne(Member body) {

        try {
            entityManager.persist(body);
            return body;
        }catch (EntityExistsException ex){
            throw new MemberDuplicateEntityException();
        }
    }
}
