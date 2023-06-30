package com.valleon.applyforme.ServiceImpl;

import com.valleon.applyforme.constants.PagingConstants;
import com.valleon.applyforme.model.domain.Professional;
import com.valleon.applyforme.repository.ProfessionalRepository;
import com.valleon.applyforme.repository.jpa.ProfessionalJpaRepository;
import com.valleon.applyforme.services.ProfessionalService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class ProfessionalServiceImpl implements ProfessionalService {
    private ProfessionalJpaRepository professionalJpaRepository;
    private ProfessionalRepository professionalRepository;

    @Override
    public List<Professional> findAll(Integer pageOffset) {

        return professionalRepository.getAll(pageOffset);

    }
}
