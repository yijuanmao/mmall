package com.example.mmall.util;

import java.util.UUID;

/**
 * 随机数生成工具类
 *
 * 广州市领课网络科技有限公司：www.roncoo.net
 * 
 * @author：shenjialong
 */
public class RandomUtil {

	/**
	 * 创建随机数(纯数字、数字+小写字母)
	 * 
	 * @param numberFlag
	 * @param length
	 * @return
	 */
	public static String createRandom(boolean numberFlag, int length) {
		String retStr = "";
		String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
		int len = strTable.length();
		boolean bDone = true;
		do {
			retStr = "";
			int count = 0;
			for (int i = 0; i < length; i++) {
				double dblR = Math.random() * len;
				int intR = (int) Math.floor(dblR);
				char c = strTable.charAt(intR);
				if (('0' <= c) && (c <= '9')) {
					count++;
				}
				retStr += strTable.charAt(intR);
			}
			if (count >= 2) {
				bDone = false;
			}
		} while (bDone);

		return retStr;
	}

	/**
	 * 创建随机数（数字+大小写字母）
	 * 
	 * @param length
	 * @return
	 */
	public static String createRandom(int length) {
		String str1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
		String str2 = "";
		int len = str1.length() - 1;
		double r;
		for (int i = 0; i < length; i++) {
			r = (Math.random()) * len;
			str2 = str2 + str1.charAt((int) r);
		}
		return str2;
	}

	public static String UUID32() {
		String str = UUID.randomUUID().toString();
		return str.replaceAll("-", "");
	}


}
