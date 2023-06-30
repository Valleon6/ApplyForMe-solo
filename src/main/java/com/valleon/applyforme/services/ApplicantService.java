package com.valleon.applyforme.services;

import com.valleon.applyforme.model.domain.Member;
import com.valleon.applyforme.model.domain.Professional;
import com.valleon.applyforme.model.dto.applicant.ApplicantDto;
import com.valleon.applyforme.model.response.ApplyForMeResponse;

public interface ApplicantService{

    Member getDetails (Long id);

    Professional update(Long id, ApplicantDto applicantDto);

    ApplyForMeResponse getApplicationList(int pageNo, int pageSize, String sortBy, String sortDir);

    ApplyForMeResponse getApplicationEntries(int pageNo, int pageSize, String sortBy, String sortDir);
}
