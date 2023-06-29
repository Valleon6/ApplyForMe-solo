package com.valleon.applyforme.utilities;

import com.valleon.applyforme.security.UserDetailsImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class ApplyForMeUtil {
    private UserDetailsImpl userDetailsService;

    public ApplyForMeUtil(UserDetailsImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public static Pageable createPageable(int pageNo, int pageSize, String sortBy, String sortDir){
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        return PageRequest.of(pageNo,pageSize,sort);
    }
}
