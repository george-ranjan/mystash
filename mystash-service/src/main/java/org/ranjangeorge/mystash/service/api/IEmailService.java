package org.ranjangeorge.mystash.service.api;

import org.jetbrains.annotations.NotNull;
import org.ranjangeorge.mystash.service.api.support.Usecase;
import org.ranjangeorge.mystash.service.api.support.UsecaseName;

import javax.mail.MessagingException;

public interface IEmailService {

    @UsecaseName(value = Usecase.SEND_EMAIL)
    void sendEmail(
            @NotNull String from,
            @NotNull String to,
            @NotNull String subject,
            @NotNull String text)
            throws MessagingException;
}
