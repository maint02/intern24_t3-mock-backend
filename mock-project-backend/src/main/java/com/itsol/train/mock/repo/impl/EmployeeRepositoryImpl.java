package com.itsol.train.mock.repo.impl;


import com.itsol.train.mock.constants.RoleConstants;
import com.itsol.train.mock.dto.BaseDto;
import com.itsol.train.mock.dto.BaseSearchDTO;
import com.itsol.train.mock.dto.EmployeeDto;
import com.itsol.train.mock.dto.PagingDataDTO;
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
            log.error(e.getMessage(), e);
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
            log.error(e.getMessage(), e);
        } finally {
            session.close();
        }
        return null;
    }

//    @Override
//    public boolean insertEmployeeEntity(EmployeeEntity employeeEntity) {
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
//        return false;
//    }

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
            log.error(e.getMessage(), e);
        } finally {
            session.close();
        }
        return false;
    }

    // get all & get all by params
    @Override
    public BaseSearchDTO findListEmployeesByParams(EmployeeVm employeeVm) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = SQLBuilder.readSqlFromFile(SQLBuilder.SQL_MODULE_EMPLOYEE, "find-employee");
        BaseSearchDTO baseSearchDTO = new BaseSearchDTO();
        BaseDto baseDto = new BaseDto();
        try {
            session.beginTransaction();
            if (DataUtil.isNotNullAndEmptyString(employeeVm.getUsername())) {
                sql += " and e.username like :p_employee_username";
            }
//            tìm nhân viên cùng phòng ban
            if (DataUtil.isNotNullAndEmptyString(employeeVm.getDepartmentName())) {
                sql += " and e.department like :p_employee_department";
            }
//            if (DataUtil.isNotNullAndEmptyString(employeeVm.getAddress())){
//                sql += " and e.username like ':p_employee_address'";
//            }
//            if (DataUtil.isNotNullAndEmptyString(employeeVm.getPositionName())){
//                sql += " and e.username like ':p_employee_position'";
//            }
            if (DataUtil.isNotNullAndEmptyString(employeeVm.getMyRoleName()) && employeeVm.getMyRoleName().trim().equalsIgnoreCase(RoleConstants.MANAGER)) {
                sql += " and er.role_id < :p_employee_roleId\n" +
                        " and d.id = :p_employee_departmentId ";
            }

            sql+= " order by username";

            String mainSql = SQLBuilder.readSqlFromFile(SQLBuilder.SQL_MODULE_EMPLOYEE, "search_and_count_emp");
            mainSql = mainSql.replaceAll("_sql_", sql);

            NativeQuery<EmployeeDto> sqlQuery = session.createSQLQuery(mainSql);

            sqlQuery.setParameter("p_page_number", employeeVm.getPage());
            sqlQuery.setParameter("p_page_size", employeeVm.getPageSize());

            if (DataUtil.isNotNullAndEmptyString(employeeVm.getUsername())) {
                sqlQuery.setParameter("p_employee_username", DataUtil.removeWildcardCharacters(employeeVm.getUsername()));
            }
            if (DataUtil.isNotNullAndEmptyString(employeeVm.getDepartmentName())) {
                sqlQuery.setParameter("p_employee_department", DataUtil.removeWildcardCharacters(employeeVm.getDepartmentName()));
            }
            if (DataUtil.isNotNullAndEmptyString(employeeVm.getRoleName())) {
                sqlQuery.setParameter("p_employee_rolename", DataUtil.removeWildcardCharacters(employeeVm.getRoleName()));
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
                    .addScalar("totalRecord", new LongType())
                    .setResultTransformer(Transformers.aliasToBean(EmployeeDto.class));
            List<EmployeeDto> resultList = sqlQuery.getResultList();
            baseSearchDTO.setData(resultList);
            baseSearchDTO.setPage(employeeVm.getPage());
            baseSearchDTO.setPageSize(employeeVm.getPageSize());
            baseSearchDTO.setTotalRecords(resultList != null && !resultList.isEmpty() ? resultList.get(0).getTotalRecord() : 0);
            return baseSearchDTO;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            log.error(e.getMessage(), e);
        } finally {
            session.close();
        }
        return null;
    }

    private int total() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        int count = 0;
        try {
            String sql = SQLBuilder.readSqlFromFile(SQLBuilder.SQL_MODULE_EMPLOYEE, "count-employee");
            NativeQuery sqlQuery = session.createSQLQuery(sql);
            count = sqlQuery.list().size();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            log.error(e.getMessage(), e);
        } finally {
            session.close();
        }
        return count;
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
