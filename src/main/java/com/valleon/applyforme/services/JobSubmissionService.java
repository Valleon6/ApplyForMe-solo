package com.valleon.applyforme.services;

import com.valleon.applyforme.model.dto.submission.ApplierSubmissionDto;
import com.valleon.applyforme.model.response.ApplyForMeResponse;

import java.util.List;

public interface JobSubmissionService {

    Long countAllApplierSubmissions(Long applierId);

    ApplyForMeResponse getAllJobSubmissions(int pageNo, int pageSize, String sortDir, String sortBy);

    ApplyForMeResponse filterJobSubmission(int pageNo, int pageSize, String sortDir, String sortBy, String q);

    List<ApplierSubmissionDto> getApplierSubmissionDetails(Long applierId);
}
