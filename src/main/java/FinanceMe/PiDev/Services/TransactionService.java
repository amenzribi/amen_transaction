package FinanceMe.PiDev.Services;

import FinanceMe.PiDev.Enteties.Compte;
import FinanceMe.PiDev.Enteties.Transaction;
import FinanceMe.PiDev.Enteties.TransactionState;
import FinanceMe.PiDev.Repository.CompteRepository;
import FinanceMe.PiDev.Repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
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

@Slf4j
@EnableScheduling
@Service
public class TransactionService {
/*
        @Autowired
        private TransactionRepository transactionRepository;

        @Autowired
        private CompteRepository compteRepository;

        public void deposer(Long compte_id, float montant) {
            Compte compte = compteRepository.findById(compte_id).orElseThrow(() -> new EntityNotFoundException("Compte non trouvé"));

            float nouveauSolde = compte.getSolde() + montant;
            compte.setSolde(nouveauSolde);
            compteRepository.save(compte);

            Transaction transaction = new Transaction();
            transaction.setCompte(compte);
            transaction.setMontant(montant);
            transaction.setDate(LocalDateTime.now());
            transactionRepository.save(transaction);
        }

        public void retirer(Long compteId, float montant) {
            Compte compte = compteRepository.findById(compteId).orElseThrow(() -> new EntityNotFoundException("Compte non trouvé"));

            float nouveauSolde = compte.getSolde() - montant;
            compte.setSolde(nouveauSolde);
            compteRepository.save(compte);

            Transaction transaction = new Transaction();
            transaction.setCompte(compte);
            transaction.setMontant(-montant);
            transaction.setDate(LocalDateTime.now());
            transactionRepository.save(transaction);
        }
    }
*/


    @Autowired
    private CompteRepository compteRepository;

    @Autowired
    private TransactionRepository transactionRepository;
   @Autowired
   private EmailService emailService;
/*
    public void sendConfirmationEmail(String recipientEmail, String validationCode) {
        String subject = "Confirmation de transaction bancaire";
        String body = "Bonjour,\n\n" +
                "Nous vous remercions d'avoir effectué une transaction bancaire. Veuillez utiliser le code suivant pour valider la transaction : " + validationCode + "\n\n" +
                "Cliquez sur le lien suivant pour valider la transaction : http://monsite.com/validation?id=1234\n\n" +
                "Cordialement,\n" +
                "L'équipe de la banque";

        try {
            // Initialiser une connexion SMTP
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("amenallah.zribi@esprit.tn", "211JMT0311");
                }
            });

            // Créer le message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("votre.email@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setText(body);

            // Envoyer le message
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

 */
/*
    @Autowired
    private JavaMailSender javaMailSender;

 */

    // ...

