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
  private static final String password = "volplsfywlhteupg";
  private static final String from = "arronshentu@gmail.com";
  private static final String host = "smtp.gmail.com";

  public void sendMail(String subject, String body, String receiver) {
    Properties properties = System.getProperties();
    properties.put("mail.smtp.host", host);
    properties.put("mail.smtp.port", "465");
    properties.put("mail.smtp.ssl.enable", "true");
    properties.put("mail.smtp.auth", "true");

    Session session = Session.getInstance(properties, new Authenticator() {
      public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(from, password);
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
}
