package com.valleon.applyforme.controllers;

import com.valleon.applyforme.model.dto.ApplierDto;
import com.valleon.applyforme.services.ApplierService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "applier", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ApplierController {

    private final ApplierService applierService;



    public ApplierController(ApplierService applierService) {
        this.applierService = applierService;
    }

    @GetMapping("/entries")
    public List<ApplierDto> retrieveAllAppliers (){
        return applierService.getAllAppliers();
    }

}
