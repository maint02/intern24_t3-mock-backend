package com.itsol.train.mock.dao.impl;

import com.itsol.train.mock.dao.StatusDAO;
import com.itsol.train.mock.dto.StatusDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class StatusDAOImpl implements StatusDAO {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public List<StatusDTO> getByTypeId(Long idType) {
        StringBuilder builder=new StringBuilder();
        builder.append("select * from status s where s.type_id=:Stype ");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("Stype",idType);
        return namedParameterJdbcTemplate.query(builder.toString(),parameters,
                BeanPropertyRowMapper.newInstance(StatusDTO.class));
    }
}
