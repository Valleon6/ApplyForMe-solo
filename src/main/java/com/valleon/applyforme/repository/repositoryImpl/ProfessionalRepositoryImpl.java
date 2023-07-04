package com.valleon.applyforme.repository.repositoryImpl;

import com.valleon.applyforme.model.exceptions.ProfessionalDuplicateEntityException;
import com.valleon.applyforme.model.domain.Professional;
import com.valleon.applyforme.repository.ProfessionalRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ProfessionalRepositoryImpl implements ProfessionalRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private static final int DEFAULT_PAGE_SIZE = 11;

    @Override
    public Professional saveOne(Professional body) {
        try {
            entityManager.persist(body);
            return body;
        } catch (EntityNotFoundException ex) {
            throw new ProfessionalDuplicateEntityException();
        }

    }

    @Override
    public Professional getOne(Long id) {
        String query = "select p from Professional p where p.member.id = :id";
        TypedQuery<Professional> professional = entityManager.createQuery(query, Professional.class);
        professional.setParameter("id", id);
        return professional.getSingleResult();
    }

    @Override
    public List<Professional> getAll(Integer pageOffset) {

        String q = "select p from Professional p order by p.member.updatedOn desc";
        TypedQuery<Professional> professionalTypedQuery = entityManager.createQuery(q, Professional.class);

        professionalTypedQuery.setFirstResult((pageOffset - 1) * DEFAULT_PAGE_SIZE);
        professionalTypedQuery.setMaxResults(DEFAULT_PAGE_SIZE);
        return professionalTypedQuery.getResultList();
    }
}
