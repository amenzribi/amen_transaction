package FinanceMe.PiDev.Services;

import FinanceMe.PiDev.Enteties.Compte;
import FinanceMe.PiDev.Enteties.Transaction;
import FinanceMe.PiDev.Repository.CompteRepository;
import FinanceMe.PiDev.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.websocket.Session;
import java.net.PasswordAuthentication;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;
import java.util.Random;

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

    public String generateValidationCode() {
        Random random = new Random();
        int code = random.nextInt(100000000);
        return String.format("%08d", code);
    }




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
        String validationCode = generateValidationCode(); // Générer le code de validation
        transaction.setValidationCode(validationCode);
        transactionRepository.save(transaction);

        String to = compte.getEmail();
        String subject = "Transaction validation code";
        String text = "Your transaction validation code is: " + generateValidationCode();
        emailService.sendEmail(to, subject, text);
    }


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
        transactionRepository.save(transaction);
    }
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
    transactionRepository.save(transactionSrc);
    Transaction transactionDest = new Transaction();
    transactionDest.setCompteDestinataire(compteDesstinataire);
    transactionDest.setMontant(montant);
    transactionDest.setDate(LocalDateTime.now());
    transactionDest.setType_transaction(type_transaction);
    transactionRepository.save(transactionDest);
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



