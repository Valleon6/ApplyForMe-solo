package com.valleon.applyforme.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.valleon.applyforme.model.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApplierDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("id")
    private Member member;


}
