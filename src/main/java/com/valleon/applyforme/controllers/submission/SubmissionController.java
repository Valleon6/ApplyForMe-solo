package com.valleon.applyforme.controllers.submission;

import com.valleon.applyforme.model.response.ApplyForMeResponse;
import com.valleon.applyforme.services.JobSubmissionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static com.valleon.applyforme.constants.PagingConstants.*;

@RestController
@RequestMapping(
        value = "job-submission",
        produces = { MediaType.APPLICATION_JSON_VALUE }
)
public class SubmissionController {

    private final JobSubmissionService jobSubmissionService;

    public SubmissionController(JobSubmissionService jobSubmissionService) {
        this.jobSubmissionService = jobSubmissionService;
    }

    @GetMapping("/entries/all")
    public ApplyForMeResponse findEntries(
            @RequestParam(value = "pageNo", defaultValue = DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = DEFAULT_SORT_DIRECTION, required = false) String sortDir,
            @RequestParam(value = "from", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fromDate,
            @RequestParam(value = "to", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date toDate,
            @RequestParam(value = "q", required = false) String q) {
        return jobSubmissionService.getEntries(pageNo, pageSize, sortBy, sortDir, q, fromDate, toDate);
    }
}
