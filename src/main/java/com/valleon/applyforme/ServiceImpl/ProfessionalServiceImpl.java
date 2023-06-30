package com.valleon.applyforme.ServiceImpl;

import com.valleon.applyforme.exceptions.ProfessionalNotFoundException;
import com.valleon.applyforme.model.domain.Member;
import com.valleon.applyforme.model.domain.Professional;
import com.valleon.applyforme.repository.MemberRepository;
import com.valleon.applyforme.repository.ProfessionalRepository;
import com.valleon.applyforme.repository.jpa.ProfessionalJpaRepository;
import com.valleon.applyforme.services.ProfessionalService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProfessionalServiceImpl implements ProfessionalService {
    private ProfessionalJpaRepository professionalJpaRepository;
    private ProfessionalRepository professionalRepository;
    private MemberRepository memberRepository;

    @Override
    public List<Professional> findAll(Integer pageOffset) {

        return professionalRepository.getAll(pageOffset);

    }

    @Override
    @Transactional()
    public Professional findOne(Long id) {
        Member member = memberRepository.getOne(id);
        if(member == null){
            throw new ProfessionalNotFoundException(id);
        }
        Professional professional = professionalRepository.getOne(member.getId());
//        professional.setSubmissions(null);
//        professional.setProfessionalProfiles(null);
        return professional;
    }
}
