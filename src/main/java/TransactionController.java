import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/transact")

public class TransactionController {


    @Autowired
    private AccountRepository accountRepository;

    /*  @Autowired
      private PaymentRepository paymentRepository;*/
    @Autowired
    private TransactionRepository transactRepository;

    User user;
    double currentBalance;
    double newBalance;
    LocalDateTime currentDateTime = LocalDateTime.now();

    @PostMapping("/deposit")
    public String deposit(@RequestParam("deposit_amount") String depositAmount,
                          @RequestParam("account_id") String accountID,
                          HttpSession session,
                          RedirectAttributes redirectAttributes) {

        // TODO: CHECK FOR EMPTY STRINGS:
        if (depositAmount.isEmpty() || accountID.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Deposit Amount or Account Depositing to Cannot Be Empty!");
            return "redirect:/transact/deposit";
        }
        // TODO GET LOGGED IN USER:
        user = (User) session.getAttribute("user");

        // TODO: GET CURRENT ACCOUNT BALANCE:
        int acc_id = Integer.parseInt(accountID);

        double depositAmountValue = Double.parseDouble(depositAmount);



        //TODO: CHECK IF DEPOSIT AMOUNT IS 0 (ZERO):
        if(depositAmountValue == 0){
            redirectAttributes.addFlashAttribute("error", "Deposit Amount Cannot Be of 0 (Zero) Value");
            return "redirect:/transact/deposit";
        }

        // TODO: UPDATE BALANCE:
        currentBalance = accountRepository.getAccountBalance(user.getUser_id(), acc_id);

        newBalance = currentBalance + depositAmountValue;

        // Update Account:
        accountRepository.changeAccountBalanceById(newBalance, acc_id);

        // Log Successful Transaction:
        transactRepository.logTransaction(acc_id, "deposit", depositAmountValue, "online", "success", "Deposit Transaction Successful",currentDateTime);

        redirectAttributes.addFlashAttribute("success", "Amount Deposited Successfully");
        return "redirect:/transact/deposit";
    }
/*
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(
            @RequestParam Long creditAccountId,
            @RequestParam Long debitAccountId,
            @RequestParam BigDecimal amount) {

        try {
            transactionService.transfer(creditAccountId, debitAccountId ,amount);
            return ResponseEntity.ok("Transfer successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (InsufficientFundsException e) {
            throw new RuntimeException(e);
        }
    }
}

*/
    /*
    private final TransactionService transactionService;
    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private AccountRepository accountRepository;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }





        @PostMapping("/add")
        public ResponseEntity<Transaction> createTransaction(
                @RequestParam Long senderAccountId,
                @RequestParam Long receiverAccountId,
                @RequestParam double amount) {

            // Vérifier que les deux comptes existent
            Account senderAccount = accountRepository.findById(senderAccountId)
                    .orElseThrow(() -> new RuntimeException("Compte émetteur introuvable"));
            Account receiverAccount = accountRepository.findById(receiverAccountId)
                    .orElseThrow(() -> new RuntimeException("Compte récepteur introuvable"));

            // Vérifier que le solde du compte émetteur est suffisant
            if (senderAccount.getBalance() < amount) {
                throw new RuntimeException("Solde insuffisant sur le compte émetteur");
            }

            // Créer la transaction
            Transaction transaction = new Transaction();
            transaction.setSenderAccount(senderAccount);
            transaction.setReceiverAccount(receiverAccount);
            transaction.setAmount(amount);

            // Mettre à jour les soldes des comptes
            senderAccount.setBalance(senderAccount.getBalance() - amount);
            receiverAccount.setBalance(receiverAccount.getBalance() + amount);
            accountRepository.saveAll(Arrays.asList(senderAccount, receiverAccount));

            // Enregistrer la transaction
            Transaction savedTransaction = transactionRepo.save(transaction);

            return ResponseEntity.ok(savedTransaction);
        }

        @GetMapping("/get")
        public ResponseEntity<List<Transaction>> getAllTransactions() {
            List<Transaction> transactions = transactionRepo.findAll();
            return ResponseEntity.ok(transactions);
        }
    }


*/


/*
    @GetMapping("/all")
    public ResponseEntity<List<Transaction>> getAllTransaction() {
        List<Transaction> transactions = transactionService.findAllTransaction();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable("id") Long id) {
        Transaction transaction = transactionService.findTransactionById(id);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction) {
        Transaction newtransaction = transactionService.addETransaction(transaction);
        return new ResponseEntity<>(newtransaction, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Transaction> updateTransaction(@RequestBody Transaction transaction) {
        Transaction updatetransaction = transactionService.updateTransaction(transaction);
        return new ResponseEntity<>(updatetransaction, HttpStatus.OK);


    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable("id") Long id) {
        transactionService.deleteTransaction(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }
*/


    }

