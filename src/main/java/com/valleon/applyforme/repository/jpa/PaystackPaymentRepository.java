package com.valleon.applyforme.repository.jpa;

import com.valleon.applyforme.model.domain.PaymentPaystack;

public interface PaystackPaymentRepository {

    void saveOne(PaymentPaystack paymentPaystack);
}
