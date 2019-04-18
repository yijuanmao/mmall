package com.example.mmall.util;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.ParseException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 真、二
 * @date 11:19 2019/4/10
 */
public class jwt {

	/**
	 * 1.创建一个32-byte的密匙
	 */

	private static final byte[] secret = "geiwodiangasfdjsikolkjikolkijswe".getBytes();

	//生成一个token
	public static String creatToken(Map<String,Object> payloadMap) throws JOSEException {

		//3.先建立一个头部Header
		/**
		 * JWSHeader参数：1.加密算法法则,2.类型，3.。。。。。。。
		 * 一般只需要传入加密算法法则就可以。
		 * 这里则采用HS256
		 *
		 * JWSAlgorithm类里面有所有的加密算法法则，直接调用。
		 */
		JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);

		//建立一个载荷Payload
		Payload payload = new Payload(new JSONObject(payloadMap));

		//将头部和载荷结合在一起
		JWSObject jwsObject = new JWSObject(jwsHeader, payload);

		//建立一个密匙
		JWSSigner jwsSigner = new MACSigner(secret);

		//签名
		jwsObject.sign(jwsSigner);

		System.out.println("token :"+jwsObject.serialize());
		//生成token
		return jwsObject.serialize();
	}

	//解析一个token
	public static Map<String,Object> valid(String token) throws Exception {

//      解析token
		JWSObject jwsObject = JWSObject.parse(token);

		//获取到载荷
		Payload payload=jwsObject.getPayload();

		//建立一个解锁密匙
		JWSVerifier jwsVerifier = new MACVerifier(secret);

		Map<String, Object> resultMap = new HashMap<>();
		//判断token
		if (jwsObject.verify(jwsVerifier)) {
			resultMap.put("Result", 0);
			//载荷的数据解析成json对象。
			JSONObject jsonObject = payload.toJSONObject();
			resultMap.put("data", jsonObject);

			//判断token是否过期
			if (jsonObject.containsKey("exp")) {
				Long expTime = Long.valueOf(jsonObject.get("exp").toString());
				Long nowTime = new Date().getTime();
				//判断是否过期
				if (nowTime > expTime) {
					//已经过期
					resultMap.clear();
					resultMap.put("Result", 2);

				}
			}
		}else {
			resultMap.put("Result", 1);
		}
		return resultMap;

	}


	public static void main(String[] args) throws Exception{
	/*	Map<String, Object> userMap = new HashMap<String, Object>();
		userMap.put("username","824414828");
		userMap.put("email","824414828@qq.com");
		userMap.put("phone","15697360503");
		userMap.put("role","0");
		creatToken(userMap);*/
	String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiMCIsInBob25lIjoiMTU2OTczNjA1MDMiLCJlbWFpbCI6IjgyNDQxNDgyOEBxcS5jb20iLCJ1c2VybmFtZSI6IjgyNDQxNDgyOCJ9.TgD0EkBFKoTfo323tW4YXs2_-TaKiRBpBTREN9wbXDs";
		System.out.println("==>"+valid(token));;
	}
}
