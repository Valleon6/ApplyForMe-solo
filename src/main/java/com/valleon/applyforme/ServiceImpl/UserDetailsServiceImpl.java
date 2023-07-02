package com.valleon.applyforme.ServiceImpl;

import com.valleon.applyforme.exceptions.MemberNotFoundException;
import com.valleon.applyforme.model.domain.Member;
import com.valleon.applyforme.repository.jpa.MemberJpaRepository;
import com.valleon.applyforme.security.UserDetailsImpl;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

    MemberJpaRepository memberJpaRepository;

    public UserDetailsServiceImpl(MemberJpaRepository memberJpaRepository) {
        this.memberJpaRepository = memberJpaRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        Member member = memberJpaRepository.findByEmailAddress(emailAddress);
        if(member == null){
            throw new MemberNotFoundException(emailAddress);
        }
        return UserDetailsImpl.build(member);
    }
}
