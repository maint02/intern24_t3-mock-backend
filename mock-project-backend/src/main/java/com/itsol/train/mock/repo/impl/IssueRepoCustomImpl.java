package com.itsol.train.mock.repo.impl;

import com.itsol.train.mock.dto.IssueDTO;
import com.itsol.train.mock.dto.IssueSearchDTO;
import com.itsol.train.mock.dto.PagingDataDTO;
import com.itsol.train.mock.entity.IssueEntity;
import com.itsol.train.mock.repo.IssueRepoCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@Repository
public class IssueRepoCustomImpl implements IssueRepoCustom {
    @Autowired
    EntityManagerFactory entityManagerFactory;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Boolean save(IssueEntity issueEntity) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        EntityTransaction transaction=entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(issueEntity);
            transaction.commit();
            return true;
        }catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        }
    }

    @Override
    public IssueDTO getById(Long id) {
        return null;
    }

    @Override
    public boolean deleteByIds(Long[] ids) {
        return false;
    }

    @Override
    public PagingDataDTO getByProjectIdPaging(Long id, Pageable pageable) {
        return null;
    }

    @Override
    public PagingDataDTO getByParams(IssueSearchDTO issueSearchDTO) {

        return null;
    }
}
