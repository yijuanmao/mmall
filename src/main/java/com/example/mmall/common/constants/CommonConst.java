package com.example.mmall.common.constants;

/**
 * 基本常量
 * @author 真、二
 * @date 16:00 2019/5/21
 */
public class CommonConst {

	/**
	 * 生成服务端token 创建者使用此常量
	 */
	public static final String SERVICE_TOKEN = "service_token";

	//=============================================  用户模块使用redis基本常量   ==============================
	public static final String USERINFO_REDIS = "userinfo_redis";

	//=============================================  短信验证码redis基本常量   ==============================
	public static final String CHECK_CODE_REDIS = "check_code_redis";

	public static final Integer DELIVERING = 0;// 消息投递中
	public static final Integer DELIVER_SUCCESS = 1;// 投递成功
	public static final Integer DELIVER_FAIL = 2;// 投递失败
	public static final Integer CONSUMED_SUCCESS = 3;// 已消费
}
