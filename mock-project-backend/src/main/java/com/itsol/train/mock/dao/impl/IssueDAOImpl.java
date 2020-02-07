package com.itsol.train.mock.dao.impl;

import com.itsol.train.mock.dao.AbstractBaseDAO;
import com.itsol.train.mock.dao.IssueDAO;
import com.itsol.train.mock.dto.IssueDTO;
import com.itsol.train.mock.dto.IssueSearchDTO;
import com.itsol.train.mock.dto.PagingDataDTO;
import com.itsol.train.mock.mapper.IssueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class IssueDAOImpl extends AbstractBaseDAO implements IssueDAO {
    @Override
    public PagingDataDTO getByParams(PagingDataDTO pagingDataDTO, IssueSearchDTO issueSearchDTO) {
        PagingDataDTO pagingDataOut=new PagingDataDTO();
        StringBuilder sb=new StringBuilder();
        Map<String, Object> parameters = new HashMap<>();
        sb.append("select i.id, i.name,i.due_date,i.priority,i.start_date,i.done_percent, ");
        sb.append(" i.reason,i.description,i.type,i.project_id,i.status_id,i.employee_reported_id, ");
        sb.append(" s.name as status_name"); // cái tên này lấy ở bảng status (cái 'as status_name' để mapper lấy map vs dto)
        sb.append(" from issue i inner join status s on i.status_id=s.id where 1=1 ");
        if(issueSearchDTO.getProjectId()!=null){
            sb.append("and i.project_id=:idProject ");
            parameters.put("idProject",issueSearchDTO.getProjectId());
        }if(issueSearchDTO.getName()!=null&&!issueSearchDTO.getName().isEmpty()){
            sb.append("and i.name like :Iname ");
         parameters.put("Iname","%"+issueSearchDTO.getName().trim()+"%");
        }
        if (issueSearchDTO.getPriority()!=null&&!issueSearchDTO.getPriority().isEmpty()){
            sb.append("and i.priority = :Ipriority ");
            parameters.put("Ipriority",issueSearchDTO.getPriority().trim());
        }
        Long levelDonePercent=issueSearchDTO.getDonePercent();
        if(levelDonePercent!=null&& levelDonePercent!=0){
            if (levelDonePercent==1){
                sb.append(" and i.done_percent between 0 and 30 ");
            }
            if (levelDonePercent==2){
                sb.append(" and i.done_percent between 31 and 50 ");
            }
            if (levelDonePercent==3){
                sb.append(" and i.done_percent between 51 and 80 ");
            }
            if (levelDonePercent==4){
                sb.append(" and i.done_percent between 80 and 100 ");
            }
        }
        List<IssueDTO> issueDTOS=queryPaging(pagingDataDTO,sb.toString(),parameters,new IssueMapper());
        pagingDataOut.setListData(issueDTOS);
        Long totalRecord= countTotalRecords(sb.toString(),parameters);
        int totalPage=(int) Math.ceil((double)((Long)totalRecord)/pagingDataDTO.getLimit());
        pagingDataOut.setTotalPage(totalPage);
        pagingDataOut.setLimit(pagingDataDTO.getLimit());
        pagingDataOut.setPage(pagingDataDTO.getPage()+1);
        return pagingDataOut;
    }
}
