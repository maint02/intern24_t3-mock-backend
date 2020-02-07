package com.itsol.train.mock.service.impl;

import com.itsol.train.mock.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@Slf4j
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendActivationEmail(String receiverEmail, String username, String originalPass) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "utf-8");

        try {
            String htmlMsg = "<h3>Activation Email</h3>"
                    + "<div>You've created account successfully!<br> " +
                    "Your default password is <strong>" + originalPass + "</strong>. Use this password to login our system." +
//                    "<br>Before login, you must click <a href='http://192.168.0.103:8008/smart_office/api/user/get-employee-info/" + username + "'>here</a> to active your account. </div>";
                    "<br>Before login, you must click <a href='http://172.1.2.95:8008/smart_office/api/user/get-employee-info/" + username + "'>here</a> to active your account. </div>";
            mimeMessageHelper.setText(htmlMsg, true);
            mimeMessageHelper.setTo(receiverEmail);
            mimeMessageHelper.setSubject("Smart Office - Activation Email");
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        simpleMailMessage.setTo(receiverEmail);
//        simpleMailMessage.setSubject("Smart Office - Activation Email");
//        simpleMailMessage.setText("You've created account successfully! Your default password is " + originalPass + ". Click here to active your account: http://192.168.0.103:8008/smart_office/api/user/get-employee-info/" + username);
//        javaMailSender.send(simpleMailMessage);
    }

    @Override
    public void sendResetPassword(String receiverEmail, String password) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(receiverEmail);
        simpleMailMessage.setSubject("Smart Office - Reset password email");
        simpleMailMessage.setText("Your new password is " + password + ", please use it to login again");
        javaMailSender.send(simpleMailMessage);
    }


}
