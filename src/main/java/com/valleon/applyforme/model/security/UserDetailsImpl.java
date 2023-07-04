package com.valleon.applyforme.model.security;

import com.valleon.applyforme.model.domain.Member;
import com.valleon.applyforme.model.domain.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String emailAddress;

    private String password;

    private Set<Role> plainRoles;

    private Collection<? extends GrantedAuthority> authorities;

    private String fullName;

    private String avatar;

    private String phoneNumber;

    private String displayName;

    public UserDetailsImpl(Long id, String emailAddress, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.emailAddress = emailAddress;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build (Member member){
        List<GrantedAuthority> authorities = member.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE".concat(role.getCode())))
                .collect(Collectors.toList());
        var details = new UserDetailsImpl(member.getId(), member.getEmailAddress(), member.getPassword(), authorities);
        details.setPlainRoles(member.getRoles());
        details.setFullName((member.getFirstName() + " " + member.getLastName()));
        details.setAvatar(member.getAvatar());
        details.setPhoneNumber(member.getPhoneNumber());
        details.setDisplayName(member.getUsername());
        return details;
    }

//    public static UserDetailsImpl buildFromTokenDetails(JwtTokenDetails tokenDetails){
//        List<GrantedAuthority> authorities = Arrays.asList(tokenDetails.getRoles())
//                .stream()
//                .map(role -> new SimpleGrantedAuthority("ROLE_".concat(role)))
//                .collect(Collectors.toList());
//    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
