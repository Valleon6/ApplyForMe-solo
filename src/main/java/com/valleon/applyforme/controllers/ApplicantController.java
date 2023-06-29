package com.valleon.applyforme.controllers;

import com.valleon.applyforme.model.domain.Member;
import com.valleon.applyforme.model.domain.Professional;
import com.valleon.applyforme.model.dto.applicant.ApplicantDto;
import com.valleon.applyforme.model.response.ApplyForMeResponse;
import com.valleon.applyforme.services.ApplicantService;
import com.valleon.applyforme.utilities.CurrentUserUtil;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.valleon.applyforme.constants.PagingConstants.*;

@RestController
@RequestMapping(value = "applicant", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ApplicantController {

    private ApplicantService applicantService;

    @GetMapping("/details")
    public Member getMyDetails(Long id) {
        var authenticatedUser = CurrentUserUtil.getCurrentUser();
        assert authenticatedUser != null;
        return applicantService.getDetails(authenticatedUser.getId());

    }

    @PutMapping("/update/{id})")
    public Professional update(@Validated @RequestBody ApplicantDto body, @PathVariable(name = "id") Long id) {
        return applicantService.update(id, body);
    }


    @GetMapping("/entries")
    public ApplyForMeResponse getAllSubmission(
            @RequestParam(value = "pageNo", defaultValue = DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = DEFAULT_SORT_DIRECTION, required = false)String sortDir) {

        return applicantService.getApplicationList(pageNo, pageSize, sortBy, sortDir);
    }
}
