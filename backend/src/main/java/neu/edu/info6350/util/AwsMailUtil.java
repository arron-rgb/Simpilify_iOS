package neu.edu.info6350.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

/**
 * @author arronshentu
 */
public class AwsMailUtil {
  private static final String password = "BOunsCKAP5luiym7p53eGqAwM8o0QKYU83VvmlELSEYu";
  private static final String from = "notify@prod.xinyapp.me";
  private static final String username = "AKIA2EQZ22POX4NXP3HR";
  private static final String host = "email-smtp.us-east-1.amazonaws.com";
  private static final int port = 587;

  public void sendMail(String subject, String body, String receiver) {
    Properties properties = System.getProperties();
    properties.put("mail.smtp.port", port);
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.transport.protocol", "smtp");

    Session session = Session.getDefaultInstance(properties);

    MimeMessage msg = new MimeMessage(session);
    try {
      msg.setFrom(new InternetAddress(from, "Notifier"));
      msg.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
      msg.setSubject(subject);
      msg.setContent(body, "text/html");
      Transport transport = session.getTransport();
      transport.connect(host, username, password);
      transport.sendMessage(msg, msg.getAllRecipients());
    } catch (MessagingException | UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }

  }

  public static void main(String[] args) {
    AwsMailUtil mailUtil = new AwsMailUtil();
    mailUtil.sendMail("hello", "body", "shentu.k@northeastern.edu");
  }
}
