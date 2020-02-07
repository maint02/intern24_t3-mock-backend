package com.itsol.train.mock.repo.impl;


import com.itsol.train.mock.dto.EmployeeDto;
import com.itsol.train.mock.entity.EmployeeEntity;
import com.itsol.train.mock.repo.EmployeeRepository;
import com.itsol.train.mock.utils.DataUtil;
import com.itsol.train.mock.utils.HibernateUtil;
import com.itsol.train.mock.utils.PageBuilder;
import com.itsol.train.mock.utils.SQLBuilder;
import com.itsol.train.mock.vm.EmployeeVm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.hibernate.type.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    public EmployeeDto findEmployeeById(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = SQLBuilder.readSqlFromFile(SQLBuilder.SQL_MODULE_EMPLOYEE, "find-employee");
        try {
            session.beginTransaction();
            sql += " and e.id = :p_employee_id";
            NativeQuery<EmployeeDto> sqlQuery = session.createSQLQuery(sql);
            sqlQuery.setParameter("p_employee_id", id);
            sqlQuery.addScalar("id", new LongType())
                    .addScalar("username", new StringType())
                    .addScalar("imageUrl", new StringType())
                    .addScalar("fullName", new StringType())
                    .addScalar("lastAccess", new DateType())
                    .addScalar("createdDate", new DateType())
                    .addScalar("email", new StringType())
                    .addScalar("phoneNumber", new StringType())
                    .addScalar("skypeAccount", new StringType())
                    .addScalar("userType", new StringType())
                    .addScalar("address", new StringType())
                    .addScalar("university", new StringType())
                    .addScalar("graduatedYear", new IntegerType())
                    .addScalar("isApproved", new BooleanType())
                    .addScalar("isLeader", new BooleanType())
                    .addScalar("isManager", new BooleanType())
                    .addScalar("isActived", new BooleanType())
                    .addScalar("birthday", new DateType())
                    .addScalar("roleId", new LongType())
                    .addScalar("roleName", new StringType())
                    .addScalar("departmentId", new LongType())
                    .addScalar("departmentName", new StringType())
                    .addScalar("positionId", new LongType())
                    .addScalar("positionName", new StringType())
                    .addScalar("teamId", new LongType())
                    .addScalar("teamName", new StringType())
                    .setResultTransformer(Transformers.aliasToBean(EmployeeDto.class));

            EmployeeDto dto = sqlQuery.getSingleResult();
            session.getTransaction().commit();
            return dto;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean insertEmployeeEntity(EmployeeEntity employeeEntity) {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        try {
//            session.beginTransaction();
//            session.save(employeeEntity);
//            session.getTransaction().commit();
//            return true;
//        } catch (HibernateException e) {
//            session.getTransaction().rollback();
//        } finally {
//            session.close();
//        }
        return false;
    }

    @Override
    public boolean deleteEmployeeById(long id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = SQLBuilder.readSqlFromFile(SQLBuilder.SQL_MODULE_EMPLOYEE, "find-employee");
        try {
            session.beginTransaction();
            sql += " and e.id = :p_employee_id";
            NativeQuery<EmployeeDto> sqlQuery = session.createSQLQuery(sql);
            sqlQuery.setParameter("p_employee_id", id);
            EmployeeDto dto = sqlQuery.getSingleResult();
            session.delete(dto);
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
    public List<EmployeeDto> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = SQLBuilder.readSqlFromFile(SQLBuilder.SQL_MODULE_EMPLOYEE, "find-all-employees");
        try {
            session.beginTransaction();
            NativeQuery<EmployeeDto> sqlQuery = session.createSQLQuery(sql);
            List<EmployeeDto> employeeDtos = sqlQuery.list();
            session.getTransaction().commit();
            return employeeDtos;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return null;
    }


    @Override
    public Page<EmployeeDto> findListEmployeesByParams(EmployeeVm employeeVm){
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = SQLBuilder.readSqlFromFile(SQLBuilder.SQL_MODULE_EMPLOYEE, "find-employee");
        try {
            session.beginTransaction();
            if (DataUtil.isNotNullAndEmptyString(employeeVm.getUsername())){
                sql += " and e.username like ':p_employee_username'";
            }
            if (DataUtil.isNotNullAndEmptyString(employeeVm.getDepartmentName())){
                sql += " and e.username like ':p_employee_department'";
            }
//            if (DataUtil.isNotNullAndEmptyString(employeeVm.getAddress())){
//                sql += " and e.username like ':p_employee_address'";
//            }
//            if (DataUtil.isNotNullAndEmptyString(employeeVm.getPositionName())){
//                sql += " and e.username like ':p_employee_position'";
//            }
            if (DataUtil.isNotNullAndEmptyString(employeeVm.getRoleName())){
                sql += " and e.username like ':p_employee_rolename'";
            }
            NativeQuery<EmployeeDto> sqlQuery = session.createSQLQuery(sql);
            if (!DataUtil.isNotNullAndEmptyString(employeeVm.getUsername())){
                sqlQuery.setParameter("p_employee_username", "%" +
                        employeeVm.getUsername().trim()
                                .replace("\\", "\\\\")
                                .replaceAll("%", "\\%")
                                .replaceAll("_", "\\_")
                        + "%");
            }
            if (!DataUtil.isNotNullAndEmptyString(employeeVm.getDepartmentName())){
                sqlQuery.setParameter("p_employee_department", "%" +
                        employeeVm.getUsername().trim()
                                .replace("\\", "\\\\")
                                .replaceAll("%", "\\%")
                                .replaceAll("_", "\\_")
                        + "%");
            }
            if (!DataUtil.isNotNullAndEmptyString(employeeVm.getRoleName())) {
                sqlQuery.setParameter("p_employee_rolename", "%" +
                        employeeVm.getUsername().trim()
                                .replace("\\", "\\\\")
                                .replaceAll("%", "\\%")
                                .replaceAll("_", "\\_")
                        + "%");
            }
            sqlQuery.addScalar("id", new LongType())
                    .addScalar("username", new StringType())
                    .addScalar("imageUrl", new StringType())
                    .addScalar("fullName", new StringType())
                    .addScalar("lastAccess", new DateType())
                    .addScalar("createdDate", new DateType())
                    .addScalar("email", new StringType())
                    .addScalar("phoneNumber", new StringType())
                    .addScalar("skypeAccount", new StringType())
                    .addScalar("userType", new StringType())
                    .addScalar("address", new StringType())
                    .addScalar("university", new StringType())
                    .addScalar("graduatedYear", new IntegerType())
                    .addScalar("isApproved", new BooleanType())
                    .addScalar("isLeader", new BooleanType())
                    .addScalar("isManager", new BooleanType())
                    .addScalar("isActived", new BooleanType())
                    .addScalar("birthday", new DateType())
                    .addScalar("roleId", new LongType())
                    .addScalar("roleName", new StringType())
                    .addScalar("departmentId", new LongType())
                    .addScalar("departmentName", new StringType())
                    .addScalar("positionId", new LongType())
                    .addScalar("positionName", new StringType())
                    .addScalar("teamId", new LongType())
                    .addScalar("teamName", new StringType())
                    .setResultTransformer(Transformers.aliasToBean(EmployeeDto.class));

            if (employeeVm.getPage() != null && employeeVm.getPageSize() != null) {
                Pageable pageable = PageBuilder.buildPageable(employeeVm);
                if (pageable != null) {
                    sqlQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
                    sqlQuery.setMaxResults(pageable.getPageSize());
                }
                List<EmployeeDto> dtos = sqlQuery.list();
                Page<EmployeeDto> dataPage = new PageImpl<>(dtos, pageable, total());
                return dataPage;
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return null;
    }

    private int total() {
        return 0;
    }
//    @Override
//    public List<EmployeeEntity> getEmployeesById(long id) {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        StringBuilder sql = new StringBuilder("");
//
//        try {
//            session.beginTransaction();
//            FileReader fileReader = new FileReader(new File("./sql.employee/find-employee.sql"));
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
