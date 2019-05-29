package com.example.mmall.mapper.sys;

import com.example.mmall.model.sys.SysFunctionCell;

import java.util.List;

public interface SysFunctionCellMapper {
    int deleteByPrimaryKey(String fuceId);

    int insert(SysFunctionCell record);

    int insertSelective(SysFunctionCell record);

    SysFunctionCell selectByPrimaryKey(String fuceId);

    int updateByPrimaryKeySelective(SysFunctionCell record);

    int updateByPrimaryKey(SysFunctionCell record);

    /**
     * 获取用户所有权限
     */
    List<SysFunctionCell> getPermissionList(String userId);
}