package com.valleon.applyforme.controllers;

import com.valleon.applyforme.model.domain.Professional;
import com.valleon.applyforme.security.UserDetailsImpl;
import com.valleon.applyforme.services.ProfessionalService;
import com.valleon.applyforme.utilities.CurrentUserUtil;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "professional", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ProfessionalController {

    public ProfessionalController(ProfessionalService professionalService) {
        this.professionalService = professionalService;
    }

    private ProfessionalService professionalService;

    @GetMapping(path = "/detail")
    public Professional findOne(){
        UserDetailsImpl currentUser = CurrentUserUtil.getCurrentUser();
        return professionalService.findOne(currentUser.getId());
    }


@GetMapping("/entries")private List<Professional> findAll(
        @RequestParam(name = "page", required = false, defaultValue = "1") Integer pageOffset){
        return professionalService.findAll(pageOffset);
    }

}
