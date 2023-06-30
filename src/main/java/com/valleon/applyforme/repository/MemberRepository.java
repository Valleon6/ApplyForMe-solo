package com.valleon.applyforme.repository;


import com.valleon.applyforme.model.domain.Member;

public interface MemberRepository {
    Member fetchOne(Long id);

    Member saveOne(Member body);

    Member getOne(Long id);
}
