package com.example.mmall.service.msg.impl;

import com.example.mmall.mapper.msg.MsgLogMapper;
import com.example.mmall.model.msg.MsgLog;
import com.example.mmall.service.msg.MsgLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author zhener
 * @date 11:12 2019/11/1
 */
@Slf4j
@Service
public class MsgLogServiceImpl implements MsgLogService {

	@Autowired
	private MsgLogMapper msgLogMapper;

	@Override
	public boolean updateStatus(String msgId, Integer status) {

		MsgLog msgLog = new MsgLog();
		msgLog.setMsgId(msgId);
		msgLog.setStatus(status);
		msgLog.setUpdateTime(new Date());
		msgLogMapper.updateByPrimaryKeySelective(msgLog);
		return false;
	}

	@Override
	public MsgLog selectByMsgId(String msgId) {
		return msgLogMapper.selectByPrimaryKey(msgId);
	}
}
