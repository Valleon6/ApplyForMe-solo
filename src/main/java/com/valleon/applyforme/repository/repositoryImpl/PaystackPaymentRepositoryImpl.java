package com.valleon.applyforme.repository.repositoryImpl;

import com.valleon.applyforme.model.exceptions.PaymentDetailsNotSaveException;
import com.valleon.applyforme.model.domain.PaymentPaystack;
import com.valleon.applyforme.repository.jpa.PaystackPaymentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PaystackPaymentRepositoryImpl implements PaystackPaymentRepository {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void saveOne(PaymentPaystack paymentPaystack) {
        try{
            entityManager.persist(paymentPaystack);
        }
        catch (PaymentDetailsNotSaveException ex){
            throw new PaymentDetailsNotSaveException("Paystack");
        }
    }
}
