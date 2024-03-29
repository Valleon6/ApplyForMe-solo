package com.valleon.applyforme.repository.repositoryImpl;

import com.valleon.applyforme.model.exceptions.MemberDuplicateEntityException;
import com.valleon.applyforme.model.domain.Member;
import com.valleon.applyforme.repository.MemberRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


@Repository
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

    @Override
    public Member getOne(Long id) {
       return entityManager.find(Member.class, id);
    }
}
