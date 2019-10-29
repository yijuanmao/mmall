package com.example.mmall.util;
import com.example.mmall.config.MailProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 邮件工具
 * @author zhener
 * @date 15:14 2019/10/28
 */
@Slf4j
@Component
public class EmailUtil {

	@Autowired
	MailProperties mailProperties;

	/**
	 * 生成邮件
	 * @author zhener
	 * @param	content 邮箱内容
	 * @param	toEmail 接收人（多个英文逗号隔开：123456@qq.com,123456@qq.com）
	 * @throws Exception
	*/
	public void sendEmail(String toEmail,String content) throws Exception {

		String host = mailProperties.getHost();
		Integer port = mailProperties.getPort();
		String username = mailProperties.getUserName();
		String password = mailProperties.getPassword();
		String subject = mailProperties.getSubject();

		Properties properties = new Properties();
		properties.setProperty("mail.transport.protocol", "smtp"); // 使用smtp协议
		properties.setProperty("mail.smtp.host",host); // 发件人邮箱
		properties.setProperty("mail.smtp.auth", "true"); // 请求认证
		properties.setProperty("mail.smtp.port", String.valueOf(port));
		// 开启 SSL 安全连接
		properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.smtp.socketFactory.fallback", "false");
		properties.setProperty("mail.smtp.socketFactory.port", String.valueOf(port));

		Authenticator auth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username,password);
			}
		};

		Session session = Session.getInstance(properties,auth); //第二种写法
		MimeMessage mimeMessage = new MimeMessage(session);
		mimeMessage.setFrom(username);
		mimeMessage.setSubject(subject);	//主题
		mimeMessage.setContent(content, "text/html;charset=utf-8");

		//TODO：批量发送多个收件人
		String[] arr = null;
		if(!StringUtil.isEmpty(toEmail)){
			arr = toEmail.split(",");
		}
		Address[] addresses = new Address[arr.length];
		for(int i=0;i<arr.length;i++){
			addresses[i]=new InternetAddress(arr[i]);
		}
		mimeMessage.addRecipients(Message.RecipientType.TO, addresses);

		//TODO：只发送一个收件人
//		mimeMessage.addRecipients(Message.RecipientType.TO, "824414828@qq.com");
//		mimeMessage.addRecipients(Message.RecipientType.CC, "linsenzhong@126.com");

		Transport transport = session.getTransport();
		transport.connect(host, username, password);
		transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
		transport.close();
	}



}
