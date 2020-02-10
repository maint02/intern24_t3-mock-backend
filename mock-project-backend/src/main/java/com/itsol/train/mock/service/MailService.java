package com.itsol.train.mock.service;

public interface MailService {
    void sendActivationEmail(String receiverEmail, String username, String originalPass);

    void sendResetPassword(String receiverEmail, String password);

}
