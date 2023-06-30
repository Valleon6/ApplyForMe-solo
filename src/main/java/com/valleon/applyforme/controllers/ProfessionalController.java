package com.valleon.applyforme.controllers;

import com.valleon.applyforme.constants.PagingConstants;
import com.valleon.applyforme.model.domain.Professional;
import com.valleon.applyforme.model.domain.ProfessionalProfile;
import com.valleon.applyforme.model.dto.professional.ProfessionalDto;
import com.valleon.applyforme.security.UserDetailsImpl;
import com.valleon.applyforme.services.ProfessionalService;
import com.valleon.applyforme.utilities.CurrentUserUtil;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "professional", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ProfessionalController {

    public ProfessionalController(ProfessionalService professionalService) {
        this.professionalService = professionalService;
    }

    private final ProfessionalService professionalService;

    @GetMapping(path = "/detail")
    public Professional findOne() {
        UserDetailsImpl currentUser = CurrentUserUtil.getCurrentUser();
        return professionalService.findOne(currentUser.getId());
    }


    @GetMapping("/entries")
    private List<Professional> findAll(
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer pageOffset) {
        return professionalService.findAll(pageOffset);
    }

    @PutMapping("/update")
    public Boolean updateProfile (@RequestBody ProfessionalDto professionalDto, Long professional_id){
        Long currentUser = CurrentUserUtil.getCurrentUser().getId();

        return professionalService.updateProfile(professionalDto,currentUser);
    }

    @GetMapping("/allProfessionals/{pageNo}/{pageSize}")
    public Page<Professional> retrieveAllProfessionals(@PathVariable int pageNo, @PathVariable int pageSize){
        return professionalService.retrieveAllProfessionals(pageNo,pageSize);
    }

    @GetMapping("/profiles")
    public List<ProfessionalProfile> getAllJobProfiles(@RequestParam(value = "pageNo", required = false, defaultValue = PagingConstants.DEFAULT_PAGE_NUMBER) int pageNo,
                                                        @RequestParam(value = "pageSize", required = false, defaultValue = PagingConstants.DEFAULT_PAGE_SIZE) int pageSize,
                                                        @RequestParam(value = "sortBy", required = false, defaultValue = PagingConstants.DEFAULT_SORT_BY) String sortBy,
                                                        @RequestParam(value = "sortDir", required = false, defaultValue = PagingConstants.DEFAULT_SORT_DIRECTION) String sortDir){
//        Long currentUser = CurrentUserUtil.getCurrentUser().getId();
        UserDetailsImpl currentUser = CurrentUserUtil.getCurrentUser();

        return professionalService.findAllJobProfiles(currentUser.getId());

    }

}
