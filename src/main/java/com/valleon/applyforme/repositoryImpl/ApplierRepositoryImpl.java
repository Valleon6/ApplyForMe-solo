package com.valleon.applyforme.repositoryImpl;

import com.valleon.applyforme.model.domain.Applier;
import com.valleon.applyforme.repository.ApplierRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ApplierRepositoryImpl implements ApplierRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Applier getOne(Long applierId) {
//        String query = "select a from Applier a where a.member = :applierId";
//        TypedQuery<Applier> applier = entityManager.createQuery(query, Applier.class);
//        applier.setParameter("applierId", applierId);
//        return applier.getSingleResult();

        return entityManager.find(Applier.class, applierId);
    }
}
