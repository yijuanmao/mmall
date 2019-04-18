package com.example.mmall.util;

import com.example.mmall.config.SystemConfig;
import org.apache.commons.lang.StringUtils;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;

/**
 * Json Web Token 工具类
 * @author 真、二
 * @datetime 2019-04-09
 *
 */
@Service("jwtUtils")
public class JwtUtils {
	
	private	static final Logger log =LoggerFactory.getLogger(JwtUtils.class);

	private String privateKeyStr = SystemConfig.getProperty("jwt.rsa.privateKey");//私钥key
	
	private String pubicKeyStr = SystemConfig.getProperty("jwt.rsa.publicKey");//公钥key
	
	private Key privateKey;//公钥
	
	private Key publicKey;//私钥
	
	/**
	 * 生成token方法
	 * @param issUser 令牌的创建者 网站或应用clientId
	 * @param audience 令牌使用者 用户ID
	 * @param minutes 有效期时间 
	 * @param payLoadMap 令牌有效负载参数，即需要在token中保存的用户信息
	 * @author 真、二
	 * @return
	 * @throws JoseException
	 */
	public String generateToken(String issUser ,String audience , Float minutes , Map<String , Object> payLoadMap) throws JoseException{
		 if(StringUtils.isBlank(issUser)){//令牌创建者
			 log.info("令牌创建者为空");
			 return null;
		 }
		
		 if(StringUtils.isBlank(audience)){//令牌使用者
			 log.info("令牌使用者为空");
			 return null;
		 }
		 
		 if(minutes == null){
			 log.info("令牌有效时间为空");
			 return null;
		 }
		 
		 System.out.println();
		 
		 if(privateKey == null){
			 if(!getPrivateKey(privateKeyStr)){//读取私钥失败
				 log.info("读取私钥失败");
				 return null;
			 }
		 }
		 
		 JwtClaims claims = new JwtClaims();
		 claims.setIssuer(issUser);  // 令牌创建者
		 claims.setAudience(issUser); // 令牌使用者
		 claims.setExpirationTimeMinutesInTheFuture(minutes);//有效时间
		 claims.setGeneratedJwtId(); // 令牌ID，默认16
		 claims.setIssuedAtToNow();  // 令牌的发出时间（设置为）
		 claims.setNotBeforeMinutesInThePast(1); // time before which the token is not yet valid (2 minutes ago)
		 claims.setSubject("subject"); // the subject/principal is whom the token is about
		 
		 for (Map.Entry<String, Object> entry : payLoadMap.entrySet()) {
			   String key = entry.getKey().toString();
			   String value = entry.getValue() == null ? "" : entry.getValue().toString();
			   claims.setClaim(key,value);
			  }
		 claims.setClaim("audience",audience);//此处将用户编号作为payload传入

		JsonWebSignature jws = new JsonWebSignature(); // A JWT is a JWS and/or a JWE with JSON claims as the payload.
		jws.setPayload(claims.toJson());//设置json web token 的 payLoad

		jws.setKey(privateKey);//设置私钥
		jws.setKeyIdHeaderValue("k1");//key Id
		jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);
		String jwt = jws.getCompactSerialization();

