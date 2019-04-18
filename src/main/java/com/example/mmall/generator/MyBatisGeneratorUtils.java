package com.example.mmall.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * 生成逆向工程方法
 * @author zengyi
 * @time 2019-3-26
 */
public class MyBatisGeneratorUtils {

    public void generator() throws Exception{

        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        //指定 逆向工程配置文件

        String filePath = MyBatisGeneratorUtils.class.getResource("/mybatis-generator.xml").getPath();
        System.out.println("文件路径地址：====》》》》》 "+filePath);

        File configFile = new File(filePath);
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

    public static void main(String[] args) throws Exception {
        try {
            MyBatisGeneratorUtils generatorSqlmap = new MyBatisGeneratorUtils();
            generatorSqlmap.generator();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
