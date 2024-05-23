package uz.app.service;

import lombok.SneakyThrows;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class SimpleMailSender {
   String sender = "abror01042001@gmail.com";

    @SneakyThrows
    public void sendSmsToUser(String email, String text) {
        String subject = String.format("<h1>Hello dear student! This is code to confirm your account<a href=https://kun.uz> %s<a/>, please do not share this code with anyone</h1>", text);
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender, "wdwtlgydgnwtlopp");
            }
        };
        Session session = Session.getInstance(properties, auth);

        Message message = new MimeMessage(session);
        message.setContent(subject, "text/html");
        message.setFrom(new InternetAddress(sender));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
        message.setSubject(text);

        Multipart multipart = new MimeMultipart();

        BodyPart bodyPart=new MimeBodyPart();
        bodyPart.setText("this is just test content");
        DataSource dataSource = new FileDataSource("D:\\image.jpg");
        bodyPart.setDataHandler(new DataHandler(dataSource));
        bodyPart.setFileName("image.jpg");
        multipart.addBodyPart(bodyPart);
        message.setContent(multipart);

        Transport.send(message);
    }

    @SneakyThrows
    public void sendMedia(String contenct, String path) {
        MimeMultipart mimeMultipart = new MimeMultipart();
        BodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent(mimeMultipart);
    }
}
