package com.valleon.applyforme.repository;

import com.valleon.applyforme.model.domain.Professional;

import java.util.List;

public interface ProfessionalRepository {

    Professional saveOne(Professional body);

    Professional getOne (Long id);

    List<Professional> getAll(Integer pageOffset);
}
