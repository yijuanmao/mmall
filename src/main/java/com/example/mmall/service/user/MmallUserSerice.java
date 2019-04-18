package com.example.mmall.service.user;

import com.example.mmall.common.exception.UserBizException;
import com.example.mmall.model.user.MmallUser;
import com.github.pagehelper.PageInfo;

import java.util.Map;


public interface MmallUserSerice {

    boolean insertMmallUser(String username, String password,String email,
                            String phone,String question,String answer,int role);

    PageInfo<MmallUser> getUserInfoList(int pageNum, int pageSize);

    boolean updateUserInfo(int userId,String phone);

    MmallUser getUserInfo(String username, String password) throws UserBizException;

    /**
     * 鉴权
     *
     * @param clientId
     * @param token
     * @return
     */
    public Map<String, Object> checkToken(String clientId, String token) throws UserBizException;

}
