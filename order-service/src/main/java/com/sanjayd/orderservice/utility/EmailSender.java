package com.sanjayd.orderservice.utility;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
public class EmailSender {
	// SMTP server information
    static String host = "smtp.gmail.com";
    static String port = "587";
    static String mailFrom = "sanjay.dhonde@gmail.com";
    static String password = "xxxxxx";//set password here
    private static final Logger logger = LoggerFactory.getLogger(EmailSender.class);
    
 
    public static void sendVerifyEmail(String toAddress,String token) {
    	String subject = "Email Verification";
    	// message contains HTML markups
        String message = "<html>\n" +
                "<body>\n" +
                "\n" +
                "<a href=\"http://localhost:8080/services/verifyEmail?tok="+token+"&email="+toAddress+"\">\n" +
                "Verify email address.</a>\n" +
                "\n" +
                "</body>\n" +
                "</html>";
 
        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
 
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailFrom, password);
            }
        };
 
        Session session = Session.getInstance(properties, auth);
 
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
        
        try {
			msg.setFrom(new InternetAddress(mailFrom));
			InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
	        msg.setRecipients(Message.RecipientType.TO, toAddresses);
	        msg.setSubject(subject);
	        msg.setSentDate(new Date());
	        // set plain text message
	        msg.setContent(message, "text/html");
	 
	        // sends the e-mail
	        Transport.send(msg);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());;
		}
         
    }
    
    public static void sendEmail(String toAddress, String subject, String message) {
 
        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
 
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailFrom, password);
            }
        };
 
        Session session = Session.getInstance(properties, auth);
 
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
        
        try {
			msg.setFrom(new InternetAddress(mailFrom));
			InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
	        msg.setRecipients(Message.RecipientType.TO, toAddresses);
	        msg.setSubject(subject);
	        msg.setSentDate(new Date());
	        // set plain text message
	        msg.setContent(message, "text/html");
	 
	        // sends the e-mail
	        Transport.send(msg);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());;
		}
        
 
    }

}