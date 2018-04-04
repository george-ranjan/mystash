package org.ranjangeorge.mystash.service.api;

import org.jetbrains.annotations.NotNull;
import org.ranjangeorge.mystash.service.api.support.Usecase;
import org.ranjangeorge.mystash.service.api.support.UsecaseName;
import org.ranjangeorge.mystash.service.impl.email.EmailService;

public interface IEmailService {

    @UsecaseName(value = Usecase.SEND_EMAIL)
    void sendEmail(
            @NotNull String from,
            @NotNull String to,
            @NotNull String subject,
            @NotNull String text);

    default void sendEmail(
            @NotNull String to,
            @NotNull String subject,
            @NotNull String text) {

        sendEmail(
                "george.ranjan@gmail.com",
                to, subject, text);
    }

    static IEmailService getEmailService() {
        return new EmailService();
    }
}
