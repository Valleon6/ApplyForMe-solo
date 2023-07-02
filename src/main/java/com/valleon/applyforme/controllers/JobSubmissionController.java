package com.valleon.applyforme.controllers;

import com.valleon.applyforme.services.JobSubmissionService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/job-submission", produces = {MediaType.APPLICATION_JSON_VALUE})
@CrossOrigin("*")
public class JobSubmissionController {

    private final JobSubmissionService jobSubmissionService;

    public JobSubmissionController(JobSubmissionService jobSubmissionService) {
        this.jobSubmissionService = jobSubmissionService;
    }


    @GetMapping("/applier/count/{applierId}")
    public Long jobSubmissionsCount(@PathVariable(name = "applierId") Long applierId){
        return jobSubmissionService.countAllApplierSubmissions(applierId);
    }

}
