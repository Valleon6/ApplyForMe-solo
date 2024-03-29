package com.valleon.applyforme.services;

import com.valleon.applyforme.model.domain.Professional;
import com.valleon.applyforme.model.domain.ProfessionalProfile;
import com.valleon.applyforme.model.dto.professional.ProfessionalDto;
import com.valleon.applyforme.model.response.JobDescriptionResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProfessionalService {

    List<Professional> findAll(Integer pageOffset);

    Professional findOne (Long id);

    boolean updateProfile(ProfessionalDto professionalDto, Long id);

    Page<Professional> retrieveAllProfessionals(int pageNo, int pageSize);

    List<ProfessionalProfile> findAllJobProfiles(Long id);

//    List<JobSummaryResponse> retrieveProfessionalSubmissions(Long id);

    JobDescriptionResponse viewJobDescription (Long professionalId, Long SubmissionId);

    List<JobDescriptionResponse> viewAllJobDescriptions(Long professionalId);



}
