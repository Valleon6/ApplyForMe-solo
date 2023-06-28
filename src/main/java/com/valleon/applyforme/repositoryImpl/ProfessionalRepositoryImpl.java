package com.valleon.applyforme.repositoryImpl;

import com.valleon.applyforme.exceptions.ProfessionalDuplicateEntityException;
import com.valleon.applyforme.model.domain.Professional;
import com.valleon.applyforme.repository.ProfessionalRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

public class ProfessionalRepositoryImpl implements ProfessionalRepository {

   @PersistenceContext
   private EntityManager entityManager;

    @Override
    public Professional saveOne(Professional body) {
       try{
           entityManager.persist(body);
           return body;
       }catch (EntityNotFoundException ex){
           throw new ProfessionalDuplicateEntityException();
       }

    }
}
