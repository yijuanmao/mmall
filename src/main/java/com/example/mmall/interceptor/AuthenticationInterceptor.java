package com.example.mmall.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.example.mmall.annotation.PassToken;
import com.example.mmall.annotation.AuthToken;
import com.example.mmall.common.msg.CallBackMsg;
import com.example.mmall.service.user.MmallUserSerice;
import com.example.mmall.util.CallBackMsgUtils;
import com.example.mmall.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author 真、二
 * @date 18:16 2019/4/16
 */
@Slf4j
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	MmallUserSerice mmallUserSerice;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		String token = request.getHeader("token");// 从 http 请求头中取出 token

		// 如果不是映射到方法直接通过
		if(!(object instanceof HandlerMethod)){
			return true;
		}

		HandlerMethod handlerMethod=(HandlerMethod)object;
		Method method=handlerMethod.getMethod();
		//检查是否有passtoken注释，有则跳过认证
		if (method.isAnnotationPresent(PassToken.class)) {
			PassToken passToken = method.getAnnotation(PassToken.class);
			if (passToken.required()) {
				return true;
			}
		}else {
			CallBackMsg callBackMsg = CallBackMsgUtils.setResult(null,"请求失败！缺少token参数！",-1);
			writeErrorMsg(response,callBackMsg);
			return false;
		}

		//检查有没有需要用户权限的注解
		if (method.isAnnotationPresent(AuthToken.class)) {
			AuthToken userLoginToken = method.getAnnotation(AuthToken.class);
			if (userLoginToken.required()) {
				// 执行认证
				if(StringUtil.isEmpty(token)){
					String token1 = request.getParameter("token");
					if(StringUtil.isEmpty(token1)){
						CallBackMsg callBackMsg = CallBackMsgUtils.setResult(null,"请求失败！缺少token参数！",-1);
						writeErrorMsg(response,callBackMsg);
						return false;
					}
					token = token1;
				}

				// 获取 token 中的 用户数据
				String clientId = "S";
				Map<String, Object> json = mmallUserSerice.checkToken(clientId,token);
				if(!StringUtil.isEmpty(json)){
					Object userId = json.get("userId");
					Object role = json.get("role");

					if (userId != null) {
						request.setAttribute("userId", userId);
						request.setAttribute("role", role);
					}
				}else{
					CallBackMsg callBackMsg = CallBackMsgUtils.setResult(null,"鉴权失败！",-10);
					writeErrorMsg(response,callBackMsg);
					return false;
				}
				return true;
			}
		}
		return super.preHandle(request, response, object);
	}


	public void writeErrorMsg(HttpServletResponse response, Object obj) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter writer = response.getWriter();
//		log.info("=========>" + JSONObject.toJSONString(obj));
		writer.print(JSONObject.toJSONString(obj));
	}

	/**
	 * 该方法将在Controller执行之后，返回视图之前执行，ModelMap表示请求Controller处理之后返回的Model对象，所以可以在
	 * 这个方法中修改ModelMap的属性，从而达到改变返回的模型的效果。
	 */
	@Override
	public void postHandle(HttpServletRequest httpServletRequest,
						   HttpServletResponse httpServletResponse,
						   Object o, ModelAndView modelAndView) throws Exception {
	}

	/**
	 * 该方法将在整个请求完成之后，也就是说在视图渲染之后进行调用，主要用于进行一些资源的释放
	 */
	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest,
								HttpServletResponse httpServletResponse,
								Object o, Exception e) throws Exception {
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		super.afterConcurrentHandlingStarted(request, response, handler);
	}

}
