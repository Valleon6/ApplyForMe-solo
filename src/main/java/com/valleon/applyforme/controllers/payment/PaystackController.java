package com.valleon.applyforme.controllers.payment;

import com.valleon.applyforme.model.dto.payment.CreatePlanDto;
import com.valleon.applyforme.model.dto.payment.InitializePaymentDto;
import com.valleon.applyforme.model.response.payment.CreatePlanResponse;
import com.valleon.applyforme.model.response.payment.InitializePaymentResponse;
import com.valleon.applyforme.model.response.payment.PaymentVerificationResponse;
import com.valleon.applyforme.services.payment.PaystackService;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/paystack", produces = {MediaType.APPLICATION_JSON_VALUE})
public class PaystackController {

    private final PaystackService paystackService;


    public PaystackController(PaystackService paystackService) {
        this.paystackService = paystackService;
    }

    @PreAuthorize("hasRole('Professional')")
    @PostMapping("/create-plan")
    public CreatePlanResponse createPlan(@Validated @RequestBody CreatePlanDto createPlanDto) throws Exception {
        return paystackService.createPlan(createPlanDto);
    }

    @PostMapping("/initialize-payment")
    @PreAuthorize("hasRole('Prodfessional')")
    public InitializePaymentResponse initializePayment (@Validated @RequestBody InitializePaymentDto initializePaymentDto){
        return paystackService.initializePayment(initializePaymentDto);
    }

    @PreAuthorize("hasRole('Professional')")
    @GetMapping("/verifypayment/{reference}/{plan}")
    public PaymentVerificationResponse paymentVerification(@PathVariable(value = "reference") String reference,
                                                           @PathVariable(value = "plan") String plan) throws Exception{
        if(reference.isEmpty() || plan.isEmpty()){
            throw new Exception("reference and plan must be provided in path");
        }
        return paystackService.paymentVerification(reference, plan);
    }
}
