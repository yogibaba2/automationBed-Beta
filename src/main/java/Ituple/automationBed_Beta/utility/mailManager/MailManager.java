package Ituple.automationBed_Beta.utility.mailManager;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import Ituple.automationBed_Beta.utility.ReportManager.AdvanceReporting;
import Ituple.automationBed_Beta.utility.base.Utils;

public class MailManager {
	
	public static void sendMail(){
		
		
		String host=MailManagerConfig.getString("mm.host");  
		String user=MailManagerConfig.getString("mm.user");
		String password=MailManagerConfig.getString("mm.password");   
		
		String to=MailManagerConfig.getString("recepient");  
		  
		   //Get the session object  
		   Properties props = new Properties();  
		   props.put("mail.smtp.host",host); 
		   props.put("mail.smtp.socketFactory.port", MailManagerConfig.getString("mm.socketFactory.port"));  
		   props.put("mail.smtp.socketFactory.class", MailManagerConfig.getString("mm.smtp.socketFactory"));  
		   props.put("mail.smtp.socketFactory.fallback", "true");
		   props.put("mail.smtp.auth", "true");  
		   props.put("mail.smtp.starttls.enable", "true");
		   props.put("mail.smtp.port", MailManagerConfig.getString("mm.smtp.port"));  
		     
		   Session session = Session.getDefaultInstance(props,  
		    new javax.mail.Authenticator() {  
		      protected PasswordAuthentication getPasswordAuthentication() {  
		    return new PasswordAuthentication(user,password);  
		      }  
		    });  
		  
		   //Compose the message  
		    try {  
		     MimeMessage message = new MimeMessage(session); 
		     MimeBodyPart messageBody = new MimeBodyPart();
		     
		     message.setFrom(new InternetAddress(user));  
		     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
		     message.setSubject(MailManagerConfig.getString("subject")); 
		    
		     File file = new File(AdvanceReporting.reportPath);
	         messageBody.attachFile(file);
	         MimeMultipart multipart= new MimeMultipart();
	         multipart.addBodyPart(messageBody);
	         
	         message.setContent(multipart); 
		     Transport.send(message);  
		  
		     Utils.addLog.info("Report mailed successfully...");  
		   
		     } 
		    catch (MessagingException e) {e.printStackTrace();} 
		    catch (IOException e) {e.printStackTrace();}  
		 
	}
}
