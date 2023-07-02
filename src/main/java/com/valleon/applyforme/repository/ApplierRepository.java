package com.valleon.applyforme.repository;

import com.valleon.applyforme.model.domain.Applier;

public interface ApplierRepository {

    Applier getOne(Long ApplierId);
}
