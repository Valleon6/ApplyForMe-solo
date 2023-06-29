package com.valleon.applyforme.services;

import com.valleon.applyforme.model.domain.Member;
import com.valleon.applyforme.model.domain.Professional;
import com.valleon.applyforme.model.dto.applicant.ApplicantDto;

public interface ApplicantService{

    Member getDetails (Long id);

    Professional update(Long id, ApplicantDto applicantDto);

    
}
