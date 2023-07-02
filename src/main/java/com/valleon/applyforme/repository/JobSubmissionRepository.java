package com.valleon.applyforme.repository;

import com.valleon.applyforme.model.dto.submission.ApplierSubmissionDto;

import java.util.List;

public interface JobSubmissionRepository {

    public List<ApplierSubmissionDto> getSubmissionDetails(Long applierId);


}
