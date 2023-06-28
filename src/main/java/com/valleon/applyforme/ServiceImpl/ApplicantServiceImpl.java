package com.valleon.applyforme.ServiceImpl;

import com.valleon.applyforme.model.domain.Member;
import com.valleon.applyforme.repository.ApplicantRepository;
import com.valleon.applyforme.services.ApplicantService;

public class ApplicantServiceImpl implements ApplicantService {
    private ApplicantRepository applicantRepository;
    @Override
    public Member getDetails(Long id) {
        return applicantRepository.getMyDetailsById(id);
    }
}
