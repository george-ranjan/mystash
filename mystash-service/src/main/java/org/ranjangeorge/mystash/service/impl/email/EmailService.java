package org.ranjangeorge.mystash.service.impl.email;

import org.jetbrains.annotations.NotNull;
import org.ranjangeorge.mystash.service.api.IEmailService;
import org.ranjangeorge.mystash.service.api.support.Usecase;
import org.ranjangeorge.mystash.service.api.support.UsecaseNames;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@UsecaseNames(Usecase.SEND_EMAIL)
public class EmailService implements IEmailService {

    private String mailhost = "smtp.gmail.com";

    private int mailport = 465;

    private String smtpuser = "george.ranjan@gmail.com";

    private String smtppassword = "!lucile123";

    @Override
    public void sendEmail(
            @NotNull final String from,
            @NotNull final String to,
            @NotNull final String subject,
            @NotNull final String text) {

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
        try {
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            msg.setSubject(subject);
            msg.setText(text);

            // send the thing off
            Transport.send(msg);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
