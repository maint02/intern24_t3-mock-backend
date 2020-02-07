package com.itsol.train.mock.service.impl;

import com.itsol.train.mock.dto.EmployeeIssueDTO;
import com.itsol.train.mock.entity.*;
import com.itsol.train.mock.repo.EmployeeIssueRepository;
import com.itsol.train.mock.service.EmployeeIssueService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@Service
public class EmployeeIssueServiceImpl implements EmployeeIssueService {
    @Autowired
    EmployeeIssueRepository employeeIssueRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    EntityManagerFactory entityManagerFactory;
    @Override
    public boolean save(EmployeeIssueDTO employeeIssueDTO) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        EntityTransaction transaction=entityManager.getTransaction();
        try {
            transaction.begin();
            EmployeeIssueEntity entity=new EmployeeIssueEntity();
            entity.setNote(employeeIssueDTO.getNote());
            entity.setSpentTime(employeeIssueDTO.getSpentTime());
            EmployeeEntity employeeEntity=entityManager
                    .find(EmployeeEntity.class,employeeIssueDTO.getEmployeeId());
            StatusEntity statusEntity=entityManager
                    .find(StatusEntity.class,employeeIssueDTO.getStatusId());
            IssueEntity issueEntity=entityManager.find(IssueEntity.class,employeeIssueDTO.getIssueId());
            entity.setIssueEntity(issueEntity);
            entity.setEmployeeEntity(employeeEntity);
            entity.setStatusEntity(statusEntity);
            entity.setEmpIssuePK(new EmpIssuePK(employeeIssueDTO.getEmployeeId(),employeeIssueDTO.getIssueId()));
            entity.setEmployeeAssignId(employeeIssueDTO.getEmployeeAssignedId());
            entityManager.persist(entity);
            transaction.commit();
            entityManager.close();
            return true;

        }catch (Exception e){
            if (transaction.isActive()){
                transaction.rollback();
            }
            return false;
        }finally {
            entityManager.close();
        }
    }
}
