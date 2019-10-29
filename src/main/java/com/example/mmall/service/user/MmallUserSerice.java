package com.example.mmall.service.user;

import com.example.mmall.common.exception.UserBizException;
import com.example.mmall.model.base.BaseEmployee;
import com.example.mmall.model.sys.SysFunctionCell;
import com.example.mmall.model.user.MmallUser;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;


public interface MmallUserSerice {

    boolean insertMmallUser(String username, String password,String email,
                            String phone,String question,String answer,int role);

    PageInfo<MmallUser> getUserInfoList(int pageNum, int pageSize);

    boolean updateUserInfo(int userId,String phone);

    String login(String username, String password) throws UserBizException;

    MmallUser selectByUserName(String userName);

    /**
     * 获取用户所有权限（包括角色权限和+-权限）
     */
    List<SysFunctionCell> getPermissionList(String adminId);

    boolean etidBatchUserNoStatusById(int employeeStatus,String[] userIdArr);

    PageInfo<BaseEmployee> selectUserInfoList(String keyword, String employeeStatus, Date startEntryTime,
                                              Date endEntryTime, String[] locationArr, int pageNum, int pageSize);
}
