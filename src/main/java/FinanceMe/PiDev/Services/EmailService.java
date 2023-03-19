package FinanceMe.PiDev.Services;

public interface EmailService {
    void sendEmail(String to, String subject, String text);
}
