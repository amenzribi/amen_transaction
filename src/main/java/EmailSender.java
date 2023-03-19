/*import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import FinanceMe.PiDev.Services.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {
/*
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendVerificationCode(String to, String verificationCode) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("your-email@example.com");
        helper.setTo(to);
        helper.setSubject("Verification Code");
        helper.setText("Your verification code is: " + verificationCode);
        javaMailSender.send(message);
    }

}

 */

