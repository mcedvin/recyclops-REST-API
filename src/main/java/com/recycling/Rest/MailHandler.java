package com.recycling.Rest;

import com.recycling.production.Report;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailHandler {
    public boolean sendMail(Report report) {

        // Credential for email
        final String username = "iotrpiproject@gmail.com";
        final String password = "pvt15mail";

        // Set properties for smtp
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.sGETmtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Create authentication with login details
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            // Create message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("iotrpiproject@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("iotrpiproject@gmail.com"));
            message.setSubject("Rapport av felanmäld miljöstation");
            message.setText(report.toString());

            // Send message
            Transport.send(message);

            System.out.println("Email sent!");
            return true;

        } catch (MessagingException e) {

            // Print sending error
            System.out.println(e);
            return false;

            // throw new RuntimeException(e);
        }

    }
}
