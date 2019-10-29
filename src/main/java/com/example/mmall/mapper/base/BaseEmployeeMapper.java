package com.example.mmall.mapper.base;

import com.example.mmall.model.base.BaseEmployee;

import java.util.List;
import java.util.Map;

public interface BaseEmployeeMapper {
    int deleteByPrimaryKey(String id);

    int insert(BaseEmployee record);

    int insertSelective(BaseEmployee record);

    BaseEmployee selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BaseEmployee record);

    int updateByPrimaryKey(BaseEmployee record);

//    int updateBatchUserNoStatusById(@Param("list") List<String> list);
    int updateBatchUserNoStatusById(Map<String,Object> map);

    List<BaseEmployee> getUserInfoList(Map<String,Object> map);
}