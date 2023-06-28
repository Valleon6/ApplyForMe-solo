package com.valleon.applyforme.ServiceImpl;

import com.valleon.applyforme.enums.RoleType;
import com.valleon.applyforme.exceptions.EmailAlreadyExistException;
import com.valleon.applyforme.exceptions.MemberNotFoundException;
import com.valleon.applyforme.exceptions.RoleNotFoundException;
import com.valleon.applyforme.model.Member;
import com.valleon.applyforme.model.Professional;
import com.valleon.applyforme.model.Role;
import com.valleon.applyforme.model.dto.SignUpDto;
import com.valleon.applyforme.repository.MemberRepository;
import com.valleon.applyforme.repository.MemberSecretCodeRepository;
import com.valleon.applyforme.repository.ProfessionalRepository;
import com.valleon.applyforme.repository.jpa.MemberJpaRepository;
import com.valleon.applyforme.repository.jpa.RoleJpaRepository;
import com.valleon.applyforme.services.MemberService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    private MemberJpaRepository memberJpaRepository;
    private MemberRepository memberRepository;

    private RoleJpaRepository roleJpaRepository;

    private ProfessionalRepository professionalRepository;
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MemberSecretCodeRepository memberSecretCodeRepository;

    @Override
    @Transactional
    public Member findOne(Long id) {
        Member member = memberRepository.fetchOne(id);
        if (member == null) {
            throw new MemberNotFoundException(id);
        }
        return member;
    }

    @Override
    @Transactional
    public Member save(SignUpDto body) {
        boolean existingMember = memberJpaRepository.existsByEmailAddress(body.getEmailAddress())
        if (existingMember) {
            throw new EmailAlreadyExistException();
        }
        Optional<Role> existingRole = roleJpaRepository.findByCode(RoleType.PROFESSIONAL.getValue());

        if (existingRole.isEmpty()) {
            throw new RoleNotFoundException(RoleType.PROFESSIONAL.getValue());
        }
        Member member = new Member();
        member = modelMapper.map(body, Member.class);
        member.addRole(existingRole.get());
        member.setPassword(passwordEncoder.encode(body.getPassword()));

        memberRepository.saveOne(member);

        Professional professional = Professional.builder()
                .member(member)
                .submissions(null)
                .professionalProfiles(null)
                .build();

        professionalRepository.saveOne(professional);
        String generatedSecretCode = generateSignUpCode();
        memberSecretCodeRepository.saveSecretCode(generatedSecretCode);
        return member;
    }

    /**
     * This method helps to generate sign-up verification and was used in the method above
     * to save the sign-up verification code into the DB as shown below.
     * String generatedSecretCode = generateSignUpCode();
          memberSecretCodeRepository.saveSecretCode(generatedSecretCode);
     */
    private String generateSignUpCode() {
        int[] numbers = new int[4];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = (int) (Math.random() * 9);
        }
        String code = "" + numbers[0] + numbers[1] + numbers[2] + numbers[3] + "";
        return code;
    }


}
