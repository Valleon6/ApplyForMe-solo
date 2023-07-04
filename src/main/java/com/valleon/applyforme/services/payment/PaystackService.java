package com.valleon.applyforme.services.payment;

import com.valleon.applyforme.model.dto.payment.CreatePlanDto;
import com.valleon.applyforme.model.dto.payment.InitializePaymentDto;
import com.valleon.applyforme.model.response.payment.CreatePlanResponse;
import com.valleon.applyforme.model.response.payment.InitializePaymentResponse;
import com.valleon.applyforme.model.response.payment.PaymentVerificationResponse;

public interface PaystackService {

    CreatePlanResponse createPlan (CreatePlanDto createPlanDto) throws Exception;

    InitializePaymentResponse initializePayment (InitializePaymentDto initializePaymentDto);

    PaymentVerificationResponse paymentVerification(String reference, String plan) throws Exception;
}
