package com.example.mmall.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 真、二
 * @date 14:19 2019/5/17
 */
public class PinYinUtil {
	private static Map<String, List<String>> pinyinMap = new HashMap<String, List<String>>();

	public static void main(String[] args) {
		String str = "覃啊";
		//      String str = "龙港巷店";
		initPinyin("E:\\duoyinzi_dic.txt");
		String py = getFirstLetter(str);
		System.out.println(str + " = " + py);
	}

	//获取中文的首字母
	public static String getFirstLetter(String ChineseLanguage){
		char[] cl_chars = ChineseLanguage.trim().toCharArray();

		String hanyupinyin = "";
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);// 输出拼音全部大写
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
		try {
			String str = String.valueOf(cl_chars[0]);
			if (str.matches("^[\u2E80-\u9FFF]+$")) {// 如果字符是中文,则将中文转为汉语拼音,并取第一个字母
				String[] chars = PinyinHelper.toHanyuPinyinStringArray(cl_chars[0], defaultFormat) ;
				if(chars==null){
					hanyupinyin="#";
				}else{
					int len = chars.length;
					if(len == 1){
						// 不是多音字
						hanyupinyin=chars[0].substring(0, 1);
					}else if(len > 2){
						//非多音字 有多个音，取第一个
						hanyupinyin=chars[0].substring(0, 1);
					}else{
						//多音字
						hanyupinyin=chars[1].substring(0, 1);
						//System.out.println("多音字拼音===》》》："+hanyupinyin.toString());
					}

				}
			} else if (str.matches("[0-9]+")) {// 如果字符是数字,取数字
//          hanyupinyin+="*";
				hanyupinyin+=(cl_chars[0]);
			} else if (str.matches("[a-zA-Z]+")) {// 如果字符是字母,取字母 [a-zA-Z]+

				hanyupinyin += (cl_chars[0]);
				hanyupinyin = hanyupinyin.toUpperCase();

			} else {// 否则不转换

				hanyupinyin +="#";
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			System.out.println("字符不能转成汉语拼音");
		}
		return hanyupinyin;
	}


	public static void initPinyin(String fileName) {

//		InputStream file = PinYinUtil.class.getResourceAsStream(fileName);
//		BufferedReader br = new BufferedReader(new InputStreamReader(file));
		BufferedReader br = null;
		try {
			// 读取多音字的全部拼音表;
			File file = new File(fileName);
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
			String s = null;
			while ((s = br.readLine()) != null) {
				if (s != null) {
					String[] arr = s.split("#");
					String pinyin = arr[0];
					String chinese = arr[1];
					if(chinese!=null){
						String[] strs = chinese.split(" ");
						List<String> list = Arrays.asList(strs);
						pinyinMap.put(pinyin, list);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
