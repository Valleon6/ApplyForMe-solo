package com.valleon.applyforme.repository.jpa;

import com.valleon.applyforme.model.domain.Applier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplierJpaRepository extends JpaRepository<Applier, Long> {

}
