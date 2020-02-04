package com.itsol.train.mock.dao.impl;

import com.itsol.train.mock.dao.AbstractBaseDAO;
import com.itsol.train.mock.dao.IssueDAO;
import com.itsol.train.mock.dto.IssueDTO;
import com.itsol.train.mock.dto.IssueSearchDTO;
import com.itsol.train.mock.dto.PagingDataDTO;
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
        sb.append("select i.id, i.name,i.due_date,i.priority from issue i where 1=1 ");
        if(issueSearchDTO.getProjectId()!=null){
            sb.append("and i.project_id=:idProject ");
            parameters.put("idProject",issueSearchDTO.getProjectId());
        }if(issueSearchDTO.getName()!=null&&!issueSearchDTO.getName().isEmpty()){
            sb.append("and i.name like :Iname ");
         parameters.put("Iname","%"+issueSearchDTO.getName().trim()+"%");
        }
        if (issueSearchDTO.getPriority()!=null&&!issueSearchDTO.getPriority().isEmpty()){
            sb.append("and i.priority = :Ipriority");
            parameters.put("Ipriority",issueSearchDTO.getPriority().trim());
        }
        List<IssueDTO> issueDTOS=queryPaging(pagingDataDTO,sb.toString(),parameters,IssueDTO.class );
        pagingDataOut.setListData(issueDTOS);
        Long totalRecord= countTotalRecords(sb.toString(),parameters);
        int totalPage=(int) Math.ceil((double)((Long)totalRecord)/pagingDataDTO.getLimit());
        pagingDataOut.setTotalPage(totalPage);
        pagingDataOut.setLimit(pagingDataDTO.getLimit());
        pagingDataOut.setPage(pagingDataDTO.getPage()+1);
        return pagingDataOut;
    }
}
