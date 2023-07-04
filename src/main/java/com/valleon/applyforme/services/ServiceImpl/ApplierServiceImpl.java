package com.valleon.applyforme.services.ServiceImpl;

import com.valleon.applyforme.model.domain.Applier;
import com.valleon.applyforme.model.dto.ApplierDto;
import com.valleon.applyforme.repository.jpa.ApplierJpaRepository;
import com.valleon.applyforme.services.ApplierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplierServiceImpl implements ApplierService {

   @Autowired
   private ApplierJpaRepository applierJpaRepository;

  @Autowired
  private  ModelMapper modelMapper;



    @Override
    @Transactional(readOnly = true)
    public List<ApplierDto> getAllAppliers() {
        List<Applier> applierList = applierJpaRepository.findAll();

        List<ApplierDto> applierDtoList = applierList.stream()
                .map(applier -> modelMapper.map(applier, ApplierDto.class))
            .collect(Collectors.toList());

        return applierDtoList;

    }
}
