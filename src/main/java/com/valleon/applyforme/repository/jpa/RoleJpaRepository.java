package com.valleon.applyforme.repository.jpa;

import com.valleon.applyforme.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleJpaRepository extends JpaRepository<Role, Long> {

 Optional<Role> findByTitleAndCode(String title, String code);

 Optional<Role> findByCode(String Code);
}
