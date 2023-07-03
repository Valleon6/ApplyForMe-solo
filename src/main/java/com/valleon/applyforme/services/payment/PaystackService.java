package com.valleon.applyforme.services.payment;

import com.valleon.applyforme.model.dto.payment.CreatePlanDto;
import com.valleon.applyforme.model.response.payment.CreatePlanResponse;

public interface PaystackService {

    CreatePlanResponse createPlan (CreatePlanDto createPlanDto) throws Exception;
}
