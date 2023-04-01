
package FinanceMe.PiDev.Services;

public interface EmailService {
    void sendEmail(String to, String subject, String text);
    void sendMail(String A, String body, String words);
    void remindMail(String For, String body, String words);
}


