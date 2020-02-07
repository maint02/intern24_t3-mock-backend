package com.itsol.train.mock.repo.impl;

import com.itsol.train.mock.entity.DepartmentEntity;
import com.itsol.train.mock.repo.DepartmentRepository;
import com.itsol.train.mock.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentRepositoryImpl implements DepartmentRepository {
    public Logger logger = LoggerFactory.getLogger(DepartmentRepositoryImpl.class);

    @Override
    public DepartmentEntity findByDefault() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Query<DepartmentEntity> query = session.createQuery("select s from DepartmentEntity s where s.id = 0");
            DepartmentEntity singleResult = query.getSingleResult();
            session.getTransaction().commit();
            return singleResult;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            logger.error(e.getMessage());
        } finally {
            session.close();
        }
        return null;

    }
}
