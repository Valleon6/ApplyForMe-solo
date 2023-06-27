package com.valleon.applyforme.repository;


import com.valleon.applyforme.model.Member;

public interface MemberRepository {
    Member fetchOne(Long id);
}
