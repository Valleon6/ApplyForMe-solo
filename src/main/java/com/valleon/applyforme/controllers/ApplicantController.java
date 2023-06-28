package com.valleon.applyforme.controllers;

import com.valleon.applyforme.model.domain.Member;
import lombok.var;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "applicant", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ApplicantController {


    @GetMapping("/details")
    public Member getMyDetails(Long id){
        var

    }
}
