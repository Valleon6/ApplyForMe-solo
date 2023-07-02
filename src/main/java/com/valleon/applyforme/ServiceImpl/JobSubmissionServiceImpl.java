package com.valleon.applyforme.ServiceImpl;

import com.valleon.applyforme.exceptions.ApplierNotFoundException;
import com.valleon.applyforme.model.domain.Applier;
import com.valleon.applyforme.model.domain.Submission;
import com.valleon.applyforme.model.dto.submission.SubmissionDto;
import com.valleon.applyforme.model.response.ApplyForMeResponse;
import com.valleon.applyforme.model.response.SubmissionEntriesResponse;
import com.valleon.applyforme.repository.ApplierRepository;
import com.valleon.applyforme.repository.jpa.ApplierJpaRepository;
import com.valleon.applyforme.repository.jpa.SubmissionJpaRepository;
import com.valleon.applyforme.services.JobSubmissionService;
import com.valleon.applyforme.utilities.ApplyForMeUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class JobSubmissionServiceImpl implements JobSubmissionService {
    private final SubmissionJpaRepository submissionJpaRepository;
    private final ApplierRepository applierRepository;

    private final ApplyForMeUtil applyForMeUtil;

    @Autowired
    private ModelMapper modelMapper;

    private final ApplierJpaRepository applierJpaRepository;

    public JobSubmissionServiceImpl(SubmissionJpaRepository submissionJpaRepository, ApplierRepository applierRepository, ApplyForMeUtil applyForMeUtil, ApplierJpaRepository applierJpaRepository) {
        this.submissionJpaRepository = submissionJpaRepository;
        this.applierRepository = applierRepository;
        this.applyForMeUtil = applyForMeUtil;
        this.applierJpaRepository = applierJpaRepository;
    }

    @Override
    public Long countAllApplierSubmissions(Long applierId) {
        Applier applier = applierRepository.getOne(applierId);
        if (applier == null) {
            throw new ApplierNotFoundException(applierId);
        }
        return submissionJpaRepository.countSubmissionByApplierId(applierId);
    }

    @Override
    public ApplyForMeResponse getAllJobSubmissions(int pageNo, int pageSize, String sortDir, String sortBy) {
        Page<Submission> submissions = submissionJpaRepository.findAll(ApplyForMeUtil.createPageable(pageNo, pageSize, sortDir, sortBy));
        return getJobSubmissionResponse(submissions);
    }

    @Override
    public ApplyForMeResponse filterJobSubmission(int pageNo, int pageSize, String sortDir, String sortBy, String q) {
        Page<Submission> submissions = submissionJpaRepository.findSubmissionsBySearch(ApplyForMeUtil.createPageable(pageNo, pageSize, sortBy, sortDir), q);
        return getJobSubmissionResponse(submissions);
    }

    public ApplyForMeResponse getJobSubmissionResponse(Page<Submission> submission) {
        Collection<SubmissionDto> submissions = submission
                .getContent()
                .stream()
                .map(submission1 -> {
                    return modelMapper.map(submission1, SubmissionDto.class);
                })
                .collect(Collectors.toList());

        SubmissionEntriesResponse submissionEntriesResponse = new SubmissionEntriesResponse();
        submissionEntriesResponse.setContent(submission.getContent());
        submissionEntriesResponse.setLast(submission.isLast());
        submissionEntriesResponse.setPageNo(submission.getTotalPages());
        submissionEntriesResponse.setTotalPages(submission.getTotalPages());
        submissionEntriesResponse.setPageSize(submission.getSize());
        submissionEntriesResponse.setTotalElements(submission.getTotalElements());
        return submissionEntriesResponse;
    }
}
