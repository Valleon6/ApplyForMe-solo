package com.valleon.applyforme.repository;

import com.valleon.applyforme.model.domain.Professional;

public interface ProfessionalRepository {

    Professional saveOne(Professional body);

    Professional getOne (Long id);
}
