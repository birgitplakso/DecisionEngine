package com.example.DecisionEngine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class EngineRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;


    public Integer getCreditModifier(String code) {
        String sql="SELECT credit_modifier FROM segments where code_id =:dbCodeId";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("dbCodeId", code);
        Integer creditModifier=jdbcTemplate.queryForObject(sql,paramMap, Integer.class);
        return creditModifier;
    }
}
