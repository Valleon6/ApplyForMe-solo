package com.valleon.applyforme.repositoryImpl;

import com.valleon.applyforme.model.domain.MemberSecretCode;
import com.valleon.applyforme.repository.MemberSecretCodeRepository;

import javax.persistence.EntityManager;


public class MemberSecretCodeRepositoryImpl implements MemberSecretCodeRepository {
    private EntityManager entityManager;
    @Override
    public void saveSecretCode(String secretCode) {
        MemberSecretCode memberSecretCode = new MemberSecretCode();
        memberSecretCode.setSignUpVerificationCode(secretCode);
        entityManager.persist(memberSecretCode);
    }
}