    // Method to send email validation
         /*  public void sendValidationEmail(Transaction transaction) {
        // Create a SimpleMailMessage instance
        SimpleMailMessage message = new SimpleMailMessage();

        // Set the sender email address
        message.setFrom("sender@example.com");

        // Set the recipient email address
        message.setTo(transaction.getUser().getEmail());

        // Set the email subject
        message.setSubject("Transaction Validation");

        // Set the email body
        message.setText("Dear " + transaction.getUser().getFullName() + ",\n\n" +
                "Your transaction has been validated.\n\n" +
                "Amount: " + transaction.getAmount() + "\n" +
                "Date: " + transaction.getDate() + "\n\n" +
                "Thank you for using our banking services.");

        // Send the email
        javaMailSender.send(message);
    }

          */

//    public String generateValidationCode() {
//        Random random = new Random();
//        int code = random.nextInt(100000000);
//        return String.format("%08d", code);
//    }


//    @Autowired
//    private EmailService emailService;
//
//    public Transaction validerTransactionParEmail(Transaction transaction) {
//        // Générer un code de validation unique
//        String codeValidation = generateValidationCode();
//      //  String email =  compte.getEmail();
//
//        // Envoyer un e-mail avec le code de validation
//        String subject = "Validation de la transaction #" + transaction.getId();
//        String message = "Votre code de validation est : " + codeValidation;
//        emailService.sendSimpleMessage(email, subject, message);
//
//        // Mettre à jour l'état de la transaction en "en attente de validation"
//        transaction.setEtatTransaction(EtatTransaction.EN_ATTENTE_DE_VALIDATION);
//        transaction.setValidationCode(codeValidation);
//
//        // Sauvegarder la transaction dans la base de données
//        return transactionRepository.save(transaction);
//    }
//
//    private String generateValidationCode() {
//        // Générer un code de validation unique
//        return UUID.randomUUID().toString().substring(0, 6).toUpperCase();
//    }


/*

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void depot(Long compteDestinataire, float montant , String type_transaction) throws Exception {
        Compte compte = compteRepository.findById(compteDestinataire).orElseThrow(() -> new Exception("Compte non trouvé"));
        float solde = compte.getSolde() + montant;
        compte.setSolde(solde);
        compteRepository.save(compte);
        Transaction transaction = new Transaction();
        transaction.setCompteDestinataire(compte);
        transaction.setMontant(montant);
        transaction.setDate(LocalDateTime.now());
        transaction.setType_transaction(type_transaction);
        transaction.setEtat("En attente de validation");
        String to = compte.getEmail();
        String subject = "Transaction validation code";
        String code = UUID.randomUUID().toString().substring(0, 6);
        String text = "Your transaction validation code is: " + code;
        emailService.sendEmail(to, subject, text);
        transactionRepository.save(transaction);

    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void validerTransaction(Long transactionId, String codeValidation) throws Exception {
        Transaction transaction = transactionRepository.findById(transactionId).orElseThrow(() -> new Exception("Transaction non trouvée"));
        if (!transaction.getEtat().equals("En attente de validation")) { //Vérifier si la transaction est en attente de validation
            throw new Exception("La transaction ne peut pas être validée car elle n'est pas en attente de validation");
        }
        if (!transaction.getValidationCode().equals(codeValidation)) { //Vérifier si le code de validation est correct
            throw new Exception("Le code de validation est incorrect");
        }
        transaction.setEtat("Validé"); //Mettre à jour l'état de la transaction en "Validé"
        transaction.setEtat("Validé"); // Mettre à jour l'état de la transaction en "Validé"
        transactionRepository.save(transaction);

        Compte compteDestinataire = transaction.getCompteDestinataire();
        float solde = compteDestinataire.getSolde() + transaction.getMontant();
        compteDestinataire.setSolde(solde);
        compteRepository.save(compteDestinataire);


        transactionRepository.save(transaction);
    }
    the origin code with simple mail
 */

//        String validationCode = generateValidationCode(); // Générer le code de validation
//        transaction.setValidationCode(validationCode);
//        transactionRepository.save(transaction);
//        emailService.sendSimpleMessage(email, "Transaction validée", "Votre dépôt de " + montant + " a été validé.");


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void depot(Long compteDestinataire, float montant, String type_transaction) throws Exception {
        Compte compte = compteRepository.findById(compteDestinataire)
                .orElseThrow(() -> new Exception("Compte non trouvé"));
        float solde = compte.getSolde() + montant;
        compte.setSolde(solde);
        compteRepository.save(compte);
        Transaction transaction = new Transaction();
        transaction.setCompteDestinataire(compte);
        transaction.setMontant(montant);
        transaction.setDate(LocalDateTime.now());
        transaction.setType_transaction(type_transaction);
        transaction.setEtat(TransactionState.PENDING);

        String to = compte.getEmail();
        String subject = "Transaction validation code";


        String code = new SecureRandom().ints(6, 0, 36)
                .mapToObj(i -> Integer.toString(i, 36))
                .collect(Collectors.joining())
                .toUpperCase();     //String code =     //UUID.randomUUID().toString().substring(0, 6);
        String text = "Your transaction validation code is: " + code;
        emailService.sendEmail(to, subject, text);
        transaction.setValidationCode(code); // Save validation code in transaction
        transactionRepository.save(transaction);
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void validerTransaction(Long transactionId, String codeValidation) throws Exception {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new Exception("Transaction non trouvée"));
        if (!transaction.getValidationCode().equals(codeValidation) /*|| transaction.getDate().plusDays(1).isBefore(LocalDateTime.now())*/) { //Vérifier si la transaction est en attente de validation
            transaction.setEtat(TransactionState.CANCELED);
         //   transactionRepository.save(transaction);
            throw new Exception("La transaction a été annulée car le code de validation est incorrect ou le temps de validation a dépassé 1 jour");
        }
        if (!transaction.getValidationCode().equals(codeValidation)) { //Vérifier si le code de validation est correct
            throw new Exception("Le code de validation est incorrect");
        }
        transaction.setEtat(TransactionState.VALIDATED); // Mettre à jour l'état de la transaction en "Transaction is validated"
        transactionRepository.save(transaction);


    }
    private static final Logger logger = LogManager.getLogger(TransactionService.class);
    @Scheduled(cron = "0 */7 * * * *") // exécuter la méthode toutes les 7 minutes
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

//    @Scheduled(cron = "0 */7 * * * *") // exécuter la méthode toutes les 7 minutes
//    public void annulerTransactionsExpirées() {
//        try {
//            List<Transaction> transactionsEnAttente = transactionRepository.findTransactionsEnAttente("En attente de validation");
//            LocalDateTime maintenant = LocalDateTime.now();
//            for (Transaction transaction : transactionsEnAttente) {
//                LocalDateTime dateTransaction = transaction.getDate();
//                Duration duréeDepuisValidation = Duration.between(dateTransaction, maintenant);
//                if (duréeDepuisValidation.toMinutes() >= 5) { // Vérifier si la durée est supérieure ou égale à 5 minutes
//                    transaction.setEtat("Transaction annulée");
//                    transactionRepository.save(transaction);
//                }
//            }
//            logger.info("Les transactions expirées ont été annulées avec succès.");
//        } catch (Exception e) {
//            logger.error("Une exception s'est produite lors de l'exécution de la méthode annulerTransactionsExpirées : " + e.getMessage());
//        }
//    }






//    @Scheduled(cron = "0 */5 * * * ?") // Appelé tous les jours (24 heures) exécuter la méthode à minuit tous les jours.
//    public void annulerTransactionsExpirées() {
//        try {
//            List<Transaction> transactionsEnAttente = transactionRepository.findTransactionsEnAttente("En attente de validation");
//            for (Transaction transaction : transactionsEnAttente) {
//                LocalDateTime maintenant = LocalDateTime.now();
//                Duration duréeDepuisValidation = Duration.between(transaction.getDate(), maintenant);
//                if (duréeDepuisValidation.toDays() >= 1) {
//                    transaction.setEtat("Transaction annulée");
//                    transactionRepository.save(transaction);
//                }
//            }
//            logger.debug("Message de niveau debug");
//            logger.info("Message de niveau info");
//            logger.warn("Message de niveau warn");
//            logger.error("Message de niveau error");
//        } catch (Exception e) {
//            logger.error("Une exception s'est produite lors de l'exécution de la méthode annulerTransactionsExpirées : " + e.getMessage());
//        }
//    }

//
private static final Logger loggerRemind = LogManager.getLogger(TransactionService.class);
    @Transactional
    @Scheduled(cron = "0 */3 * * * *") // execute the method every 3 minutes
    public void remindUsersOfPendingTransactions() {
        try {
            List<Transaction> pendingTransactions = transactionRepository.findByEtat(TransactionState.PENDING);

            for (Transaction transaction : pendingTransactions) {
                Compte compteEmmetteur = transaction.getCompteEmetteur();
                Compte compteDesstinataire = transaction.getCompteDestinataire();
//                Compte compteEmmetteur = compteRepository.findById(compteEmetteur).orElseThrow(() -> new Exception("Compte source non trouvé"));
//                Compte compteDesstinataire = compteRepository.findById(compteDestinataire).orElseThrow(() -> new Exception("Compte destination non trouvé"));
//
//                String emetteurEmail = emetteur.getEmail();
//                String destinataireEmail = destinataire.getEmail();

                // send an email to both the sender and the recipient
                String For = compteEmmetteur.getEmail();
                String sender = compteEmmetteur.getNom();
                //    String recipientEmail = compteDestinataire.getNom(); here i can create Query to bring the name of the recipient
                String recipient = compteDesstinataire.getNom();
                String body = "Reminder: Pending Transaction";

                String words = "Hello "+ sender +",\n\nYou have a pending transaction to  account with name "+recipient+" Please validate or cancel it.\n\nThank you.";

                emailService.remindMail(For, body, words);

                logger.info("Reminder email sent successfully for transaction with ID " + transaction.getId());
            }

            logger.info("Pending transactions reminder email job executed successfully.");
        } catch (Exception e) {
            logger.error("An exception occurred while executing the pending transactions reminder email job: " + e.getMessage());
        }
    }


//    @Autowired
//    private Excelgenerate excelgenerate ;

//    @Scheduled(cron = "0 0 0 1 * *") // Run on the first day of every month at midnight
//    public void generateExcelFile() throws IOException {
//        excelgenerate.generateExcelFile();
//    }
//@Scheduled(fixedRate = 120000)
//public void generateExcelFile() throws IOException {
//    List<Transaction> transactions = transactionRepository.findAll();
//    Workbook workbook = new XSSFWorkbook();
//    Sheet sheet = workbook.createSheet("Transactions");
//    Row headerRow = sheet.createRow(0);
//    headerRow.createCell(0).setCellValue("ID");
//    headerRow.createCell(1).setCellValue("Date");
//    headerRow.createCell(2).setCellValue("Montant");
//    headerRow.createCell(3).setCellValue("Compte destinataire");
//    headerRow.createCell(4).setCellValue("Compte émetteur");
//    headerRow.createCell(5).setCellValue("Type de transaction");
//    headerRow.createCell(6).setCellValue("État");
//    int rowNum = 1;
//    for (Transaction transaction : transactions) {
//        Row row = sheet.createRow(rowNum++);
//        row.createCell(0).setCellValue(transaction.getId());
//        row.createCell(1).setCellValue(transaction.getDate().toString());
//        row.createCell(2).setCellValue(transaction.getMontant());
//        Compte compteDestinataire = transaction.getCompteDestinataire();
//        Compte compteEmetteur = transaction.getCompteEmetteur();
//        row.createCell(3).setCellValue(compteDestinataire.getCompte_id());
//        row.createCell(4).setCellValue(compteEmetteur.getCompte_id());
//        row.createCell(5).setCellValue(transaction.getType_transaction());
//        row.createCell(6).setCellValue(transaction.getEtat().toString());
//    }
//    String filePath = "C:\\Users\\zribi\\Desktop\\PiTrans\\amen_transaction-master\\transactions.xlsx";
//    FileOutputStream outputStream = new FileOutputStream(filePath);
//    workbook.write(outputStream);
//    workbook.close();
//    System.out.println("Excel file generated and saved to " + filePath);
//}

//    @Autowired
//    private JavaMailSender mailSender;

//    @Scheduled(cron = "0 0 0 * * ?") // Runs at midnight every day
//    public void sendTransactionSummaryEmail() throws MessagingException, IOException {
//        // Get the current date and time
//        LocalDateTime currentTime = LocalDateTime.now();
//
//        // Subtract 24 hours from the current time to get the start time for the summary report
//        LocalDateTime startTime = currentTime.minusHours(24);
//
//        // Get all transactions made between the start time and the current time
//        List<Transaction> transactions = transactionRepository.findAllByDateBetween(startTime, currentTime);
//
//        // Create a CSV file with the transaction data
//        StringWriter writer = new StringWriter();
//        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("ID", "Date", "Montant", "Compte destinataire", "Compte émetteur", "Type de transaction", "État"));
//        for (Transaction transaction : transactions) {
//            csvPrinter.printRecord(transaction.getId(), transaction.getDate().toString(), transaction.getMontant(), transaction.getCompteDestinataire().getCompte_id(), transaction.getCompteEmetteur().getCompte_id(), transaction.getType_transaction(), transaction.getEtat().toString());
//        }
//        csvPrinter.flush();
//
//        // Create the email message and attach the CSV file to it
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//        helper.setTo(accountRepository.findAll().stream().map(Account::getEmail).toArray(String[]::new));
//        helper.setSubject("Transaction Summary Report");
//        helper.setText("Please find attached the summary report of all transactions made in the last 24 hours");
//        helper.addAttachment("transaction_summary_report.csv", new ByteArrayResource(writer.toString().getBytes()));
//
//        // Send the email message
//        mailSender.send(message);
//    }



//    @Scheduled(fixedRate = 120000)
//    public void generateExcelFile() throws IOException {
//        List<Transaction> transactions = transactionRepository.findAll();
//        Workbook workbook = new XSSFWorkbook();
//        Sheet sheet = workbook.createSheet("Transactions");
//        Row headerRow = sheet.createRow(0);
//        headerRow.createCell(0).setCellValue("ID");
//        headerRow.createCell(1).setCellValue("Date");
//        headerRow.createCell(2).setCellValue("Montant");
//        headerRow.createCell(3).setCellValue("Compte destinataire");
//        headerRow.createCell(4).setCellValue("Compte émetteur");
//        headerRow.createCell(5).setCellValue("Type de transaction");
//        headerRow.createCell(6).setCellValue("État");
//        int rowNum = 1;
//        for (Transaction transaction : transactions) {
//            Row row = sheet.createRow(rowNum++);
//            row.createCell(0).setCellValue(transaction.getId());
//            row.createCell(1).setCellValue(transaction.getDate().toString());
//            row.createCell(2).setCellValue(transaction.getMontant());
//            Compte compteDestinataire = transaction.getCompteDestinataire();
//            Compte compteEmetteur = transaction.getCompteEmetteur();
//            row.createCell(3).setCellValue(compteDestinataire.getCompte_id());
//            row.createCell(4).setCellValue(compteEmetteur.getCompte_id());
//            row.createCell(5).setCellValue(transaction.getType_transaction());
//            row.createCell(6).setCellValue(transaction.getEtat().toString());
//        }
//        FileOutputStream outputStream = new FileOutputStream("C:\\Users\\zribi\\Desktop\\PiTrans\\amen_transaction-master\\transactions.xlsx");
//        workbook.write(outputStream);
//        workbook.close();
//    }
//


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void retrait(Long compteDestinataire, float montant , String type_transaction) throws Exception {
        Compte compte = compteRepository.findById(compteDestinataire).orElseThrow(() -> new Exception("Compte non trouvé"));
        float solde = compte.getSolde() - montant;
        if (montant >compte.getSolde() ) {
            throw new Exception("Solde insuffisant");
        }
        compte.setSolde(solde);
        compteRepository.save(compte);
        Transaction transaction = new Transaction();
        transaction.setCompteEmetteur(compte);
        transaction.setMontant(-montant);
        transaction.setDate(LocalDateTime.now());
        transaction.setType_transaction(type_transaction);
        transaction.setEtat(TransactionState.PENDING);
        String to = compte.getEmail();
        String subject = "Transaction validation code";

        String code = new SecureRandom().ints(6, 0, 36)
                .mapToObj(i -> Integer.toString(i, 36))
                .collect(Collectors.joining())
                .toUpperCase();
        String text = "Your transaction validation code is: " + code;
        emailService.sendEmail(to, subject, text);
        transaction.setValidationCode(code); // Save validation code in transaction

        transactionRepository.save(transaction);
    }
//    private  final String TRANSACTION_STATE_PENDING = "En attente de validation";
//    private  final String TRANSACTION_STATE_VALIDATED = "Transaction validée";
//    private  final String TRANSACTION_STATE_CANCELLED = "Transaction annulée";

//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
//    public void validerTransactionretrait(Long transactionId, String codeValidation) throws Exception {
//        Transaction transaction = transactionRepository.findById(transactionId)
//                .orElseThrow(() -> new Exception("Transaction non trouvée"));
//        if (!transaction.getEtat().equals("En attente de validation")) { //Vérifier si la transaction est en attente de validation
//            throw new Exception("La transaction ne peut pas être validée car elle n'est pas en attente de validation");
//        }
//        if (!transaction.getValidationCode().equals(codeValidation)) { //Vérifier si le code de validation est correct
//            throw new Exception("Le code de validation est incorrect");
//        }
//        transaction.setEtat("Transaction is validated"); // Mettre à jour l'état de la transaction en "Transaction is validated"
//        transactionRepository.save(transaction);
//
//        Compte compteDestinataire = transaction.getCompteDestinataire();
//        float solde = compteDestinataire.getSolde() - transaction.getMontant();
//        compteDestinataire.setSolde(solde);
//        compteRepository.save(compteDestinataire);
//    }




//        Compte compteDestinataire = transaction.getCompteDestinataire();
//        float solde = compteDestinataire.getSolde() + transaction.getMontant();
//        compteDestinataire.setSolde(solde);
//        compteRepository.save(compteDestinataire);


/*
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void transfert(Long compte_r, Long compte_s, float montant) throws Exception {
        Compte compte_Src = compteRepository.findById(compte_s).orElseThrow(() -> new Exception("Compte source non trouvé"));
        Compte compteDest = compteRepository.findById(compte_r).orElseThrow(() -> new Exception("Compte destination non trouvé"));
        float soldeSrc = compte_Src.getSolde() - montant;
        if (soldeSrc < 0) {
            throw new Exception("Solde insuffisant");
        }
        float soldeDest = compteDest.getSolde() + montant;
        compte_Src.setSolde(soldeSrc);
        compteDest.setSolde(soldeDest);
        compteRepository.save(compte_Src);
        compteRepository.save(compteDest);
        Transaction transactionSrc = new Transaction();
        transactionSrc.setCompteEmetteur(compte_Src);
        transactionSrc.setMontant(-montant);
        transactionSrc.setDate(LocalDateTime.now());
        transactionRepository.save(transactionSrc);
        Transaction transactionDest = new Transaction();
        transactionDest.setCompteDestinataire(compte_Dest);
        transactionDest.setMontant(montant);
        transactionDest.setDate(LocalDateTime.now());
        transactionRepository.save(transactionDest);
    }

 */

@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public void transfert(Long compteEmetteur, Long compteDestinataire,  float montant , String type_transaction) throws Exception {
    Compte compteEmmetteur = compteRepository.findById(compteEmetteur).orElseThrow(() -> new Exception("Compte source non trouvé"));
    Compte compteDesstinataire = compteRepository.findById(compteDestinataire).orElseThrow(() -> new Exception("Compte destination non trouvé"));
    float soldeSrc = compteEmmetteur.getSolde() - montant;
    if (compteEmmetteur.getSolde() < montant) {
        throw new Exception("Solde insuffisant");
    }
    float soldeDest = compteDesstinataire.getSolde() + montant;
    compteEmmetteur.setSolde(soldeSrc);
    compteDesstinataire.setSolde(soldeDest);
    compteRepository.save(compteEmmetteur);
    compteRepository.save(compteDesstinataire);
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
    String senderName = compteEmmetteur.getNom();
//    String recipientEmail = compteDestinataire.getNom(); here i can create Query to bring the name of the recipient
   String recipientName = compteDesstinataire.getNom();
    String subject = "Transfer successful";
    String code = new SecureRandom().ints(6, 0, 36)
            .mapToObj(i -> Integer.toString(i, 36))
            .collect(Collectors.joining())
            .toUpperCase();
    String text = "Dear " + senderName + ",\n\nYour transfer of " + montant + " To " + recipientName + "has been successful. \n\nThank you for using our banking services.\n\nBest regards,\nBank XYZ";
    emailService.sendEmail(to, subject, text);
    transactionSrc.setValidationCode(code);


//send mail to the receiver
    String A = compteDesstinataire.getEmail();
    String sender = compteEmmetteur.getNom();
//    String recipientEmail = compteDestinataire.getNom(); here i can create Query to bring the name of the recipient
    String recipient = compteDesstinataire.getNom();
    String body = "Transfer successful";

    String words = "Dear " + recipient + ",\n\nYou have receive amount of " + montant + " From " + sender + ". \n\nThank you for using our banking services.\n\nBest regards,\nBank XYZ";
    emailService.sendMail(A, body, words);
    //transactionSrc.setValidationCode(code);



}

   // String to = compte.getEmail();
   // String subject = "Transaction validation code";


















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
    /*
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CompteRepository compteRepository;

    public void deposer(Long compteId, Double montant) {
        Compte compte = compteRepository.findById(compteId).orElseThrow(() -> new EntityNotFoundException("Compte non trouvé"));

        Double nouveauSolde = compte.getSolde() + montant;
        compte.setSolde(nouveauSolde);
        compteRepository.save(compte);

        Transaction transaction = new Transaction();
        transaction.setCompte(compte);
        transaction.setMontant(montant);
        transaction.setDate(LocalDateTime.now());
        transactionRepository.save(transaction);
    }

    public void retirer(Long compteId, Double montant) {
        Compte compte = compteRepository.findById(compteId).orElseThrow(() -> new EntityNotFoundException("Compte non trouvé"));

        Double nouveauSolde = compte.getSolde() - montant;
        compte.setSolde(nouveauSolde);
        compteRepository.save(compte);

        Transaction transaction = new Transaction();
        transaction.setCompte(compte);
        transaction.setMontant(-montant);
        transaction.setDate(LocalDateTime.now());
        transactionRepository.save(transaction);
    }

     */



