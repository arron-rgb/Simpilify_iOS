package neu.edu.info6350.util;

import java.util.Properties;

import org.springframework.stereotype.Component;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

/**
 * @author arronshentu
 */
@Component
public class MailUtil {
  private static final String PASSWORD = "volplsfywlhteupg";
  private static final String FROM = "arronshentu@gmail.com";
  private static final String USERNAME = "arronshentu@gmail.com";
   private static final String HOST = "smtp.gmail.com";
//  private static final String HOST = "email-smtp.us-east-1.amazonaws.com";
   private static final String PORT = "465";
//  private static final String PORT = "587";

  public void sendMail(String subject, String body, String receiver) {
    Properties properties = System.getProperties();
    properties.put("mail.smtp.host", HOST);
    properties.put("mail.smtp.port", PORT);
    properties.put("mail.smtp.ssl.enable", "true");
    properties.put("mail.smtp.auth", "true");

    Session session = Session.getInstance(properties, new Authenticator() {
      public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(USERNAME, PASSWORD);
      }
    });

    try {
      MimeMessage message = new MimeMessage(session);
      message.setFrom(new InternetAddress(FROM));
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
      message.setSubject(subject);
      Multipart multipart = new MimeMultipart();
      MimeBodyPart textPart = new MimeBodyPart();
      textPart.setContent(body, "text/html; charset=utf-8");
      multipart.addBodyPart(textPart);
      message.setContent(multipart);
      Transport.send(message);
    } catch (MessagingException mex) {
      mex.printStackTrace();
      throw new RuntimeException("Mail sending failed");
    }
  }

}
