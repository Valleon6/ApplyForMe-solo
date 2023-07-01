package com.valleon.applyforme.ServiceImpl;

import com.valleon.applyforme.exceptions.ProfessionalNotFoundException;
import com.valleon.applyforme.model.domain.Member;
import com.valleon.applyforme.model.domain.Professional;
import com.valleon.applyforme.model.domain.ProfessionalProfile;
import com.valleon.applyforme.model.domain.Submission;
import com.valleon.applyforme.model.dto.professional.ProfessionalDto;
import com.valleon.applyforme.model.response.JobDescriptionResponse;
import com.valleon.applyforme.repository.MemberRepository;
import com.valleon.applyforme.repository.ProfessionalRepository;
import com.valleon.applyforme.repository.jpa.ProfessionalJpaRepository;
import com.valleon.applyforme.repository.jpa.SubmissionJpaRepository;
import com.valleon.applyforme.services.ProfessionalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessionalServiceImpl implements ProfessionalService {
    private ProfessionalJpaRepository professionalJpaRepository;
    private ProfessionalRepository professionalRepository;
    private MemberRepository memberRepository;

    private SubmissionJpaRepository submissionJpaRepository;

    @Override
    public List<Professional> findAll(Integer pageOffset) {

        return professionalRepository.getAll(pageOffset);

    }

    @Override
    @Transactional()
    public Professional findOne(Long id) {
        Member member = memberRepository.getOne(id);
        if (member == null) {
            throw new ProfessionalNotFoundException(id);
        }
        Professional professional = professionalRepository.getOne(member.getId());
//        professional.setSubmissions(null);
//        professional.setProfessionalProfiles(null);
        return professional;
    }

    @Override
    public boolean updateProfile(ProfessionalDto professionalDto, Long id) {

        Professional professional = findOne(id);
        if (professional == null) {
            throw new ProfessionalNotFoundException(id);
        }

        System.out.println(
                "Before => professional.isAvailableForInterview(): " + professional.isAvailableForInterview()
        );
        professional.setAvailableForInterview(professionalDto.isAvailableForInterview());

        System.out.println(
                "After =>professional.isAvailableForInterview(): " + professionalDto.isAvailableForInterview()
        );
        if (professionalDto.getLinkedInLink() != null) {
            professional.setLinkedinLink(professional.getLinkedinLink());
        }
        if (professionalDto.getFacebook() != null) {
            professional.setFacebookLink(professional.getFacebookLink());
        }
        if (professionalDto.getInstagram() != null) {
            professional.setInstagramLink(professionalDto.getInstagram());
        }
        if (professionalDto.getOtherLink() != null) {
            professional.setOtherLink(professionalDto.getOtherLink());
        }
        if (professionalDto.getOtherLink2() != null) {
            professional.setOtherLink2(professionalDto.getOtherLink2());
        }
        if (professionalDto.getHobbies() != null) {
            professional.setHobbies(professionalDto.getHobbies());
        }
        if (professionalDto.getOtherLink3() != null) {
            professional.setOtherLink3(professionalDto.getOtherLink3());
        }

        return true;
    }

    @Override
    public Page<Professional> retrieveAllProfessionals(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Professional> professionalsPage = professionalJpaRepository.findAll(pageable);
        if(professionalsPage.isEmpty()){
            throw new ProfessionalNotFoundException(professionalsPage.getTotalElements());
        }
        return professionalsPage;

    }

    @Override
    public List<ProfessionalProfile> findAllJobProfiles(Long id) {
        List<ProfessionalProfile> professionalProfileList = professionalJpaRepository.getJobProfile(id);

//        professionalProfileList.forEach(profile -> {
//            profile.getProfessional().setSubmissions(null);
//            profile.getProfessional().setProfessionalProfiles(null);
//        });
        return professionalProfileList;
    }

//    @Override
//    public List<JobSummaryResponse> retrieveProfessionalSubmissions(Long id) {
//        UserDetailsImpl currentUser = CurrentUserUtil.getCurrentUser();
//
//        return null;
//    }

    @Override
    public JobDescriptionResponse viewJobDescription(Long professionalId, Long submissionId) {
        Optional<Professional> professional = professionalJpaRepository.findById(professionalId);
        if(professional.isEmpty()){
           throw new ProfessionalNotFoundException(professionalId);
        }

        List<Submission> submissionList = submissionJpaRepository.findAllByProfessionalId(professionalId);
        for(Submission submission : submissionList ){
            if(submission.getId().equals(submissionId)){
                return JobDescriptionResponse.builder()
                        .jobLocation(submission.getJobLocation())
                        .jobTitle(submission.getJobTitle())
                        .jobLocationType(submission.getJobLocationType().getValue())
                        .jobCompany(submission.getJobCompany())
                        .jobLink(submission.getJobLink())
                        .otherComment(submission.getOtherComment())
                        .createdOn(submission.getCreatedOn())
                        .updatedOn(submission.getUpdatedOn())
                        .applier(submission.getApplier())
                        .jobSummary(submission.getSummary())
                        .build();
            }
        }

        return null;

    }

    @Override
    public List<JobDescriptionResponse> viewAllJobDescriptions(Long professionalId) {
        Optional<Professional> professional = professionalJpaRepository.findById(professionalId);
        if(professional.isEmpty()){
            throw new ProfessionalNotFoundException(professionalId);
        }

        List<Submission> submission1 = submissionJpaRepository.findAllByProfessionalId(professionalId);
        List<JobDescriptionResponse> descriptionResponseslist = new ArrayList<>();

        for(Submission submission : submission1){
          JobDescriptionResponse jobDescriptionResponse =  JobDescriptionResponse.builder()
                    .jobLocation(submission.getJobLocation())
                    .jobTitle(submission.getJobTitle())
                    .jobLocationType(submission.getJobLocationType().getValue())
                    .jobCompany(submission.getJobCompany())
                    .jobLink(submission.getJobLink())
                    .otherComment(submission.getOtherComment())
                    .createdOn(submission.getCreatedOn())
                    .updatedOn(submission.getUpdatedOn())
                    .applier(submission.getApplier())
                    .jobSummary(submission.getSummary())
                    .build();

          descriptionResponseslist.add(jobDescriptionResponse);
        }
        return descriptionResponseslist;
    }
}
