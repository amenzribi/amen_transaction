
package FinanceMe.PiDev.Services;

import FinanceMe.PiDev.Enteties.Compte;

public interface EmailService {
    void sendEmail(String to, String subject, String text);
    void sendMail(String A, String body, String words);
    void remindMail(String pour , String inside, String phrase);
    void remindDest(String dest , String in, String texte);

}


