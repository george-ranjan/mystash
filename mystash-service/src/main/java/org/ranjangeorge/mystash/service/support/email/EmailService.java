package org.ranjangeorge.mystash.service.support.email;

import org.jetbrains.annotations.NotNull;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * String mailhost = "smtp.gmail.com";
 * int mailport = 465;
 * String smtpuser = "george.ranjan@gmail.com";
 * String smtppassword = XXX;
 */
public class EmailService {

    private String mailhost;

    private int mailport;

    private String smtpuser;

    private String smtppassword;

    public EmailService(
            @NotNull final String mailhost,
            final int mailport,
            @NotNull final String smtpuser,
            @NotNull final String smtppassword) {

        this.mailhost = mailhost;
        this.mailport = mailport;
        this.smtpuser = smtpuser;
        this.smtppassword = smtppassword;
    }

    public void sendEmail(
            @NotNull final String from,
            @NotNull final String to,
            @NotNull final String subject,
            @NotNull final String text)
            throws MessagingException {

        // Get a Session object
        Properties props = new Properties();
        props.put("mail.smtp.host", mailhost);
        props.put("mail.smtp.socketFactory.port", mailport);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", mailport);

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(smtpuser, smtppassword);
                    }
                });
        session.setDebug(true);

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
        msg.setSubject(subject);
        msg.setText(text);

        // send the thing off
        Transport.send(msg);
    }
}
