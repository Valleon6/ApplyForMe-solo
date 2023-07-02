package com.valleon.applyforme.utilities;

import com.valleon.applyforme.ServiceImpl.UserDetailsServiceImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class ApplyForMeUtil {
    private final UserDetailsServiceImpl userDetailsService;

    public ApplyForMeUtil(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public static Pageable createPageable(int pageNo, int pageSize, String sortBy, String sortDir){
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        return PageRequest.of(pageNo,pageSize,sort);
    }
}
