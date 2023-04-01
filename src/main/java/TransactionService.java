import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
    public class TransactionService {






















    /*
        @Autowired
        private CompteRepository compteRepository;

        @Autowired
        private TransactionRepository transactionRepository;

        public void transfer(Long creditAccountId, Long debitAccountId, BigDecimal amount) throws InsufficientFundsException {
            Compte creditAccount = compteRepository.findById(creditAccountId)
                    .orElseThrow(() -> new ResourceNotFoundException("Credit account not found"));
            Compte debitAccount = compteRepository.findById(debitAccountId)
                    .orElseThrow(() -> new ResourceNotFoundException("Debit account not found"));
            if (creditAccount.getSolde().compareTo(amount) < 0) {
                throw new InsufficientFundsException("Insufficient funds in credit account");
            }
            creditAccount.setSolde(creditAccount.getSolde().subtract(amount));
            debitAccount.setSolde(debitAccount.getSolde().add(amount));
            compteRepository.save(creditAccount);
            compteRepository.save(debitAccount);
            Transaction transaction = new Transaction();
            transaction.setCompteCrediteur(creditAccount);
            transaction.setCompteDebiteur(debitAccount);
            transaction.setMontant(amount);
            transaction.setDate(LocalDateTime.now());
            transactionRepository.save(transaction);
        }

        /*

        @Autowired
        private TransactionRepo transactionRepo;

        @Autowired
        private AccountRepository accountRepository;


        public Transaction createTransaction(Long senderAccountId, Long receiverAccountId, double amount) throws Exception {

            // Récupération des comptes émetteur et récepteur
            Account senderAccount = accountRepository.findById(senderAccountId)
                    .orElseThrow(() -> new Exception("Compte émetteur invalide"));

            Account receiverAccount = accountRepository.findById(receiverAccountId)
                    .orElseThrow(() -> new Exception("Compte récepteur invalide"));

            // Vérification du solde du compte émetteur
            if (senderAccount.getBalance() < amount) {
                throw new Exception("Solde insuffisant");
            }

            // Mise à jour des soldes
            senderAccount.setBalance(senderAccount.getBalance() - amount);
            receiverAccount.setBalance(receiverAccount.getBalance() + amount);

            // Création de la transaction
            Transaction transaction = new Transaction();
            transaction.setSenderAccount(senderAccount);
            transaction.setReceiverAccount(receiverAccount);
            transaction.setAmount(amount);

            // Enregistrement de la transaction en base de données
            transactionRepo.save(transaction);

            return transaction;
        }
    }
*/

  /*
    private TransactionRepo transactionRepo;

    @Autowired
    public TransactionService(TransactionRepo transactionRepo){
    this.transactionRepo = transactionRepo;
    }

    public Transaction addETransaction(Transaction transaction){

        return transactionRepo.save(transaction);
    }

    public List<Transaction> findAllTransaction(){
        return transactionRepo.findAll();
    }



    public Transaction updateTransaction(Transaction transaction)
    {
        return transactionRepo.save(transaction);
    }




    public Transaction findTransactionById(Long id){
       // return transactionRepo.findTransactionById(id).orElseThrow(()-> new UserNotFoundException("user bot found"));
    return transactionRepo.findById(id).orElseThrow(()-> new UserNotFoundException("User Not Found"));

    }

    public void deleteTransaction(Long id){
      //  transactionRepo.deleteTransactionById(id);
        transactionRepo.deleteById(id);
    }
*/


    }