package com.example.mmall.mapper.sys;

import com.example.mmall.model.sys.SysRoleUser;

public interface SysRoleUserMapper {
    int deleteByPrimaryKey(String roleUserId);

    int insert(SysRoleUser record);

    int insertSelective(SysRoleUser record);

    SysRoleUser selectByPrimaryKey(String roleUserId);

    int updateByPrimaryKeySelective(SysRoleUser record);

    int updateByPrimaryKey(SysRoleUser record);
}