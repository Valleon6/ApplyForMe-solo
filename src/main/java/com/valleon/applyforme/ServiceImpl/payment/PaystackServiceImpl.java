package com.valleon.applyforme.ServiceImpl.payment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.valleon.applyforme.enums.PricingPlanType;
import com.valleon.applyforme.exceptions.NotAuthorisedException;
import com.valleon.applyforme.exceptions.PaymentInitializationException;
import com.valleon.applyforme.exceptions.PaymentVerificationException;
import com.valleon.applyforme.exceptions.PlanCreationException;
import com.valleon.applyforme.model.domain.Member;
import com.valleon.applyforme.model.domain.PaymentPaystack;
import com.valleon.applyforme.model.dto.payment.CreatePlanDto;
import com.valleon.applyforme.model.dto.payment.InitializePaymentDto;
import com.valleon.applyforme.model.response.payment.CreatePlanResponse;
import com.valleon.applyforme.model.response.payment.InitializePaymentResponse;
import com.valleon.applyforme.model.response.payment.PaymentVerificationResponse;
import com.valleon.applyforme.repository.jpa.MemberJpaRepository;
import com.valleon.applyforme.repository.jpa.PaystackPaymentRepository;
import com.valleon.applyforme.services.payment.PaystackService;
import com.valleon.applyforme.utilities.CurrentUserUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;

import static com.valleon.applyforme.constants.PagingConstants.*;

@Service
public class PaystackServiceImpl implements PaystackService {

    @Value("${applyforme.paystack.secret.key}")
    private String paystackSecretKey;

    private MemberJpaRepository memberJpaRepository;

    private PaystackPaymentRepository paystackPaymentRepository;

    @Override
    public CreatePlanResponse createPlan(CreatePlanDto createPlanDto) throws Exception {
        CreatePlanResponse createPlanResponse = null;

        try {
            Gson gson = new Gson();
            StringEntity postingString = new StringEntity(gson.toJson(createPlanDto));
            HttpClient client = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(PAYSTACK_INIT);
            post.setEntity(postingString);
            post.addHeader("Content-type", "application/json");
            post.addHeader("Authorization", "Bearer " + paystackSecretKey);
            StringBuilder result = new StringBuilder();

            HttpResponse response = client.execute(post);

            if (response.getStatusLine().getStatusCode() == STATUS_CODE_CREATED) {
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
            } else {
                throw new PlanCreationException("Paystack");
            }
            ObjectMapper mapper = new ObjectMapper();
            createPlanResponse = mapper.readValue(result.toString(), CreatePlanResponse.class);
        } catch (Throwable ex) {
            throw new NotAuthorisedException("Paystack");
        }

        return createPlanResponse;
    }

    @Override
    public InitializePaymentResponse initializePayment(InitializePaymentDto initializePaymentDto) {
        InitializePaymentResponse initializePaymentResponse = null;

        try {
            Gson gson = new Gson();
            StringEntity postingString = new StringEntity(gson.toJson(initializePaymentDto));
            HttpClient client = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(PAYSTACK_INITIALIZE_PAY);
            post.setEntity(postingString);
            post.addHeader("Content-Type", "application/json");
            post.addHeader("Authorization", "Bearer " + paystackSecretKey);
            StringBuilder result = new StringBuilder();
            HttpResponse response = client.execute(post);

            if (response.getStatusLine().getStatusCode() == STATUS_CODE_OK) {
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
            } else {
                throw new PaymentInitializationException("Paystack");
            }

            ObjectMapper mapper = new ObjectMapper();
            initializePaymentResponse = mapper.readValue(result.toString(), InitializePaymentResponse.class);

        } catch (Throwable ex) {
            throw new NotAuthorisedException("Paystack");


        }
        return initializePaymentResponse;
    }

    @Override
    public PaymentVerificationResponse paymentVerification(String reference, String plan) throws Exception {
        PaymentVerificationResponse paymentVerificationResponse = null;
        PaymentPaystack paymentPaystack = null;

        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(PAYSTACK_VERIFY + reference);
            request.addHeader("Content-type", "application/json");
            request.addHeader("Authorization", "Bearer " + paystackSecretKey);
            StringBuilder result = new StringBuilder();
            HttpResponse response = client.execute(request);

            if (response.getStatusLine().getStatusCode() == STATUS_CODE_OK) {
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }

            } else {
                throw new PaymentVerificationException("Paystack");
            }

            ObjectMapper mapper = new ObjectMapper();
            paymentVerificationResponse = mapper.readValue(result.toString(), PaymentVerificationResponse.class);

            if (paymentVerificationResponse == null || paymentVerificationResponse.getStatus().equals("false")) {
                throw new Exception("An error");
            } else if (paymentVerificationResponse.getData().getStatus().equals("success")) {

                var currentUser = CurrentUserUtil.getCurrentUser();

                Member member = memberJpaRepository.findById(currentUser.getId()).get();
                PricingPlanType pricingPlanType = PricingPlanType.valueOf(plan.toUpperCase());

                paymentPaystack = PaymentPaystack.builder()
                        .member(member)
                        .reference(paymentVerificationResponse.getData().getReference())
                        .amount(paymentVerificationResponse.getData().getAmount())
                        .gatewayResponse(paymentVerificationResponse.getData().getGatewayResponse())
                        .paidAt(paymentVerificationResponse.getData().getPaidAt())
                        .createdAt(paymentVerificationResponse.getData().getCreatedAt())
                        .channel(paymentVerificationResponse.getData().getChannel())
                        .currency(paymentVerificationResponse.getData().getCurrency())
                        .ipAddress(paymentVerificationResponse.getData().getIpAddress())
                        .pricingPlanType(pricingPlanType)
                        .createdOn(new Date())
                        .build();
            }

        } catch (Exception ex) {
            throw new NotAuthorisedException("Paystack");
        }
        paystackPaymentRepository.saveOne(paymentPaystack);
        return paymentVerificationResponse;
    }
}
