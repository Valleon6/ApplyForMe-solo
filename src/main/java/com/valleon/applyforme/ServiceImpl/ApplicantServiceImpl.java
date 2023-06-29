package com.valleon.applyforme.ServiceImpl;

import com.valleon.applyforme.exceptions.ProfessionalNotFoundException;
import com.valleon.applyforme.model.domain.Member;
import com.valleon.applyforme.model.domain.Professional;
import com.valleon.applyforme.model.dto.applicant.ApplicantDto;
import com.valleon.applyforme.repository.ApplicantRepository;
import com.valleon.applyforme.repository.ProfessionalRepository;
import com.valleon.applyforme.repository.jpa.ProfessionalJpaRepository;
import com.valleon.applyforme.services.ApplicantService;

import javax.transaction.Transactional;

public class ApplicantServiceImpl implements ApplicantService {
    private ApplicantRepository applicantRepository;

    private ProfessionalRepository professionalRepository;

    private ProfessionalJpaRepository professionalJpaRepository;
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
}
