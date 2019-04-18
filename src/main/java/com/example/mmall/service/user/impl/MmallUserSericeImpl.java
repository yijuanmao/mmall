package com.example.mmall.service.user.impl;

import com.example.mmall.common.exception.UserBizException;
import com.example.mmall.mapper.user.MmallUserMapper;
import com.example.mmall.model.user.MmallUser;
import com.example.mmall.service.user.MmallUserSerice;
import com.example.mmall.util.JwtUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class MmallUserSericeImpl implements MmallUserSerice {

    @Resource
    MmallUserMapper mmallUserMapper;
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public boolean insertMmallUser(String username, String password, String email, String phone, String question, String answer, int role) {
        MmallUser mmallUser = new MmallUser();
        mmallUser.setUsername(username);
        mmallUser.setPassword(password);
        mmallUser.setEmail(email);
        mmallUser.setPhone(phone);
        mmallUser.setQuestion(question);
        mmallUser.setAnswer(answer);
        mmallUser.setRole(role);
        mmallUser.setCreateTime(new Date());
        mmallUser.setUpdateTime(new Date());
        int i = mmallUserMapper.insertSelective(mmallUser);
        return i>0 ? true:false;
    }


    @Override
    public PageInfo<MmallUser> getUserInfoList(int pageNum,int pageSize) {

        PageHelper.startPage(pageNum,pageSize);

        List<MmallUser> getListUser = mmallUserMapper.getUserInfoList();
        PageInfo<MmallUser> pageInfo = new PageInfo<>(getListUser);

        return pageInfo;
    }

    /** 根据用户id修改用户信息资料
    * @author zy
    * @date 9:53 2019/4/8
    */
    @Override
    public boolean updateUserInfo(int userId,String phone) {
        MmallUser mmallUser = new MmallUser();
        mmallUser.setId(userId);
        mmallUser.setQuestion(phone);
        int i = mmallUserMapper.updateByPrimaryKeySelective(mmallUser);
        return i>0 ? true:false;
    }

    //登陆
    @Override
    public MmallUser getUserInfo(String username, String password) throws UserBizException{
        //查询此账号是否存在
        int i = mmallUserMapper.checkUsername(username);
        if(i != 1){
            throw new UserBizException(UserBizException.USER_IS_NULL,"用户不存在");
        }

        //校验账号密码
        MmallUser mmallUser = mmallUserMapper.selectLogin(username,password);
        if(null == mmallUser){
            throw UserBizException.USER_LOGIN_ERROR;
        }
        Map<String, Object> userMap = new HashMap<String, Object>();
        userMap.put("username",mmallUser.getUsername());
        userMap.put("email",mmallUser.getEmail());
        userMap.put("phone",mmallUser.getPhone());
        userMap.put("role",mmallUser.getRole());
        userMap.put("userId",mmallUser.getId());

        try {
            //生成tokne，保存30分钟
            String token = jwtUtils.generateToken("S","S",Float.valueOf(60 * 30),userMap);
            mmallUser.setToken(token);
            mmallUser.setPassword("");

            return mmallUser;
        } catch (JoseException e) {
            log.error("token生成异常", e);
        }
        return null;
    }

    /**
     * 鉴权
     *
     * @param clientId
     * @param token
     * @return
     */
    @Override
    public Map<String, Object> checkToken(String clientId, String token) throws UserBizException {

        try {
            Map<String, Object> payLoadMap = jwtUtils.checkToken(token, clientId, clientId);
            String userId = String.valueOf(payLoadMap.get("userId") == null ? 0 : payLoadMap.get("userId"));

            MmallUser mmallUser = mmallUserMapper.selectByPrimaryKey(Integer.valueOf(userId));

            if (mmallUser == null) {
                throw UserBizException.USER_LOGIN_ERROR;
            }

            return payLoadMap;
        } catch (InvalidJwtException e) {
            log.error("鉴权失败:", e);
        }
        return null;
    }
}
