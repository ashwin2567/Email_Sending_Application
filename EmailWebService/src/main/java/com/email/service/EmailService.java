package com.email.service;

import java.io.File;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EmailService {

	public boolean sendEmail(String subject, String msgContent, String to) {
		boolean flag = false;
		String from = "devil20270211@gmail.com";
		
		String host = "smtp.gmail.com";
		
		//step 1
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		//Step 2
		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("ashwin2567@gmail.com", "hajjtxayralhzxmb");
			}
			
		});
		session.setDebug(true);
		//Step 3 compose message
		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(from));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			msg.setSubject(subject);
			msg.setText(msgContent);
		
		//step 4
		Transport.send(msg);
		System.out.println("sent");
		flag = true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean sendEmailWithAttachment(String subject, String msgContent, String to) {
		boolean flag = false;
		String from = "devil20270211@gmail.com";
		
		String host = "smtp.gmail.com";
		
		//step 1
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		//Step 2
		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("ashwin2567@gmail.com", "hajjtxayralhzxmb");
			}
			
		});
		session.setDebug(true);
		//Step 3 compose message
		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(from));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			msg.setSubject(subject);
			//msg.setText(msgContent);
			String fileToAttach = "C:\\Users\\akumarverma\\Pictures\\Screenshots\\a.png";
			MimeMultipart mimeMultiPart = new MimeMultipart();
			MimeBodyPart textMime =new MimeBodyPart();
			textMime.setText(msgContent);
			MimeBodyPart fileMime =new MimeBodyPart();
			File file = new File(fileToAttach);
			fileMime.attachFile(file);
			
			mimeMultiPart.addBodyPart(fileMime);
			mimeMultiPart.addBodyPart(textMime);
			msg.setContent(mimeMultiPart);
		
		//step 4
		Transport.send(msg);
		System.out.println("sent");
		flag = true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}


