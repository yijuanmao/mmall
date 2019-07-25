package com.example.mmall.common.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * 用户业务异常类
 * @date 14:21 2019/4/9
 * @author：真、二
 */
@Slf4j
public class UserBizException extends BizException {

	/** 用户不存在 **/
	public static final int USER_IS_NULL = 101;

	/** 用户银行附属信息异常 **/
	public static final int USER_BANK_WAY_ERROR = 104;

	/** 用户状态异常 **/
	public static final int USER_STATUS_ERROR = 105;

	public static final UserBizException USER_MOBILE_IS_EXIST = new UserBizException(10010003, "用户手机号已存在！");

	public static final UserBizException USER_LOGIN_ERROR = new UserBizException(10010004, "用户账号/密码错误");

	public static final UserBizException USER_MOBILE_CODE_ERROR = new UserBizException(10010006, "验证码错误！");

	public static final UserBizException USER_TYPE_ERROR = new UserBizException(10010008, "用户类型异常！");

	public UserBizException() {
	}

	public UserBizException(int code, String msgFormat, Object... args) {
		super(code, msgFormat, args);
	}

	public UserBizException(int code, String msg) {
		super(code, msg);
	}

	public UserBizException print() {
		log.info("==>BizException, code:" + this.code + ", msg:" + this.msg);
		return this;
	}
}
