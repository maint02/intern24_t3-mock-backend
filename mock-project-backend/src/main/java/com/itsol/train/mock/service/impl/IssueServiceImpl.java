package com.itsol.train.mock.service.impl;

import com.google.gson.Gson;
import com.itsol.train.mock.constants.AppConstants;
import com.itsol.train.mock.constants.IssuesExelUploadConstants;
import com.itsol.train.mock.constants.UpdateIssueConstants;
import com.itsol.train.mock.dao.IssueDAO;
import com.itsol.train.mock.dto.*;
import com.itsol.train.mock.entity.*;
import com.itsol.train.mock.repo.IssueHistoryRepository;
import com.itsol.train.mock.repo.IssueRepository;
import com.itsol.train.mock.repo.ProjectRepository;
import com.itsol.train.mock.repo.StatusRepository;
import com.itsol.train.mock.service.IssueService;
import com.itsol.train.mock.utils.ExelUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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

    @Override
    public Integer saveAllFromExel(MultipartFile file) {
        List<IssueDTO> issueDTOS = new ArrayList<>();
        ExelUtil exelUtil = new ExelUtil();
        try {
            Workbook workbook = exelUtil.getWorkbook(file);
            Sheet sheet = exelUtil.getSheetExel(workbook, 0);
            Iterator<Row> iterator = sheet.iterator(); //tạo một iterator cho dòng
            while (iterator.hasNext()) { //DUYỆT DÒNG nếu có dòng tiếp theo bắt đầu từ dòng số 0
                Row thisRow = iterator.next();
                if (thisRow.getRowNum() == 0 || thisRow.getRowNum() == 1) { //nếu là dòng thứ nhất hoặc thứ 2
                    if (thisRow.getRowNum() == 1){
                        String cell_1=thisRow.getCell(1).getStringCellValue();
                        if (!cell_1.equals("projectId")){ // nếu ko đúng mẫu exel thì return lỗi
                            return IssuesExelUploadConstants.TEMPLATE_EXEL_ERRO;
                        }
                    }
                    // Ignore header & title
                    continue;
                }
                IssueDTO issueDTO = new IssueDTO();// bắt đầu từ dòng thứ 2 trong file exel
                // Get all cells
                Iterator<Cell> cellIterator = thisRow.cellIterator();
                while (cellIterator.hasNext()) { //DUYỆT CELL TRONG DÒNG
                    //Read cell
                    Cell cell = cellIterator.next();
                    Object cellValue = exelUtil.getCellValue(cell);
                    if (cellValue == null || cellValue.toString().isEmpty()) { // nếu cell null hoặc empty chạy cell kế tiếp
                        continue;
                    }
                    // Set value for book object
                    System.out.println(cell.getCellType());
                    int columnIndex = cell.getColumnIndex();
                    switch (columnIndex) {
                        case 1:
                            issueDTO.setProjectId(Double.valueOf((Double) cellValue).longValue());
                            break;
                        case 2:
                            issueDTO.setName((String) cellValue);
                            break;
                        case 3:
                            issueDTO.setDueDate(Double.valueOf((Double) cellValue).longValue());
                            break;
                        case 4:
                            issueDTO.setStartDate(new Date(cell.toString()));
                            break;
                        case 5:
                            issueDTO.setDonePercent(Double.valueOf((Double) cellValue).longValue());
                            break;
                        case 6:
                            issueDTO.setPriority((String) cellValue);
                            break;
                        case 7:
                            issueDTO.setReason((String) cellValue);
                            break;
                        case 8:
                            issueDTO.setDescription((String) cellValue);
                            break;
                        case 9:
                            issueDTO.setType((String) cellValue);
                            break;
                        case 10:
                            issueDTO.setStatusId(Double.valueOf((Double) cellValue).longValue());
                            break;
                    }

                }
                issueDTO.setEmployeeReportedId(1l);
                issueDTOS.add(issueDTO);
            }

        } catch (IOException e) {
            e.printStackTrace();
            return IssuesExelUploadConstants.UPLOAD_ERRO;
        }
        System.out.println(issueDTOS); // lấy ra được list dto từ file exel map sang java dto
        for (IssueDTO i : issueDTOS) {
            this.save(i);
        }
        return IssuesExelUploadConstants.UPLOAD_OK;
    }

    @Override
    public Resource exportFileExelfromList(List<IssueDTO> dtos) {

        return null;
    }


}
