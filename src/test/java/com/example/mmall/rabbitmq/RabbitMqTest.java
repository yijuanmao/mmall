package com.example.mmall.rabbitmq;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.mmall.MmallApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import java.io.UnsupportedEncodingException;

/**
 * rabbitMq 测试方法
 * @author zhener
 * @date 16:49 2019/10/24
 */
@Slf4j
public class RabbitMqTest extends MmallApplicationTests {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	@Autowired
	private Environment env;

	@Value("${mq.env}")
	String mqEnv;

	@Test
	public void setRabbitTemplate() {

		try {
			System.out.println(mqEnv);
//			rabbitTemplate.setExchange(env.getProperty("mmall.exchange.name"));
//			rabbitTemplate.setRoutingKey(env.getProperty("mmall.routing.key.name"));
//			rabbitTemplate.convertAndSend(MessageBuilder.withBody("mmall发送".getBytes("utf-8")).build());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Test
	public void getJson(){

		String str = "{\n" +
				"    \"table\": {\n" +
				"        \"checkbox\": false,\n" +
				"        \"highTechIndustry\": [\n" +
				"            \n" +
				"        ],\n" +
				"        \"nationalEconomyIndustry\": [\n" +
				"            [\n" +
				"                {\n" +
				"                    \"label\": \"所有行业\",\n" +
				"                    \"value\": 0\n" +
				"                },\n" +
				"                {\n" +
				"                    \"id\": 1312,\n" +
				"                    \"name\": \"金融业\",\n" +
				"                    \"label\": \"金融业\",\n" +
				"                    \"value\": 1312,\n" +
				"                    \"category\": \"J\",\n" +
				"                    \"bigCategory\": \"\",\n" +
				"                    \"explanation\": \"本门类包括66～69大类\",\n" +
				"                    \"smallCategory\": \"\",\n" +
				"                    \"mediumCategory\": \"\"\n" +
				"                },\n" +
				"                {\n" +
				"                    \"id\": 1332,\n" +
				"                    \"name\": \"资本市场服务\",\n" +
				"                    \"label\": \"资本市场服务\",\n" +
				"                    \"value\": 1332,\n" +
				"                    \"category\": \"\",\n" +
				"                    \"bigCategory\": 67,\n" +
				"                    \"explanation\": \"\",\n" +
				"                    \"smallCategory\": \"\",\n" +
				"                    \"mediumCategory\": \"\"\n" +
				"                },\n" +
				"                {\n" +
				"                    \"id\": 1337,\n" +
				"                    \"name\": \"非公开募集证券投资基金\",\n" +
				"                    \"label\": \"非公开募集证券投资基金\",\n" +
				"                    \"value\": 1337,\n" +
				"                    \"category\": \"\",\n" +
				"                    \"bigCategory\": \"\",\n" +
				"                    \"explanation\": \"\",\n" +
				"                    \"smallCategory\": \"\",\n" +
				"                    \"mediumCategory\": 673\n" +
				"                },\n" +
				"                {\n" +
				"                    \"id\": 1338,\n" +
				"                    \"name\": \"创业投资基金\",\n" +
				"                    \"label\": \"创业投资基金\",\n" +
				"                    \"value\": 1338,\n" +
				"                    \"category\": \"\",\n" +
				"                    \"bigCategory\": \"\",\n" +
				"                    \"explanation\": \"\",\n" +
				"                    \"smallCategory\": 6731,\n" +
				"                    \"mediumCategory\": \"\"\n" +
				"                }\n" +
				"            ]\n" +
				"        ],\n" +
				"        \"strategicEmergingIndustry\": [\n" +
				"            \n" +
				"        ]\n" +
				"    },\n" +
				"    \"tableStatus\": {\n" +
				"        \"highTechIndustry\": false,\n" +
				"        \"nationalEconomyIndustry\": true,\n" +
				"        \"strategicEmergingIndustry\": false\n" +
				"    }\n" +
				"}";



		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		JSONArray json = new JSONArray();

		jsonObject.put("aaa","bbb");

		jsonArray.add(jsonObject);
		json.add(jsonArray);

		System.out.println("只有一层jsonarr==========   >>>>>>>>>>>  "+jsonArray);
		System.out.println("两层==========》》》》》》 "+json);

	}
}
