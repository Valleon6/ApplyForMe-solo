package com.valleon.applyforme.repository.jpa;

import com.valleon.applyforme.model.domain.Professional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessionalJpaRepository extends JpaRepository<Professional, Long > {

}
