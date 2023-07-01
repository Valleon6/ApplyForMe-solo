package com.valleon.applyforme.services;

import com.valleon.applyforme.model.domain.Member;
import com.valleon.applyforme.model.dto.SignUpDto;

public interface MemberService {
    Member findOne(Long id);

    Member save(SignUpDto body);



}
