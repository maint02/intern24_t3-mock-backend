package com.itsol.train.mock.repo.impl;

import com.itsol.train.mock.entity.RoleEntity;
import com.itsol.train.mock.repo.RoleRepository;
import com.itsol.train.mock.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepositoryImpl implements RoleRepository {
    public Logger logger = LoggerFactory.getLogger(DepartmentRepositoryImpl.class);

    @Override
    public RoleEntity findByDefault() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Query<RoleEntity> query = session.createQuery("SELECT s FROM RoleEntity s WHERE s.id = 1");
            RoleEntity singleResult = query.getSingleResult();
            session.getTransaction().commit();
            return singleResult;

        } catch (HibernateException e) {
            session.getTransaction().rollback();
            logger.error(e.getMessage());
        }
        finally {
            session.close();
        }
        return null;
    }
    @Override
    public RoleEntity findByRoleName(String roleName) {
        return null;
    }
}
