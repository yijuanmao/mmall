package com.example.mmall.common.oss;

import com.aliyun.oss.*;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import lombok.extern.slf4j.Slf4j;
import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.List;

/**
 * @author zhener
 * @date 16:17 2019/9/11
 */
@Slf4j
public class OssUtil {

	private static String endpoint = "xxxxxxxxxxxxxxx";
	private static String accessKeyId = "xxxxxxxxxxxxxxx";
	private static String accessKeySecret = "xxxxxxxxxxxxxxx";
	private static String bucketName = "xxxxxxxxxxxxxxx";
	private static String filedir = "xxxxxxxxxxxxxxx";

	// 您的回调服务器地址，如http://oss-demo.aliyuncs.com:23450或http://127.0.0.1:9090。
	String callbackUrl = "<yourCallbackServerUrl>";

	public static void main(String[] args) {
		// 创建OSSClient实例。
//		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

		try {
			if (ossClient.doesBucketExist(bucketName)) {
				System.out.println("您已经创建Bucket：" + bucketName + "。");
			} else {
				System.out.println("您的Bucket不存在，创建Bucket：" + bucketName + "。");
				ossClient.createBucket(bucketName);
			}

			// 上传内容到指定的存储空间（bucketName）并保存为指定的文件名称（objectName）。
			String fileKey = filedir +"/test.png";
			ossClient.putObject(bucketName, fileKey, new File("C:\\Users\\Administrator\\Downloads\\01_year_end_theme.png"));

			System.out.println("Object：" + fileKey + "存入OSS成功。");
			ObjectListing objectListing = ossClient.listObjects(bucketName);
			List<OSSObjectSummary> objectSummary = objectListing.getObjectSummaries();
			System.out.println("您有以下Object：");
			for (OSSObjectSummary object : objectSummary) {
				System.out.println("\t" + object.getKey());
			}

			// 设置URL过期时间为10年  3600l* 1000*24*365*10
			Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
			//获取文件地址
			URL url = ossClient.generatePresignedUrl(bucketName,fileKey,expiration);
			System.out.println("地址为：============》》》》》》》》》 "+url.toString());
			if(url.toString().indexOf("?")>= 0){
				System.out.println("======= " +url.toString().indexOf("?"));
				String newUrl = url.toString().substring(0,url.toString().indexOf("?"));
				System.out.println("新的地址为：、、、、、、、、、、、、、、、、"+newUrl);
			}
		} catch (OSSException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		}
		// 关闭OSSClient。
		ossClient.shutdown();
	}

}
