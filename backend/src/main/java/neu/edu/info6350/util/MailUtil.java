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
  private static final String password = "BOunsCKAP5luiym7p53eGqAwM8o0QKYU83VvmlELSEYu";
  private static final String from = "arronshentu@gmail.com";
  private static final String username = "AKIA2EQZ22POX4NXP3HR";
  // private static final String host = "smtp.gmail.com";
  private static final String host = "email-smtp.us-east-1.amazonaws.com";
  // private static final String port = "465";
  private static final String port = "587";

  public void sendMail(String subject, String body, String receiver) {
    Properties properties = System.getProperties();
    properties.put("mail.smtp.host", host);
    properties.put("mail.smtp.port", port);
    properties.put("mail.smtp.ssl.enable", "true");
    properties.put("mail.smtp.auth", "true");

    Session session = Session.getInstance(properties, new Authenticator() {
      public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
      }
    });

    try {
      MimeMessage message = new MimeMessage(session);
      message.setFrom(new InternetAddress(from));
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
      message.setSubject(subject);
      Multipart multipart = new MimeMultipart();
      MimeBodyPart textPart = new MimeBodyPart();
      textPart.setText(body);
      multipart.addBodyPart(textPart);
      message.setContent(multipart);
      Transport.send(message);
    } catch (MessagingException mex) {
      mex.printStackTrace();
    }
  }

  public static void main(String[] args) {
    MailUtil mailUtil = new MailUtil();
    mailUtil.sendMail("hello", "body", "shentu.k@northeastern.edu");
  }
}
