package FinanceMe.PiDev.Services;

import FinanceMe.PiDev.Enteties.Compte;
import FinanceMe.PiDev.Enteties.Transaction;
import FinanceMe.PiDev.Enteties.TransactionState;
import FinanceMe.PiDev.Enteties.User;
import FinanceMe.PiDev.Repository.CompteRepository;
import FinanceMe.PiDev.Repository.TransactionRepository;
import FinanceMe.PiDev.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityNotFoundException;
import javax.websocket.Session;
import java.io.*;
import java.net.PasswordAuthentication;
import java.security.SecureRandom;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.mail.javamail.JavaMailSender;


@Slf4j
@EnableScheduling
@Service
public class TransactionService {



    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;
   @Autowired
   private EmailService emailService;


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void depot(Long compteDestinataire, float montant, String type_transaction) throws Exception {
        User user = userRepository.findById(compteDestinataire)
                .orElseThrow(() -> new Exception("Compte non trouvé"));
        float solde = user.getSolde() + montant;
        user.setSolde(solde);
        userRepository.save(user);
        Transaction transaction = new Transaction();
        transaction.setCompteDestinataire(user);
        transaction.setMontant(montant);
        transaction.setDate(LocalDateTime.now());
        transaction.setType_transaction(type_transaction);
        transaction.setEtat(TransactionState.PENDING);

        String to = user.getEmail();
        String subject = "Transaction validation code";


        String code = new SecureRandom().ints(6, 0, 36)
                .mapToObj(i -> Integer.toString(i, 36))
                .collect(Collectors.joining())
                .toUpperCase();




        String text = "<html><body style='font-family: Arial, sans-serif;'>" +

                "<h2>Transaction Validation Code</h2>" +
                "<p>Hello " + user.getFirstName() + ",</p>" +

                "<p>Your transaction validation code is: <strong>" + code + "</strong></p>" +
                "<p>Please use this code to confirm your transaction on our website.</p>" +
                "<p>Thank you for choosing our bank!</p>" +
                "</body></html>";

        // String text = "Your transaction validation code is: " + code;
        emailService.sendEmail(to, subject, text);
        transaction.setValidationCode(code); // Save validation code in transaction
        transactionRepository.save(transaction);
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void validerTransaction(String codeValidation) throws Exception {
        Transaction transaction = transactionRepository.findByValidationCode(codeValidation)
                .orElseThrow(() -> new Exception("Transaction not found"));
        if (!transaction.getValidationCode().equals(codeValidation)) {
            throw new Exception("Invalid validation code");
        }
        if (transaction.getEtat() != TransactionState.PENDING) {
            throw new Exception("Transaction is not pending validation");
        }
        LocalDateTime expirationDate = transaction.getDate().plusDays(1);
        if (expirationDate.isBefore(LocalDateTime.now())) {
            transaction.setEtat(TransactionState.CANCELED);
            transactionRepository.save(transaction);
            throw new Exception("Validation period expired, transaction canceled");
        }
        transaction.setEtat(TransactionState.VALIDATED);
        transactionRepository.save(transaction);
    }



    private static final Logger logger = LogManager.getLogger(TransactionService.class);
    @Scheduled(cron = "0 */7 * * * *") // toutes les 7 minutes    //@Scheduled(cron = "0 0 8 * * *") //every day at 8h morning
    public void annulerTransactionsExpirées() {
        try {
            List<Transaction> transactionsEnAttente = transactionRepository.findByEtat(TransactionState.PENDING);
            LocalDateTime maintenant = LocalDateTime.now();
            for (Transaction transaction : transactionsEnAttente) {
                LocalDateTime dateTransaction = transaction.getDate();
                Duration duréeDepuisValidation = Duration.between(dateTransaction, maintenant);
                if (duréeDepuisValidation.toMinutes() >= 5) { // Vérifier si la durée est supérieure ou égale à 5 minutes
                    transaction.setEtat(TransactionState.CANCELED);
                    transactionRepository.save(transaction);
                    logger.info("La transaction avec l'ID " + transaction.getId() + " a été annulée avec succès.");
                }
            }
            logger.info("Les transactions expirées ont été vérifiées avec succès.");
        } catch (Exception e) {
            logger.error("Une exception s'est produite lors de l'exécution de la méthode annulerTransactionsExpirées : " + e.getMessage());
        }
    }

private static final Logger loggerRemind = LogManager.getLogger(TransactionService.class);

    @Transactional
    @Scheduled(cron = "0 */3 * * * *") //every 2 minutes // @Scheduled(cron = "0 0 17 * * ?")every day at 17h
    public void remindUsersOfPendingTransactions() {
        try {
            List<Transaction> pendingTransactions = transactionRepository.findByEtat(TransactionState.PENDING);

            for (Transaction transaction : pendingTransactions) {

                User compteEmmetteur = transaction.getCompteEmetteur();

               // Compte compteDesstinataire = transaction.getCompteDestinataire();

                String sender = compteEmmetteur.getFirstName();
              //  String recipient = compteDesstinataire.getNom();

                //mail to sender (Emetteur)
                String pour = compteEmmetteur.getEmail();
                String inside = "Reminder: Pending Transaction";

                String phrase = "Dear " + sender + " ,\n\nYou have a pending transaction. Please validate or cancel it. Thank you.";


//                //send mail to the destinataire (deposit & withdraw)
//                emailService.sendMail(pour, inside, phrase);
//                String dest = compteDesstinataire.getEmail();
//                String in = "Reminder: Pending Transaction";
//
//                String texte = "Dear "+ recipient +"  ,\n\nYou have a pending transaction.  Please validate or cancel it. Thank you.";
//                emailService.remindDest(dest, in, texte);

                loggerRemind.info("Reminder email sent successfully for transaction with ID " + transaction.getId());
            }

            loggerRemind.info("Pending transactions reminder email job executed successfully.");
        } catch (Exception e) {
            loggerRemind.error("An exception occurred while executing the pending transactions reminder email job: ");
            e.printStackTrace();
        }
    }





    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void retrait(Long compteDestinataire, float montant , String type_transaction) throws Exception {
        User user = userRepository.findById(compteDestinataire).orElseThrow(() -> new Exception("Compte non trouvé"));
        float solde = user.getSolde() - montant;
        if (montant >user.getSolde() ) {
            throw new Exception("Solde insuffisant");
        }
        user.setSolde(solde);
        userRepository.save(user);
        Transaction transaction = new Transaction();
        transaction.setCompteEmetteur(user);
        transaction.setMontant(-montant);
        transaction.setDate(LocalDateTime.now());
        transaction.setType_transaction(type_transaction);
        transaction.setEtat(TransactionState.PENDING);
        String to = user.getEmail();
        String subject = "Transaction validation code";

        String code = new SecureRandom().ints(6, 0, 36)
                .mapToObj(i -> Integer.toString(i, 36))
                .collect(Collectors.joining())
                .toUpperCase();
       // String text = "Your transaction validation code is: " + code;

        String text = "<html><body style='font-family: Arial, sans-serif;'>" +

                "<h2>Transaction Validation Code</h2>" +
               "<p>Dear client,</p>" +
                "<p>Your transaction validation code of the withdraw operation is: <strong>" + code + "</strong></p>" +
                "<p>Please use this code to confirm your transaction on our website.</p>" +
                "<p>Thank you for choosing our bank!</p>" +
                "</body></html>";
        emailService.sendEmail(to, subject, text);
        transaction.setValidationCode(code); // Save validation code in transaction

        transactionRepository.save(transaction);
    }


@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public void transfert(Long compteEmetteur, Long compteDestinataire,  float montant , String type_transaction) throws Exception {
    User compteEmmetteur = userRepository.findById(compteEmetteur).orElseThrow(() -> new Exception("Compte source non trouvé"));
    User compteDesstinataire = userRepository.findById(compteDestinataire).orElseThrow(() -> new Exception("Compte destination non trouvé"));
    float soldeSrc = compteEmmetteur.getSolde() - montant;
    if (compteEmmetteur.getSolde() < montant) {
        throw new Exception("Solde insuffisant");
    }
    float soldeDest = compteDesstinataire.getSolde() + montant;
    compteEmmetteur.setSolde(soldeSrc);
    compteDesstinataire.setSolde(soldeDest);
    userRepository.save(compteEmmetteur);
    userRepository.save(compteDesstinataire);
    Transaction transactionSrc = new Transaction();
    transactionSrc.setCompteEmetteur(compteEmmetteur);
    transactionSrc.setMontant(-montant);
    transactionSrc.setDate(LocalDateTime.now());
    transactionSrc.setType_transaction(type_transaction);
    transactionSrc.setEtat(TransactionState.PENDING);
    transactionRepository.save(transactionSrc);
    Transaction transactionDest = new Transaction();
    transactionDest.setCompteDestinataire(compteDesstinataire);
    transactionDest.setMontant(montant);
    transactionDest.setDate(LocalDateTime.now());
    transactionDest.setType_transaction(type_transaction);
   // transactionDest.setEtat(transactionSrc.getEtat());
    transactionRepository.save(transactionDest);



    // send email notification to the sender
    String to = compteEmmetteur.getEmail();// getClient().getEmail();
    String senderName = compteEmmetteur.getFirstName();
//    String recipientEmail = compteDestinataire.getNom(); here i can create Query to bring the name of the recipient
   String recipientName = compteDesstinataire.getFirstName();
    String subject = "Transfer successful";
    String code = new SecureRandom().ints(6, 0, 36)
            .mapToObj(i -> Integer.toString(i, 36))
            .collect(Collectors.joining())
            .toUpperCase();
//    String text = "Dear " + senderName + ",\n\nYour transfer of " + montant + " To " + recipientName +
//            "has been successful. \n\nThank you for using our banking services.\n\nBest regards,\nBank XYZ";
//

    String text = "<html><body style='font-family: Arial, sans-serif;'>" +

            "<h2>Transaction Validation</h2>" +
            "<p>Dear " + senderName+ ",</p>" +
            "<p>Your transfer of: <strong>" + montant + "</strong> To "+ recipientName + " has been successful. </p>" +
            "<p>This is your validation code <strong> " + code+ "</strong ,</p>" +
            "<p>Thank you for using our banking services.!</p>" +
            "</body></html>";
    emailService.sendEmail(to, subject, text);
    transactionSrc.setValidationCode(code);


//send mail to the receiver
    String A = compteDesstinataire.getEmail();
    String sender = compteEmmetteur.getFirstName();
//    String recipientEmail = compteDestinataire.getNom(); here i can create Query to bring the name of the recipient
    String recipient = compteDesstinataire.getFirstName();
    String body = "Transfer successful";

//    String words = "Dear " + recipient + ",\n\nYou have receive amount of " + montant + " From " + sender + ". " +
//            "\n\nThank you for using our banking services.\n\nBest regards,\nBank XYZ";

    String words = "<html><body style='font-family: Arial, sans-serif;'>" +


    "<h2>Transfer notification</h2>" +
            "<p>Dear " + recipient+ ",</p>" +
            "<p>You have received  amount of : <strong>" + montant + "</strong> From "+ sender + ". </p>" +

            "<p>Thank you for using our banking services.!</p>" +
            "</body></html>";
    emailService.sendMail(A, body, words);
    //transactionSrc.setValidationCode(code);



}

    public List<Transaction>findAllTransaction(){
    return transactionRepository.findAll();
    }


    public Transaction findTransactionById(Long id){
    return transactionRepository.findTransactionById(id);
    }
    public void deleteTransactionById(Long id){
    transactionRepository.deleteById(id);
}
}




