package com.valleon.applyforme.repository.repositoryImpl;

import com.valleon.applyforme.model.dto.submission.ApplierSubmissionDto;
import com.valleon.applyforme.repository.JobSubmissionRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class JobSubmissionRepositoryImpl implements JobSubmissionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ApplierSubmissionDto> getSubmissionDetails(Long applierId) {
        String queryString = "select s.job_title, s.job_link, s.job_location, s.job_company,"
                + "s.job_locationType, s.summary, s.other.comment,"
                + "s.created_on from Submission s where s.applier_id = :applierId";
        Query query = entityManager.createNativeQuery(queryString);
        query.setParameter("applierId", applierId);
        List submissionList = query.getResultList();
        System.out.println("submissionList.size(): " + submissionList.size());

        return submissionList;
    }
}
