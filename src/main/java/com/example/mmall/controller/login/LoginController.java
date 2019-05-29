package com.example.mmall.controller.login;

import com.example.mmall.common.msg.CallBackMsg;
import com.example.mmall.service.user.MmallUserSerice;
import com.example.mmall.util.CallBackMsgUtils;
import com.example.mmall.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 真、二
 * @date 15:36 2019/4/8
 */

@RestController
@Slf4j
public class LoginController {
	@Autowired
	MmallUserSerice mmallUserSerice;
	@Value("${jwt.tokenHeader}")
	private String tokenHeader;
	@Value("${jwt.tokenHead}")
	private String tokenHead;

	/**
	 * 进入登录首页
	 * @author 真、二
	 * @date 15:38 2019/4/8
	 */
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public CallBackMsg login(HttpServletRequest request){

		try {
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");

			if(StringUtil.isEmpty(userName) || StringUtil.isEmpty(password)){
				return CallBackMsgUtils.invalidParam();
			}
			String token = mmallUserSerice.login(userName,password);

			if (StringUtil.isEmpty(token)) {
				return CallBackMsgUtils.errorPassword();
			}
			Map<String, String> tokenMap = new HashMap<>();
			tokenMap.put("token", token);
			tokenMap.put("tokenHead", tokenHead);

			//登录成功后进入首页，这里页面，就暂时把数据返回给页面
			return CallBackMsgUtils.setRsSucess(tokenMap);

		} catch (Exception e) {
			e.printStackTrace();
			return CallBackMsgUtils.setResult(null,e.getMessage(),400);
		}
	}

}
