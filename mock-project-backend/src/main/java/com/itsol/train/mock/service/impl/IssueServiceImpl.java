package com.itsol.train.mock.service.impl;

import com.google.gson.Gson;
import com.itsol.train.mock.constants.UpdateIssueConstants;
import com.itsol.train.mock.dao.IssueDAO;
import com.itsol.train.mock.dto.*;
import com.itsol.train.mock.entity.*;
import com.itsol.train.mock.repo.IssueHistoryRepository;
import com.itsol.train.mock.repo.IssueRepository;
import com.itsol.train.mock.repo.ProjectRepository;
import com.itsol.train.mock.repo.StatusRepository;
import com.itsol.train.mock.service.IssueService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.Date;
import java.util.List;

@Service
public class IssueServiceImpl implements IssueService {
    @Autowired
    IssueRepository issueRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    StatusRepository statusRepository;
    @Autowired
    EntityManagerFactory entityManagerFactory;
    @Autowired
    IssueHistoryRepository issueHistoryRepository;
    @Autowired
    IssueDAO issueDAO;

    @Override
    public IssueDTO save(IssueDTO issueDTO) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        IssueEntity issueEntityInput = modelMapper.map(issueDTO,IssueEntity.class);
        ProjectEntity projectEntity=projectRepository.getById(issueDTO.getProjectId());
        StatusEntity statusEntity=statusRepository.getById(issueDTO.getStatusId());
        if (issueDTO.getEmployeeReportedId()!=null){
            EmployeeEntity employeeEntity=entityManager.find(EmployeeEntity.class,issueDTO.getEmployeeReportedId());
            issueEntityInput.setEmployeeEntity(employeeEntity);
        }
        issueEntityInput.setProjectEntity(projectEntity);
        issueEntityInput.setStatusEntity(statusEntity);
        IssueEntity issueEntityOutput=issueRepository.save(issueEntityInput);
        if (issueEntityOutput!=null){
            IssueDTO issuesDTOResponse=modelMapper.map(issueEntityOutput,IssueDTO.class);
            return issuesDTOResponse;
        }else {
            return null;
        }
    }

    @Override
    public IssueDTO getById(Long id) {
        IssueEntity issueEntity=issueRepository.getById(id);
        ProjectDTO projectDTO=modelMapper.map(issueEntity.getProjectEntity(),ProjectDTO.class);
        StatusDTO statusDTO=modelMapper.map(issueEntity.getStatusEntity(),StatusDTO.class);
        IssueDTO issuesDTO=modelMapper.map(issueEntity,IssueDTO.class);
        issuesDTO.setProjectDTO(projectDTO);
        issuesDTO.setStatusDTO(statusDTO);
        return issuesDTO;
    }

    @Override
    public boolean deleteByIds(Long[] ids) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        EntityTransaction transaction=entityManager.getTransaction();
        try {
            transaction.begin();
            for (Long id:ids){
                IssueEntity issueEntity=entityManager.find(IssueEntity.class,id);
                entityManager.remove(issueEntity);
            }
            transaction.commit();
            entityManager.close();
            return true;
        }catch (Exception e){
            if (transaction.isActive()){
                transaction.rollback();
            }
            return false;
        }

    }

    @Override
    public PagingDataDTO getByProjectIdPaging(Long id, Pageable pageable) {
        PagingDataDTO pagingDataDTO=new PagingDataDTO();
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        //EntityTransaction transaction=entityManager.getTransaction();
        try {
            ProjectEntity projectEntity=entityManager.find(ProjectEntity.class,id);
            Page<IssueEntity> pageResult=issueRepository.getAllByProjectEntity(projectEntity,pageable);
            List<IssueEntity> issueEntities=pageResult.getContent();
            List<IssueDTO> issueDTOS=modelMapper.map(issueEntities, new TypeToken<List<IssueDTO>>(){}.getType());
            for (IssueDTO i:issueDTOS){
                StatusEntity statusEntity=entityManager.find(StatusEntity.class,i.getStatusId());
                i.setStatusName(statusEntity.getName()); // lấy tên status của issue này.
            }
            pagingDataDTO.setPage(pageable.getPageNumber()+1);
            pagingDataDTO.setTotalPage(pageResult.getTotalPages());
            pagingDataDTO.setListData(issueDTOS);
            entityManager.close();
            return pagingDataDTO;
        }catch (Exception e) {
            entityManager.close();
            return null;
        }
    }

    @Override
    public String update(UpdateIssueDTO updateIssueDTO) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        EntityTransaction transaction=entityManager.getTransaction();
        try {
            transaction.begin();
            IssueEntity issueEntityOld= entityManager.find(IssueEntity.class,updateIssueDTO.getId());
            IssueDTO issueDTO_Old=modelMapper.map(issueEntityOld,IssueDTO.class);
            if (updateIssueDTO.getStatusId()!=issueEntityOld.getStatusEntity().getId()
                    || updateIssueDTO.getDonePercent()!=issueEntityOld.getDonePercent()) {
                //lưu trạng thái mới cho issue:
                issueEntityOld.setStatusEntity(entityManager.find(StatusEntity.class,updateIssueDTO.getStatusId()));
                //lưu trạng thái mới cho DonePercent:
                issueEntityOld.setDonePercent(updateIssueDTO.getDonePercent());
                //tạo cái dto mới
                IssueDTO issueDTO_New=modelMapper.map(issueEntityOld,IssueDTO.class);
                //tạo lịch sử issue:
                IssueHistoryEntity issueHistoryEntity=new IssueHistoryEntity();
                issueHistoryEntity.setComments(updateIssueDTO.getComments());
                issueHistoryEntity.setUpdateDate(new Date());
                issueHistoryEntity.setEmployeeEntity(entityManager.find(EmployeeEntity.class,updateIssueDTO.getUpdatePersonId()));
                issueHistoryEntity.setIssueEntity(issueEntityOld);
                issueHistoryEntity.setImageName(updateIssueDTO.getImageName());
                Gson gson=new Gson();
                StringBuilder historyIssue=new StringBuilder();
                historyIssue.append(gson.toJson(issueDTO_Old));
                historyIssue.append("_to_");
                String newIssue=gson.toJson(issueDTO_New);
                historyIssue.append(newIssue);
                issueHistoryEntity.setIssueChange(historyIssue.toString());
                entityManager.persist(issueHistoryEntity);// lưu lịch sử
                transaction.commit();
                entityManager.close();
                return UpdateIssueConstants.UPDATE_OK;
            }else {
                return UpdateIssueConstants.NOT_CHANGE;
            }
        }catch (Exception e){
            if (transaction.isActive()){
                transaction.rollback();
            }
            return UpdateIssueConstants.UPDATE_ERRO;
        }finally {
            entityManager.close();
        }
    }

    @Override
    public PagingDataDTO getByParams(PagingDataDTO pagingDataDTO, IssueSearchDTO issueSearchDTO) {
        return issueDAO.getByParams(pagingDataDTO,issueSearchDTO);
    }


}
