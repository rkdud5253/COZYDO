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
	public void sendEmailTOPW(String toAddress, String subject, String body) {

		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		String msg = "";
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
	public void sendEmailToEmail(String toAddress, String subject, String authkey) {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		String msg = "";
		msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
		msg += "<h3 style='color: black;'>";
		msg += "<div style='font-size: 130%'>";
		msg += "회원님께 전송된 이메일 인증 링크입니다.<br/>";
		msg += "<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p><br/>";
		msg += "<a href='https://i4a201.p.ssafy.io/authentication?email="; //우리의 주소를 쓰자.
		msg += toAddress;
		msg += "&authkey=" + authkey;
		msg +="' target='_blenk'>이메일 인증 확인</a></div>";
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
	public String GetRandom() {
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
