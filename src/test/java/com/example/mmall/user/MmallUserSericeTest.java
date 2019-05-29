package com.example.mmall.user;

import com.example.mmall.DemoApplicationTests;
import com.example.mmall.model.user.MmallUser;
import com.example.mmall.service.user.MmallUserSerice;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import javax.annotation.Resource;

@Slf4j
public class MmallUserSericeTest extends DemoApplicationTests {

    @Resource
    private MmallUserSerice mmallUserSerice;

    /*
    * 用户注册测试方法
    * */
    @Test
    public void testInsertMmallUser(){
        String username = System.currentTimeMillis()+"";
        String password = "123456";
        String email = username+"@qq.com";
        String phone = "15697360503";
        String question = "你爸爸叫什么";
        String answer = "小马";
        int role = 0;
        mmallUserSerice.insertMmallUser(username,password,email,phone,question,answer,role);
    }

    /*
     * 查询所有用户MmallUserSericeTest
     * */
    @Test
    public void testGetUserInfoList(){
        int pageNum = 0;
        int pageSize = 5;
        PageInfo<MmallUser> list =  mmallUserSerice.getUserInfoList(pageNum,pageSize);
        System.out.println("list==============》》》》》》》》》》》》》"+list.getSize());
    }

    /*
     * 根据用户id修改用户信息
     * */
    @Test
    public void testUpdateUserInfo(){
        int userId = 10000;
        String question = "你妈妈叫什么";
        mmallUserSerice.updateUserInfo(userId,question);
    }

}
