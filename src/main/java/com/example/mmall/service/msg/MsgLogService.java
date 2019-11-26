package com.example.mmall.service.msg;

import com.example.mmall.model.msg.MsgLog;

/**
 * @author zhener
 * @date 11:12 2019/11/1
 */
public interface MsgLogService {

	boolean updateStatus(String msgId, Integer status);
	MsgLog selectByMsgId(String msgId);
}
