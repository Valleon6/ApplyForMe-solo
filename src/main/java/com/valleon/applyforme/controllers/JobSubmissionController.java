package com.valleon.applyforme.controllers;

import com.valleon.applyforme.constants.PagingConstants;
import com.valleon.applyforme.model.dto.submission.ApplierSubmissionDto;
import com.valleon.applyforme.model.response.ApplyForMeResponse;
import com.valleon.applyforme.services.JobSubmissionService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "submission", produces = {MediaType.APPLICATION_JSON_VALUE})
@CrossOrigin("*")
public class JobSubmissionController {

    private final JobSubmissionService jobSubmissionService;


    public JobSubmissionController(JobSubmissionService jobSubmissionService) {
        this.jobSubmissionService = jobSubmissionService;
    }


    @GetMapping("/applier/count/{applierId}")
    public Long jobSubmissionsCount(@PathVariable(name = "applierId") Long applierId) {
        return jobSubmissionService.countAllApplierSubmissions(applierId);
    }

    @GetMapping("/entries")
    public ApplyForMeResponse getAllSubmissions(
            @RequestParam(required = false, value = "pageNo", defaultValue = PagingConstants.DEFAULT_PAGE_NUMBER) int pageNo,
            @RequestParam(required = false, value = "pageSize", defaultValue = PagingConstants.DEFAULT_PAGE_SIZE) int pageSize,
            @RequestParam(required = false, value = "sortDir", defaultValue = PagingConstants.DEFAULT_SORT_DIRECTION) String sortDir,
            @RequestParam(required = false, value = "sortBy", defaultValue = PagingConstants.DEFAULT_SORT_BY) String sortBy){
        return jobSubmissionService.getAllJobSubmissions(pageNo, pageSize, sortDir, sortBy);
    }

    @GetMapping("/entries/search")
    public ApplyForMeResponse getAllSubmissionsBySearch(
            @RequestParam(required = false, value = "pageNo", defaultValue = PagingConstants.DEFAULT_PAGE_NUMBER) int pageNo,
            @RequestParam(required = false, value = "pageSize", defaultValue = PagingConstants.DEFAULT_PAGE_SIZE) int pageSize,
            @RequestParam(required = false, value = "sortDir", defaultValue = PagingConstants.DEFAULT_SORT_DIRECTION) String sortDir,
            @RequestParam(required = false, value = "sortBy", defaultValue = PagingConstants.DEFAULT_SORT_BY) String sortBy,
            @RequestParam(value = "q") String q){
        return jobSubmissionService.filterJobSubmission(pageNo, pageSize, sortDir, sortBy, q);
    }

    @GetMapping("/submission-details")
    public List<ApplierSubmissionDto> getSubmissionDetails(Long applierId){
        return jobSubmissionService.getApplierSubmissionDetails(applierId);
    }

}
