package com.itsol.train.mock.repo.impl;


import com.itsol.train.mock.entity.EmployeeEntity;
import com.itsol.train.mock.repo.EmployeeRepository;
import com.itsol.train.mock.utils.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class EmployeeRepositoryImpl implements EmployeeRepository {

    @Override
    public boolean updateEmployeeEntity(EmployeeEntity employeeEntity) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            session.update(employeeEntity);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public EmployeeEntity getEmployeeEntityById(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query<EmployeeEntity> query = session.createQuery("select s from EmployeeEntity s where s.id = :p_employeeEntity_id");
            query.setParameter("p_employeeEntity_id", id);
            EmployeeEntity employee = query.getSingleResult();
            session.getTransaction().commit();
            return employee;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean insertEmployeeEntity(EmployeeEntity employeeEntity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(employeeEntity);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean deleteEmployeeEntity(long id) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query<EmployeeEntity> query = session.createQuery("select s from EmployeeEntity s where s.id = :p_employeeEntity_id");
            query.setParameter("p_employeeEntity_id", id);
            EmployeeEntity employee = query.getSingleResult();
            session.delete(employee);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public List<EmployeeEntity> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            List<EmployeeEntity> employeeEntities = session.createQuery("from EmployeeEntity ").list();
            session.getTransaction().commit();
            return employeeEntities;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return null;
    }


//    @Override
//    public List<EmployeeEntity> getEmployeesById(long id) {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        StringBuilder sql = new StringBuilder("");
//
//        try {
//            session.beginTransaction();
//            FileReader fileReader = new FileReader(new File("./sql.employee/find-employee-by-id.sql"));
//            sql.append(fileReader);
//            List<EmployeeEntity> list = session.createQuery(sql.toString()).list();
//            return list;
//        } catch (HibernateException | FileNotFoundException e){
//            session.getTransaction().rollback();
//        } finally {
//            session.close();
//        }
//        return null;
//    }
}
