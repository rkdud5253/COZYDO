package com.cozydo.mail;

public interface EmailUtil {
	void sendEmail(String toAddress, String subject, String body);

	String GetRandomPW();
}
