
package FinanceMe.PiDev.Services;

import FinanceMe.PiDev.Enteties.Compte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class IEmailService implements EmailService{
    @Autowired
    private JavaMailSender mailSender;


    @Autowired
    public IEmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;

    }


    @Override
    public void sendEmail(String to, String subject, String text) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new MailSendException("Failed to send email", e);
        }
    }

    @Override
    public void sendMail(String A, String body, String words) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(A);
            helper.setSubject(body);
            helper.setText(words, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new MailSendException("Failed to send email", e);
        }
    }
    @Override
    public void remindMail(String pour, String inside, String phrase) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(pour);
            helper.setSubject(inside);
            helper.setText(phrase, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new MailSendException("Failed to send email", e);
        }
    }

    @Override
    public void remindDest(String dest, String in, String texte) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(dest);
            helper.setSubject(in);
            helper.setText(texte, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new MailSendException("Failed to send email", e);
        }
    }
}


