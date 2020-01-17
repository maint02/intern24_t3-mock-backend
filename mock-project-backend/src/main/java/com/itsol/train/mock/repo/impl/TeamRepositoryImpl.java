package com.itsol.train.mock.repo.impl;

import com.itsol.train.mock.entity.TeamEntity;
import com.itsol.train.mock.repo.TeamRepository;
import com.itsol.train.mock.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class TeamRepositoryImpl implements TeamRepository {
    public Logger logger = LoggerFactory.getLogger(TeamRepositoryImpl.class);

    @Override
    public TeamEntity findByDefault() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Query<TeamEntity> query = session.createQuery("select s from TeamEntity s where s.id = 0");
            TeamEntity teamEntity = query.getSingleResult();
            session.getTransaction().commit();
            return teamEntity;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            logger.error(e.getMessage());
        } finally {
            session.close();
        }
        return null;
    }
}