		return jwt;
	}
	
	/**
	 * 获取刷新token的方法
	  * @param issUser 令牌的创建者 网站或应用clientId
	 * @param audience 令牌使用者 用户ID
	 * @param payLoadMap 令牌有效负载参数，即需要在token中保存的用户信息
	 * @return
	 * @throws JoseException
	 */
	public String generateRefreshToken(String issUser ,String audience , Map<String , Object> payLoadMap) throws JoseException{
		 if(StringUtils.isBlank(issUser)){//令牌创建者
			 log.info("令牌创建者为空");
			 return null;
		 }
		
		 if(StringUtils.isBlank(audience)){//令牌使用者
			 log.info("令牌使用者为空");
			 return null;
		 }
		 
		 if(privateKey == null){
			 if(!getPrivateKey(privateKeyStr)){//读取私钥失败
				 log.info("读取私钥失败");
				 return null;
			 }
		 }
		 
		 JwtClaims claims = new JwtClaims();
		 claims.setIssuer(issUser);  // 令牌创建者
		 claims.setAudience(issUser); // 令牌使用者
//		 claims.setExpirationTimeMinutesInTheFuture(0);//设置为0 
		 claims.setGeneratedJwtId(); // 令牌ID，默认16
		 claims.setIssuedAtToNow();  // 令牌的发出时间（设置为）
		 claims.setNotBeforeMinutesInThePast(2); // time before which the token is not yet valid (2 minutes ago)
		 claims.setSubject("subject"); // the subject/principal is whom the token is about
		 
		 for (Map.Entry<String, Object> entry : payLoadMap.entrySet()) {
			   String key = entry.getKey().toString();
			   String value = entry.getValue().toString();
			   claims.setClaim(key,value);
			  }
		 claims.setClaim("audience",audience);//此处将用户编号作为payload传入

		 JsonWebSignature jws = new JsonWebSignature(); // A JWT is a JWS and/or a JWE with JSON claims as the payload.
		 jws.setPayload(claims.toJson());//设置json web token 的 payLoad
		 
		  jws.setKey(privateKey);//设置私钥
		  jws.setKeyIdHeaderValue("k1");//key Id
		  jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);
		  String jwt = jws.getCompactSerialization();
		  
		 return jwt;
	}
	
	/**
	 * token 校验方方
	 * @param token token串
	 * @param isUser token创建者
	 * @param audience token使用者
	 * @return 令牌有效负载参数
	 * @throws InvalidJwtException 
	 */
	public Map<String,Object> checkToken(String token ,String isUser ,String audience) throws InvalidJwtException{
		
		if(publicKey == null){
			 if(!getPublicKey(pubicKeyStr)){//读取公钥失败
				 log.info("读取公钥失败");
				 return null;
			 }
		 }
		
		JwtConsumer jwtConsumer = new JwtConsumerBuilder()
	            .setRequireExpirationTime() // the JWT must have an expiration time
	            .setAllowedClockSkewInSeconds(30) // allow some leeway in validating time based claims to account for clock skew
	            .setRequireSubject() // the JWT must have a subject claim
	            .setExpectedIssuer(isUser) // whom the JWT needs to have been issued by
	            .setExpectedAudience(audience) // to whom the JWT is intended for
	            .setVerificationKey(publicKey) // verify the signature with the public key
	            .build(); // create the JwtConsumer instance

	    
	        //  Validate the JWT and process it to the Claims
	        JwtClaims jwtClaims = jwtConsumer.processToClaims(token);
	        Map<String , Object> payLoadMap = jwtClaims.getClaimsMap();
	        log.info("token 校验成功" + payLoadMap);
	        return payLoadMap;
	   
	}
	
	/**
	 * RefreshToken 刷新token的校验方法
	 * @param refreshToken token串
	 * @param issUer token创建者
	 * @param audience token使用者
	 * @return 令牌有效负载参数
	 */
	public Map<String,Object> checkRefreshToken(String refreshToken ,  String issUer , String audience) throws InvalidJwtException {
		
		if(publicKey == null){
			 if(!getPublicKey(pubicKeyStr)){//读取公钥失败
				 log.info("读取公钥失败");
				 return null;
			 }
		 }
		
		JwtConsumer jwtConsumer = new JwtConsumerBuilder()
	            .setAllowedClockSkewInSeconds(30) // allow some leeway in validating time based claims to account for clock skew
	            .setRequireSubject() // the JWT must have a subject claim
	            .setExpectedIssuer(issUer) // whom the JWT needs to have been issued by
	            .setExpectedAudience(audience) // to whom the JWT is intended for
	            .setVerificationKey(publicKey) // verify the signature with the public key
	            .build(); // create the JwtConsumer instance

	        //  Validate the JWT and process it to the Claims
	        JwtClaims jwtClaims = jwtConsumer.processToClaims(refreshToken);
	        Map<String , Object> payLoadMap = jwtClaims.getClaimsMap();
	        log.debug("token 校验成功" + payLoadMap);
	        return payLoadMap;

	}
	
	/**
	 * 用来获取公钥
	 * @param publicKeyStr
	 * @return
	 */
	@SuppressWarnings("restriction")
	private boolean getPublicKey(String publicKeyStr){
        try {
        	byte[] keyBytes = (new BASE64Decoder()).decodeBuffer(publicKeyStr);
			 X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		        publicKey = (Key)keyFactory.generatePublic(keySpec);
		       return true;
		} catch (IOException e) {
			log.error("Io异常：",e);
		} catch (NoSuchAlgorithmException e) {
			log.error("NoSuchAlgorithmException：",e);
		} catch (InvalidKeySpecException e) {
			log.error("InvalidKeySpecException:", e);
		}
       return false;
	}
	
	/**
	 * 用来获取私钥
	 * @param privateKeyStr
	 * @return
	 */
	@SuppressWarnings("restriction")
	private boolean getPrivateKey(String privateKeyStr){
		try {
	        byte[] keyBytes;
	        keyBytes = (new BASE64Decoder()).decodeBuffer(privateKeyStr);
	        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
	        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	        privateKey = (Key)keyFactory.generatePrivate(keySpec);
	        return true;
		} catch (IOException e) {
			log.error("Io异常：",e);
		} catch (NoSuchAlgorithmException e) {
			log.error("NoSuchAlgorithmException：",e);
		} catch (InvalidKeySpecException e) {
			log.error("InvalidKeySpecException:", e);
		}
		return false;
	}

}
