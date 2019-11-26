//package com.example.mmall.user;
//
//import com.example.mmall.MmallApplicationTests;
//import com.example.mmall.model.base.BaseEmployee;
//import com.example.mmall.service.user.MmallUserSerice;
//import com.example.mmall.util.DateUtil;
//import com.github.pagehelper.PageInfo;
//import lombok.extern.slf4j.Slf4j;
//import org.json.JSONArray;
//import org.junit.Test;
//import javax.annotation.Resource;
//import java.util.Date;
//
//@Slf4j
//public class MmallUserSericeTest extends MmallApplicationTests {
//
//    @Resource
//    private MmallUserSerice mmallUserSerice;
//
//    private static double EARTH_RADIUS = 6378.137;
//    /*
//    * 用户注册测试方法
//    * */
//    @Test
//    public void testInsertMmallUser(){
//        String username = System.currentTimeMillis()+"";
//        String password = "123456";
//        String email = username+"@qq.com";
//        String phone = "15697360503";
//        String question = "你爸爸叫什么";
//        String answer = "小马";
//        int role = 0;
//        mmallUserSerice.insertMmallUser(username,password,email,phone,question,answer,role);
//    }
//
//    /*
//     * 查询所有用户MmallUserSericeTest
//     * */
//    @Test
//    public void testGetUserInfoList(){
//
//        String[] userIdArr = new String[]{"3F2504E0-4F89-11D3-9A0C-0305E82C3301","bfa46604-c806-11e9-9ce5-00505684f17f"};
//        boolean o = mmallUserSerice.etidBatchUserNoStatusById(2,userIdArr);
//        System.out.println("=========>>>> "+o);
//
//      /*  int pageNum = 0;
//        int pageSize = 5;
//        PageInfo<MmallUser> list =  mmallUserSerice.getUserInfoList(pageNum,pageSize);
//        System.out.println("list==============》》》》》》》》》》》》》"+list.getSize());*/
//    }
//
//
//    @Test
//    public void selectUserInfoListTest(){
//
//        String keyword = "七";
//        String employeeStatus = "";
//
//        String startEntryTimeStr = "2019-08-03";
//        String endEntryTimeStr = "2019-08-05";
//
//        Date startEntryTime = DateUtil.parseDate(startEntryTimeStr);
//        Date endEntryTime = DateUtil.parseDate(endEntryTimeStr);
//        String[] locationArr = new String[]{};
//        int pageNum = 0;
//        int pageSize = 10;
//        PageInfo<BaseEmployee> listData = mmallUserSerice.selectUserInfoList(keyword,employeeStatus,startEntryTime,
//                endEntryTime,locationArr,pageNum,pageSize);
//        JSONArray array = new JSONArray();
//        array.put(listData);
//        System.out.println("数据为：=============== "+array);
//    }
//
//
//
//}
