package com.example.mmall.controller.login;

import com.example.mmall.annotation.PassToken;
import com.example.mmall.common.exception.UserBizException;
import com.example.mmall.common.msg.CallBackMsg;
import com.example.mmall.model.user.MmallUser;
import com.example.mmall.service.user.MmallUserSerice;
import com.example.mmall.util.CallBackMsgUtils;
import com.example.mmall.util.JwtUtils;
import com.example.mmall.util.RedisClientUtil;
import com.example.mmall.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author 真、二
 * @date 15:36 2019/4/8
 */

@RestController
@Slf4j
public class LoginController {
	@Autowired
	MmallUserSerice mmallUserSerice;

	/**
	 * 进入登录首页
	 * @author 真、二
	 * @date 15:38 2019/4/8
	*/
	@PassToken
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public CallBackMsg login(HttpServletRequest request,HttpSession session){

		try {
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");

			if(StringUtil.isEmpty(userName) || StringUtil.isEmpty(password)){
				return CallBackMsgUtils.invalidParam();
			}
			MmallUser mmallUser = mmallUserSerice.getUserInfo(userName,password);

			//登录成功后进入首页，这里页面，就暂时把数据返回给页面
			return CallBackMsgUtils.setRsSucess(mmallUser);

		} catch (Exception e) {
			return CallBackMsgUtils.setResult(null,e.getMessage(),400);
		}
	}

}
