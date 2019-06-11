package com.example.mmall.common.enums;

/**
 * @Author: 真、二
 * @Date: 2019/04/6 10:31
 * @Version 1.0
 */
public enum ResonceCodeEnum {

    SUCCESS(100 , "成功"),
    FAIL ( 255, "失败"),
    NO_LICENSE( 301 , "没有license" ),
    NOT_EXIST_USER( 302 , "此用户不存在"),
    NO_PASSWORD( 303 , "密码错误" ),
    NO_LICENSE_ONLINE( 304 , "没有此用户" ),
    INVALID_PARAM( 305 , "参数无效" ),
    EXCEPTION(500,"出现异常"),
    NO_RESULT(306,"暂无数据"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限");


    private Integer code;

    private String value;

    ResonceCodeEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
