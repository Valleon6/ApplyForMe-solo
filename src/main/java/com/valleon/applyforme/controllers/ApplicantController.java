package com.valleon.applyforme.controllers;

import com.valleon.applyforme.model.domain.Member;
import com.valleon.applyforme.services.ApplicantService;
import com.valleon.applyforme.utilities.CurrentUserUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

@RestController
@RequestMapping(value = "applicant", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ApplicantController {

    private ApplicantService applicantService;

    @GetMapping("/details")
    public Member getMyDetails(Long id){
        var authenticatedUser = CurrentUserUtil.getCurrentUser();
        assert authenticatedUser != null;
        return applicantService.getDetails(authenticatedUser.getId());

    }
}
