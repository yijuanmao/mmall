package com.example.mmall.controller.user;

import com.example.mmall.common.msg.CallBackMsg;
import com.example.mmall.model.user.MmallUser;
import com.example.mmall.service.user.MmallUserSerice;
import com.example.mmall.util.CallBackMsgUtils;
import com.example.mmall.util.StringUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zy
 * @date 13:52 2019/4/4
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class MmallUserController {

    @Autowired
    MmallUserSerice mmallUserSerice;

    @ResponseBody
    @RequestMapping(value = "/getUserList", method = RequestMethod.GET)
    public CallBackMsg getUserList(HttpServletRequest request){

        try {
            String pageNum = request.getParameter("pageNum");
            String pageSize = request.getParameter("pageSize");
            if(StringUtil.isEmpty(pageNum) || StringUtil.isEmpty(pageSize)){
                return CallBackMsgUtils.setResult(null,"参数异常",-1);
            }

            PageInfo<MmallUser> list = mmallUserSerice.getUserInfoList(Integer.valueOf(pageNum),Integer.valueOf(pageSize));
            if(list.getSize()>0 && list != null){
                return CallBackMsgUtils.setResult(list,"查询成功",100);
            }else{
                return CallBackMsgUtils.setResult(list,"暂无数据",-1);
            }
        } catch (NumberFormatException e) {
            return CallBackMsgUtils.setResult(null,e.getMessage(),-1);
        }
    }

    /**
     * @author 真、二
     * @date 11:45 2019/4/17
     * 修改用户手机号码
     * @param userId 用户id
     * @param phone 号码
    */
    @ResponseBody
    @RequestMapping(value = "/updatePhone" , method = RequestMethod.GET)
    public CallBackMsg updatePhone(@RequestParam("userId") String userId,@RequestParam("phone") String phone){

        try {
            boolean o = mmallUserSerice.updateUserInfo(Integer.valueOf(userId),phone);
            if(o){
                return CallBackMsgUtils.setResult(null,"操作成功",1);
            }else{
                return CallBackMsgUtils.setResult(null,"操作失败",0);
            }
        } catch (NumberFormatException e) {
            return CallBackMsgUtils.setResult(null,e.getMessage(),400);
        }
    }
}
