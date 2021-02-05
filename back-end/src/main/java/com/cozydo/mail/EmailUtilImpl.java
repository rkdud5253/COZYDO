package com.cozydo.mail;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtilImpl implements EmailUtil {

	@Autowired
	private JavaMailSender sender;

	@Override
	public void sendEmail(String toAddress, String subject, String body) {

		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		String msg = "";
		msg += "<p><img src=\"http://localhost:8080/resources/cozydo.png\"></p>";// 아마존 이미지를 전송하게끔하자.
		msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
		msg += "<h3 style='color: black;'>";
		msg += "<div style='font-size: 130%'>";
		msg += "회원님께 전송된 임시 비밀번호 입니다.<br/>";
		msg += "비밀번호를 변경하여 사용하세요.</div><br/>";
		msg += "<p>임시 비밀번호 : ";
		msg += body + "</p></div>";
		try {
			helper.setTo(toAddress);
			helper.setSubject(subject);
			helper.setText(msg, true);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		sender.send(message);
	}

	@Override
	public String GetRandomPW() {
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
				'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		StringBuffer sb = new StringBuffer();
		int idx = 0;

		for (int i = 0; i < 10; i++) {
			idx = (int) (charSet.length * Math.random());
			sb.append(charSet[idx]);
		}
		String imsi = sb.toString();
		return imsi;
	}

}
