package com.valleon.applyforme.repositoryImpl;

import com.valleon.applyforme.exceptions.ProfessionalDuplicateEntityException;
import com.valleon.applyforme.model.domain.Professional;
import com.valleon.applyforme.repository.ProfessionalRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class ProfessionalRepositoryImpl implements ProfessionalRepository {

    @PersistenceContext
    private EntityManager entityManager;

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
}
