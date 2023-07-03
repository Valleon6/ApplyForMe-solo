package com.valleon.applyforme.ServiceImpl.payment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.valleon.applyforme.exceptions.NotAuthorisedException;
import com.valleon.applyforme.exceptions.PlanCreationException;
import com.valleon.applyforme.model.dto.payment.CreatePlanDto;
import com.valleon.applyforme.model.response.payment.CreatePlanResponse;
import com.valleon.applyforme.services.payment.PaystackService;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static com.valleon.applyforme.constants.PagingConstants.PAYSTACK_INIT;
import static com.valleon.applyforme.constants.PagingConstants.STATUS_CODE_CREATED;

@Service
public class PaystackServiceImpl implements PaystackService {

    @Value("${applyforme.paystack.secret.key}")
    private String paystackSecretKey;

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
                while ((line = rd.readLine())!=null){
                    result.append(line);
                }
            } else {
                throw new PlanCreationException("Paystack");
            }
            ObjectMapper mapper = new ObjectMapper();
            createPlanResponse = mapper.readValue(result.toString(), CreatePlanResponse.class);
        }catch (Throwable ex){
            throw new NotAuthorisedException("Paystack");
        }

        return createPlanResponse;
    }
}
