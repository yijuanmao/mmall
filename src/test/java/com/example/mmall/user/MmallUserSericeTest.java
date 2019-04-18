package com.example.mmall.user;

import com.example.mmall.DemoApplicationTests;
import com.example.mmall.model.user.MmallUser;
import com.example.mmall.service.user.MmallUserSerice;
import com.example.mmall.util.JwtUtils;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Slf4j
public class MmallUserSericeTest extends DemoApplicationTests {

    @Resource
    private MmallUserSerice mmallUserSerice;
    @Autowired
    JwtUtils jwtUtils;

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

    /*
     * 根据用户id查询用户信息
     * */
/*    @Test
    public void testGetUserInfo(){
        int userId = 10000;
        MmallUser mmallUser = mmallUserSerice.getUserInfo(userId);
    }*/

    @Test
    public void tsetCheckToken() throws Exception{
    String token = "eyJraWQiOiJrMSIsImFsZyI6IlJTMjU2In0.eyJpc3MiOiJTIiwiYXVkIjoiUyIsImV4cCI6MTU1NTQ3Mjg1MywianRpIjoiU1JUUlh3bUtPUEp0b082UEt4WTRvZyIsImlhdCI6MTU1NDg2ODA1MywibmJmIjoxNTU0ODY3OTkzLCJzdWIiOiJzdWJqZWN0Iiwicm9sZSI6IjAiLCJwaG9uZSI6IjE1Njk3MzYwNTAzIiwiZW1haWwiOiI4MjQ0MTQ4MjhAcXEuY29tIiwidXNlcm5hbWUiOiI4MjQ0MTQ4MjgiLCJhdWRpZW5jZSI6IlMifQ.oBFIilSiBAQmLRmPkhz-tKr0ERz3CWAAiNmLk-hMND5lgvUAioQO3hiG7xX0JOajMhjServlU5nTeRDe0Ul-y2QsKugji4P754DdSD8LfkAaENyNCEFNk5m7f9-CkU69azniOgljOa_qvsUlz7e2--Pae0uH21Tu4xZe3_-3F2b-F2fRwe2iDycHo0kYrHdOTKnF3kLIr-C7ag4MOl65zCm-0Bjhoxxw9nC-PCgSvzdv1KLhR3tsODBOBOqZGl0ZI0M_ekyDg_wrtjnKksaFQXUmepiyTGMSH_tpz6LvT6E39NDP7xpdGN7ie02CDL5ssz8JEC0qWCDQ_-1-P3jy5A";
    String isUser = "S";
    String audience = "S";
    jwtUtils.checkToken(token,isUser,audience);

    }

    @Test
    public void test() throws ParseException {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String startTime = "2019-04-15";

        Date dd = df.parse(startTime);
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(dd);

        calendar.add(Calendar.DAY_OF_MONTH, 1);//加一天

        System.out.println("增加一天之后：" + df.format(calendar.getTime()));
//        System.out.println("=====>>>>"+startTime+1);
    }
}
