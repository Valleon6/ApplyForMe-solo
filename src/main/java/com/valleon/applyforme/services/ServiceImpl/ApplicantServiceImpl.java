package com.valleon.applyforme.services.ServiceImpl;

import com.valleon.applyforme.model.exceptions.ProfessionalNotFoundException;
import com.valleon.applyforme.model.domain.Member;
import com.valleon.applyforme.model.domain.Professional;
import com.valleon.applyforme.model.domain.Submission;
import com.valleon.applyforme.model.dto.applicant.ApplicantDto;
import com.valleon.applyforme.model.dto.applicant.ApplicantResponse;
import com.valleon.applyforme.model.dto.submission.SubmissionDto;
import com.valleon.applyforme.model.response.ApplicantJobResponse;
import com.valleon.applyforme.model.response.ApplyForMeResponse;
import com.valleon.applyforme.repository.ApplicantRepository;
import com.valleon.applyforme.repository.MemberRepository;
import com.valleon.applyforme.repository.ProfessionalRepository;
import com.valleon.applyforme.repository.jpa.JobSubmissionJpaRepository;
import com.valleon.applyforme.repository.jpa.ProfessionalJpaRepository;
import com.valleon.applyforme.services.ApplicantService;
import com.valleon.applyforme.utilities.ApplyForMeUtil;
import com.valleon.applyforme.utilities.CurrentUserUtil;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ApplicantServiceImpl implements ApplicantService {
    private ApplicantRepository applicantRepository;
    private ProfessionalRepository professionalRepository;
    private ProfessionalJpaRepository professionalJpaRepository;
    private MemberRepository memberRepository;
    private JobSubmissionJpaRepository jobSubmissionJpaRepository;
    private ModelMapper modelMapper;

    @Override
    public Member getDetails(Long id) {
        return applicantRepository.getMyDetailsById(id);
    }

    @Override
    @Transactional
    public Professional update(Long id, ApplicantDto applicantDto) {
        Professional professional = professionalJpaRepository.findById(id)
                .orElseThrow(() -> new ProfessionalNotFoundException("" + "Professional not found"));

        if (applicantDto.getUserName() != null) {
            professional.getMember().setLastName(applicantDto.getLastName());
        }
        if (applicantDto.getFirstName() != null) {
            professional.getMember().setFirstName(applicantDto.getFirstName());
        }
        if (applicantDto.getUserName() != null) {
            professional.getMember().setUsername(applicantDto.getUserName());
        }
        if (applicantDto.getPhoneNumber() != null) {
            professional.getMember().setPhoneNumber(applicantDto.getPhoneNumber());
        }
        if (applicantDto.getEmailAddress() != null) {
            professional.getMember().setEmailAddress(applicantDto.getEmailAddress());
        }
        return professionalRepository.saveOne(professional);
    }

    @Override
    public ApplyForMeResponse getApplicationList(int pageNo, int pageSize, String sortBy, String sortDir) {
        Pageable pageable = ApplyForMeUtil.createPageable(pageNo, pageSize, sortBy, sortDir);
        var result = jobSubmissionJpaRepository.findAll(pageable);

        Collection<ApplicantResponse> applicantResponses = result.getContent().stream().map(x -> {
                    Random random = new Random();

                    int randomNumber = random.nextInt(400 - 200) + 200;
                    return ApplicantResponse.builder()
                            .id(x.getId())
                            .date(x.getCreatedOn())
                            .jobLocation(x.getJobLocation())
                            .jobTitle(x.getJobTitle())
                            .jobType(x.getJobLocationType().getValue())
                            .jobCompany(x.getJobCompany())
                            .salaryRange("$" + randomNumber)
                            .build();
                }
        ).collect(Collectors.toList());

        ApplyForMeResponse applyForMeResponse = new ApplyForMeResponse();
        applyForMeResponse.setPageSize(result.getSize());
        applyForMeResponse.setTotalElements(result.getTotalElements());
        applyForMeResponse.setPageNo(result.getNumber());
        applyForMeResponse.setContent(result.getContent());
        applyForMeResponse.setTotalPages(result.getTotalPages());
        applyForMeResponse.setLast(result.isLast());

        return applyForMeResponse;
    }

    @Override
    public ApplyForMeResponse getApplicationEntries(int pageNo, int pageSize, String sortBy, String sortDir) {
        Long currentUser = CurrentUserUtil.getCurrentUser().getId();
        Member member = memberRepository.fetchOne(currentUser);
        Professional professional = professionalJpaRepository.getOne(member.getId());
        Long professional_id = professional.getId();

        Pageable pageable = ApplyForMeUtil.createPageable(pageNo, pageSize, sortBy, sortDir);

        Page<Submission> submissions = jobSubmissionJpaRepository.getSubmission(professional_id, pageable);

        return getApplicantJobResponses(submissions);

    }

    private ApplyForMeResponse getApplicantJobResponses(Page<Submission> submissions) {
        Collection<SubmissionDto> submissionDtoCollection = submissions
                .getContent()
                .stream()
                .map(x -> {
                    return modelMapper.map(x, SubmissionDto.class);
                }).collect(Collectors.toList());

        ApplyForMeResponse applyForMeResponse1 = new ApplyForMeResponse();

        applyForMeResponse1.setContent(getApplicantJobResponses(submissions.getContent()));
        applyForMeResponse1.setPageNo(submissions.getNumber());
        applyForMeResponse1.setPageSize(submissions.getSize());
        applyForMeResponse1.setTotalElements(submissions.getTotalElements());
        applyForMeResponse1.setTotalPages(submissions.getTotalPages());
        applyForMeResponse1.setLast(submissions.isLast());
        return applyForMeResponse1;
    }

    private List<ApplicantJobResponse> getApplicantJobResponses(Collection<Submission> submissions) {
        List<ApplicantJobResponse> applicantJobResponses = new ArrayList<>();

        submissions.forEach(submission -> {
            Professional professionalObj = submission.getProfessional();
            Member member = professionalObj.getMember();
            Professional professional = professionalJpaRepository.getProfessional(member.getId());

            ApplicantJobResponse jobResponse = ApplicantJobResponse.builder()
                    .id(submission.getId())
                    .professional(submission.getProfessional())
                    .applier(submission.getApplier())
                    .jobTitle(submission.getJobTitle())
                    .jobLocationType(submission.getJobLocationType())
                    .summary(submission.getSummary())
                    .otherComment(submission.getOtherComment())
                    .professionalProfileId(submission.getProfessionalProfile())
                    .createdOn(submission.getCreatedOn())
                    .updatedOn(submission.getUpdatedOn())
                    .build();
            applicantJobResponses.add(jobResponse);
        });

        return applicantJobResponses;
    }
}
