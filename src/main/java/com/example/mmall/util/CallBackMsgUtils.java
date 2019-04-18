package com.example.mmall.util;

import com.example.mmall.common.msg.CallBackMsg;
import com.example.mmall.common.enums.ResonceCodeEnum;
/**
 * @Author: 真、二
 * @Date: 2019/04/04 11:20
 * @Version 1.0
 *
 */
public class CallBackMsgUtils {

    /**
     * 自己传参数
     * @return
     */
    public static CallBackMsg setResult(Object obj,String msg,int code){
        CallBackMsg callBackMsg = new CallBackMsg();
        callBackMsg.setErrcode(code);
        callBackMsg.setMessage(msg);
        callBackMsg.setDate(obj);
        return callBackMsg;
    }

    /**
     * 无参数成功方法
     * @return
     */
    public static  CallBackMsg noArgsSucess(){
        CallBackMsg callBackMsg = new CallBackMsg();
        callBackMsg.setErrcode(ResonceCodeEnum.SUCCESS.getCode());
        callBackMsg.setMessage(ResonceCodeEnum.SUCCESS.getValue());
        return callBackMsg;
    }

    /**
     * 用户不存在
     * @return
     */
    public static CallBackMsg notExistUser(){
        CallBackMsg callBackMsg = new CallBackMsg();
        callBackMsg.setErrcode(ResonceCodeEnum.NOT_EXIST_USER.getCode());
        callBackMsg.setMessage(ResonceCodeEnum.NOT_EXIST_USER.getValue());
        return callBackMsg;
    }

    /**
     * 密码错误
     * @return
     */
    public static CallBackMsg errorPassword(){
        CallBackMsg callBackMsg = new CallBackMsg();
        callBackMsg.setErrcode(ResonceCodeEnum.NO_PASSWORD.getCode());
        callBackMsg.setMessage(ResonceCodeEnum.NO_PASSWORD.getValue());
        return callBackMsg;
    }

    /**
     * 有返回结果
     * @param obj
     * @return
     */
    public static CallBackMsg setRsSucess( Object obj ){
        CallBackMsg callBackMsg = new CallBackMsg();
        callBackMsg.setErrcode(ResonceCodeEnum.SUCCESS.getCode());
        callBackMsg.setMessage(ResonceCodeEnum.SUCCESS.getValue());
        callBackMsg.setDate(obj);
        return callBackMsg;
    }

    /**
     * 服务端异常
     * @return
     */
    public static CallBackMsg exceptzion(){
        CallBackMsg callBackMsg = new CallBackMsg();
        callBackMsg.setErrcode(ResonceCodeEnum.EXCEPTION.getCode());
        callBackMsg.setMessage(ResonceCodeEnum.EXCEPTION.getValue());
        return callBackMsg;
    }

    /**
     * 参数无效
     * @return
     */
    public static CallBackMsg invalidParam(){
        CallBackMsg callBackMsg = new CallBackMsg();
        callBackMsg.setErrcode(ResonceCodeEnum.INVALID_PARAM.getCode());
        callBackMsg.setMessage(ResonceCodeEnum.INVALID_PARAM.getValue());
        return callBackMsg;
    }

    /**
     * 暂无数据
     * @return
     */
    public static CallBackMsg notResult(Object obj){
        CallBackMsg callBackMsg = new CallBackMsg();
        callBackMsg.setErrcode(ResonceCodeEnum.NO_RESULT.getCode());
        callBackMsg.setMessage(ResonceCodeEnum.NO_RESULT.getValue());
        callBackMsg.setDate(obj);
        return callBackMsg;
    }

}
