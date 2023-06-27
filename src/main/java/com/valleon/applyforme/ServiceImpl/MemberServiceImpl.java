package com.valleon.applyforme.ServiceImpl;

import com.valleon.applyforme.exceptions.MemberNotFoundException;
import com.valleon.applyforme.model.Member;
import com.valleon.applyforme.model.dto.SignUpDto;
import com.valleon.applyforme.repository.jpa.MemberJpaRepository;
import com.valleon.applyforme.services.MemberService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MemberServiceImpl implements MemberService {

    private MemberJpaRepository memberJpaRepository;

    @Override
    @Transactional
    public Member findOne(Long id) {
        Member member = memberJpaRepository.findMemberById(id);
        if(member == null){
            throw new MemberNotFoundException(id);
        }
        return member;
    }

   
}
