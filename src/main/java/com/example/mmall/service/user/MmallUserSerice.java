package com.example.mmall.service.user;

import com.example.mmall.common.exception.UserBizException;
import com.example.mmall.model.sys.SysFunctionCell;
import com.example.mmall.model.user.MmallUser;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;


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
}
