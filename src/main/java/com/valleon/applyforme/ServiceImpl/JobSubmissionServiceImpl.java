package com.valleon.applyforme.ServiceImpl;

import com.valleon.applyforme.exceptions.ApplierNotFoundException;
import com.valleon.applyforme.model.domain.Applier;
import com.valleon.applyforme.repository.ApplierRepository;
import com.valleon.applyforme.repository.jpa.ApplierJpaRepository;
import com.valleon.applyforme.repository.jpa.SubmissionJpaRepository;
import com.valleon.applyforme.services.JobSubmissionService;
import org.springframework.stereotype.Service;

@Service
public class JobSubmissionServiceImpl implements JobSubmissionService {
    private final SubmissionJpaRepository submissionJpaRepository;
    private final ApplierRepository applierRepository;

    private final ApplierJpaRepository applierJpaRepository;

    public JobSubmissionServiceImpl(SubmissionJpaRepository submissionJpaRepository, ApplierRepository applierRepository, ApplierJpaRepository applierJpaRepository) {
        this.submissionJpaRepository = submissionJpaRepository;
        this.applierRepository = applierRepository;
        this.applierJpaRepository = applierJpaRepository;
    }

    @Override
    public Long countAllApplierSubmissions(Long applierId) {
        Applier applier = applierRepository.getOne(applierId);
        if(applier == null){
            throw new ApplierNotFoundException(applierId);
        }
        return submissionJpaRepository.countSubmissionByApplierId(applierId);
    }
}
