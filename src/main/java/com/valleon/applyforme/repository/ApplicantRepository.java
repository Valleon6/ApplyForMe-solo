package com.valleon.applyforme.repository;

import com.valleon.applyforme.model.domain.Member;

public interface ApplicantRepository {
    Member getMyDetailsById(Long id);
}
