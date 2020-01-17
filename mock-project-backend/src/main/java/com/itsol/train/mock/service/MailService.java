package com.itsol.train.mock.service;

public interface MailService {
    void sendActivationEmail(String toEmail, String username, String activeToken);

    void sendResetPassword(String toEmail, String password);

}
