package com.valleon.applyforme.model.response.payment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePlanResponse {

    private Boolean status;
    private String message;
    private Data data;


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class Data{


        @JsonProperty("name")
        private String name;

        @JsonProperty("amount")
        private String amount;

        @JsonProperty("interval")
        private String interval;

        @JsonProperty("integration")
        private String integration;

        @JsonProperty("plan_code")
        private String planCode;

        @JsonProperty("send_invoices")
        private String sendInvoices;

        @JsonProperty("send_sms")
        private String sendSms;

        @JsonProperty("currency")
        private String currency;

        @JsonProperty("id")
        private String id;

        @JsonProperty("created_at")
        private String createdAt;

        @JsonProperty("updated_at")
        private String updatedAt;
    }

}
