package com.itsol.train.mock.repo.impl;

import com.itsol.train.mock.entity.PositionEntity;
import com.itsol.train.mock.repo.PositionRepository;
import com.itsol.train.mock.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class PositionRepositoryImpl implements PositionRepository {
    public Logger logger = LoggerFactory.getLogger(PositionRepositoryImpl.class);

    @Override
    public PositionEntity findByDefault() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query<PositionEntity> query = session.createQuery("select s from PositionEntity s where s.id = 0 ");
            PositionEntity singleResult = query.getSingleResult();
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
