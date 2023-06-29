package com.valleon.applyforme.ServiceImpl;

import com.valleon.applyforme.exceptions.ProfessionalNotFoundException;
import com.valleon.applyforme.model.domain.Member;
import com.valleon.applyforme.model.domain.Professional;
import com.valleon.applyforme.model.dto.applicant.ApplicantDto;
import com.valleon.applyforme.model.dto.applicant.ApplicantResponse;
import com.valleon.applyforme.model.response.ApplyForMeResponse;
import com.valleon.applyforme.repository.ApplicantRepository;
import com.valleon.applyforme.repository.ProfessionalRepository;
import com.valleon.applyforme.repository.jpa.JobSubmissionJpaRepository;
import com.valleon.applyforme.repository.jpa.ProfessionalJpaRepository;
import com.valleon.applyforme.services.ApplicantService;
import com.valleon.applyforme.utilities.ApplyForMeUtil;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Random;
import java.util.stream.Collectors;

public class ApplicantServiceImpl implements ApplicantService {
    private ApplicantRepository applicantRepository;

    private ProfessionalRepository professionalRepository;

    private ProfessionalJpaRepository professionalJpaRepository;

    private JobSubmissionJpaRepository jobSubmissionJpaRepository;
    @Override
    public Member getDetails(Long id) {
        return applicantRepository.getMyDetailsById(id);
    }

    @Override
    @Transactional
    public Professional update(Long id, ApplicantDto applicantDto) {
       Professional professional = professionalJpaRepository.findById(id)
               .orElseThrow(()-> new ProfessionalNotFoundException("" + "Professional not found"));

       if(applicantDto.getUserName() != null){
           professional.getMember().setLastName(applicantDto.getLastName());
       }
       if(applicantDto.getFirstName() != null){
           professional.getMember().setFirstName(applicantDto.getFirstName());
       }
       if(applicantDto.getUserName() != null){
           professional.getMember().setUsername(applicantDto.getUserName());
       }
       if(applicantDto.getPhoneNumber() != null){
           professional.getMember().setPhoneNumber(applicantDto.getPhoneNumber());
       }
       if(applicantDto.getEmailAddress() != null){
           professional.getMember().setEmailAddress(applicantDto.getEmailAddress());
       }
        return professionalRepository.saveOne(professional);
    }

    @Override
    public ApplyForMeResponse getApplicationList(int pageNo, int pageSize, String sortBy, String sortDir) {
        Pageable pageable = ApplyForMeUtil.createPageable(pageNo, pageSize,sortBy, sortDir);
        var result = jobSubmissionJpaRepository.findAll(pageable);

        Collection<ApplicantResponse> applicantResponses = result.getContent().stream().map(x ->{
            Random random = new Random();

            int randomNumber = random.nextInt(400-200)+ 200;
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
}
