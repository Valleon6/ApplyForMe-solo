package com.valleon.applyforme.services;

import com.valleon.applyforme.model.response.ApplyForMeResponse;

public interface JobSubmissionService {

    Long countAllApplierSubmissions(Long applierId);

    ApplyForMeResponse getAllJobSubmissions(int pageNo, int pageSize, String sortDir, String sortBy);

    ApplyForMeResponse filterJobSubmission(int pageNo, int pageSize, String sortDir, String sortBy, String q);

}
