package com.itsol.train.mock.service.impl;

import com.google.gson.Gson;
import com.itsol.train.mock.dto.IssueDTO;
import com.itsol.train.mock.dto.IssueHistoryDTO;
import com.itsol.train.mock.entity.IssueEntity;
import com.itsol.train.mock.entity.IssueHistoryEntity;
import com.itsol.train.mock.repo.IssueHistoryRepository;
import com.itsol.train.mock.service.IssueHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

@Service
public class IssueHistoryServiceImpl implements IssueHistoryService {
    @Autowired
    IssueHistoryRepository issueHistoryRepository;
    @Autowired
    EntityManagerFactory entityManagerFactory;
    @Override
    public List<IssueHistoryDTO> getAllByIssueId(Long id) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        IssueEntity issueEntity=entityManager.find(IssueEntity.class,id);
        List<IssueHistoryEntity> entities=issueHistoryRepository.getAllByIssueEntity(issueEntity);
        List<IssueHistoryDTO> issueHistoryDTOS=new ArrayList<>();
        for (IssueHistoryEntity i:entities){
            IssueHistoryDTO dto=new IssueHistoryDTO();
            dto.setId(i.getId());
            dto.setIssueId(i.getIssueEntity().getId());
            dto.setComments(i.getComments());
            dto.setUpdatePersonId(i.getEmployeeEntity().getId());
            dto.setUpdateDate(i.getUpdateDate());

            String historyIssue=i.getIssueChange();
//             thư viện hỗ trợ convert:
            if(historyIssue.contains("_to_")==true){ //nếu chuỗi đúng format như đã thêm lúc đầu
                Gson gson=new Gson();
                int indexSplit1=historyIssue.indexOf("_to_");
                int indexSplit2=indexSplit1+4;
//                cắt chuỗi lấy ra oldIssue kiểu String:
                String oldIssueString =historyIssue.substring(0,indexSplit1) ;
//                convert chuỗi string sang object java:
                IssueDTO oldIssueDto=gson.fromJson(oldIssueString,IssueDTO.class);
                dto.setStatusOld(oldIssueDto.getStatusName());
                dto.setDonePercentOld(oldIssueDto.getDonePercent());

                String newIssueString =historyIssue.substring(indexSplit2,historyIssue.length());
                IssueDTO newIssueDto=gson.fromJson(newIssueString,IssueDTO.class);
                dto.setStatusNew(newIssueDto.getStatusName());
                dto.setDonePercentNew(newIssueDto.getDonePercent());
            }

            issueHistoryDTOS.add(dto);
        }
        entityManager.close();
        return issueHistoryDTOS;

    }
}
