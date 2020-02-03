package com.itsol.train.mock.dao.impl;

import com.itsol.train.mock.dao.AbstractBaseDAO;
import com.itsol.train.mock.dto.IssueDTO;
import com.itsol.train.mock.dto.IssueSearchDTO;
import com.itsol.train.mock.dto.PagingDataDTO;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class IssueDAOImpl extends AbstractBaseDAO implements IssueDAO {
    @Override
    public PagingDataDTO getByParams(PagingDataDTO pagingDataDTO, IssueSearchDTO issueSearchDTO) {
        PagingDataDTO pagingData=new PagingDataDTO();
        StringBuilder sb=new StringBuilder();
        Map<String, Object> parameters = new HashMap<>();
        sb.append("select * from issue where 1=1 ");
        if(issueSearchDTO.getProjectId()!=null){
            sb.append(" and issue.project_id = :i_projectId ");
            parameters.put("i_projectId ",issueSearchDTO.getProjectId());
        }
        if(!issueSearchDTO.getName().isEmpty()&&issueSearchDTO.getName()!=null){
            sb.append(" and issue.name like :i_name ");
            parameters.put("i_name","%" +
                    issueSearchDTO.getName().trim().toUpperCase()
                            .replace("\\", "\\\\")
                            .replaceAll("%", "\\%")
                            .replaceAll("_", "\\_")
                    + "%");
        }
        List<IssueDTO> issueDTOS=queryPaging(pagingDataDTO,sb.toString(),parameters,IssueDTO.class );
        pagingData.setListData(issueDTOS);
        return pagingData;
    }
}
