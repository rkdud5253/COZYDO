package com.cozydo.mail;

public interface EmailUtil {
	void sendEmailTOPW(String toAddress, String subject, String body);

	void sendEmailToEmail(String toAddress, String subject, String body);

	String GetRandom();
}
