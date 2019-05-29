package com.example.mmall.mapper.sys;

import com.example.mmall.model.sys.SysRoleFun;

public interface SysRoleFunMapper {
    int deleteByPrimaryKey(Long roleFunId);

    int insert(SysRoleFun record);

    int insertSelective(SysRoleFun record);

    SysRoleFun selectByPrimaryKey(Long roleFunId);

    int updateByPrimaryKeySelective(SysRoleFun record);

    int updateByPrimaryKey(SysRoleFun record);
}