package com.valleon.applyforme.services.ServiceImpl;

import com.valleon.applyforme.model.exceptions.ApplierNotFoundException;
import com.valleon.applyforme.model.domain.Applier;
import com.valleon.applyforme.model.domain.Submission;
import com.valleon.applyforme.model.dto.submission.ApplierSubmissionDto;
import com.valleon.applyforme.model.dto.submission.SubmissionDto;
import com.valleon.applyforme.model.response.ApplyForMeResponse;
import com.valleon.applyforme.model.response.SubmissionEntriesResponse;
import com.valleon.applyforme.repository.ApplierRepository;
import com.valleon.applyforme.repository.JobSubmissionRepository;
import com.valleon.applyforme.repository.jpa.ApplierJpaRepository;
import com.valleon.applyforme.repository.jpa.JobSubmissionJpaRepository;
import com.valleon.applyforme.repository.jpa.SubmissionJpaRepository;
import com.valleon.applyforme.services.JobSubmissionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.valleon.applyforme.utilities.ApplyForMeUtil.createPageable;

@Service
public class JobSubmissionServiceImpl implements JobSubmissionService {
    private final SubmissionJpaRepository submissionJpaRepository;
    private final ApplierRepository applierRepository;

    private final JobSubmissionRepository jobSubmissionRepository;
    private final JobSubmissionJpaRepository jobSubmissionJpaRepository;

    @Autowired
    private ModelMapper modelMapper;

    private final ApplierJpaRepository applierJpaRepository;

    public JobSubmissionServiceImpl(SubmissionJpaRepository submissionJpaRepository, ApplierRepository applierRepository, JobSubmissionRepository jobSubmissionRepository, JobSubmissionJpaRepository jobSubmissionJpaRepository, ApplierJpaRepository applierJpaRepository) {
        this.submissionJpaRepository = submissionJpaRepository;
        this.applierRepository = applierRepository;
        this.jobSubmissionRepository = jobSubmissionRepository;
        this.jobSubmissionJpaRepository = jobSubmissionJpaRepository;
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
        Page<Submission> submissions = submissionJpaRepository.findAll(createPageable(pageNo, pageSize, sortDir, sortBy));
        return getJobSubmissionResponse(submissions);
    }

    @Override
    public ApplyForMeResponse filterJobSubmission(int pageNo, int pageSize, String sortDir, String sortBy, String q) {
        Page<Submission> submissions = submissionJpaRepository.findSubmissionsBySearch(createPageable(pageNo, pageSize, sortBy, sortDir), q);
        return getJobSubmissionResponse(submissions);
    }


    @Override
    public List<ApplierSubmissionDto> getApplierSubmissionDetails(Long applierId) {
        List<ApplierSubmissionDto> applierSubmissionDtos = jobSubmissionRepository.getSubmissionDetails(applierId);
       return applierSubmissionDtos;
    }

    @Override
    @Transactional(readOnly = true)
    public ApplyForMeResponse getEntries(int pageNo, int pageSize, String sortDir, String sortBy, String q, Date from, Date to) {
      Page<Submission> submissions = null;
        if(from != null && to != null  && q != null && q.trim() != ""){
            submissions = jobSubmissionJpaRepository.getEntries(from,to,q, createPageable(pageNo, pageSize, sortBy, sortDir));
        }
        else if (from != null && to != null) {
            submissions = jobSubmissionJpaRepository.getEntries(from,to, createPageable(pageNo, pageSize, sortBy, sortDir));
        }
        if(q != null && q.trim() != ""){
            submissions =jobSubmissionJpaRepository.getEntries(q,createPageable(pageNo, pageSize, sortBy, sortDir));
        }
        else {
            submissions =jobSubmissionJpaRepository.getEntries(createPageable(pageNo, pageSize, sortBy, sortDir));

        }

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
