
package FinanceMe.PiDev.Controller;

import FinanceMe.PiDev.DTO.DepositRequest;
import FinanceMe.PiDev.DTO.ValidationRequest;
import FinanceMe.PiDev.DTO.WithdrawRequest;
import FinanceMe.PiDev.Enteties.Transaction;
import FinanceMe.PiDev.Repository.CompteRepository;
import FinanceMe.PiDev.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    private CompteRepository compteRepository;


    /*
    @PostMapping("/depot")
    public ResponseEntity<?> depot(@RequestParam Long compteDestinataire, @RequestParam float montant , @RequestParam String type_transaction) {
        try {
            transactionService.depot(compteDestinataire, montant,type_transaction);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PutMapping("/validation")
    public ResponseEntity<String> validerTransaction(@RequestParam Long transactionId, @RequestParam String codeValidation) {
        try {
            transactionService.validerTransaction(transactionId, codeValidation);
            return ResponseEntity.ok("Transaction validée avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    origin code
     */


    @PostMapping("/deposit")
    public ResponseEntity<?> depot(@RequestBody DepositRequest depositRequest) {
        try {
            transactionService.depot(depositRequest.getCompteDestinataire(), depositRequest.getMontant(), "Deposit");
            return ResponseEntity.ok("Transaction created successfully and waiting for validation.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }




    @PostMapping("/withdraw")
    public ResponseEntity<?> retrait(@RequestBody WithdrawRequest withdrawRequest ) {
        try {
            transactionService.retrait(withdrawRequest.getCompteDestinataire(), withdrawRequest.getMontant(), "Withdraw");
            return ResponseEntity.ok("Transaction created successfully and waiting for validation.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
//
//    @PostMapping("/validateWithdraw")
//    public ResponseEntity<?> validerTransactionretrait(@RequestBody ValidationWithdrawRequest validationWithdrawRequest  ) {
//        try {
//            transactionService.validerTransactionretrait(validationWithdrawRequest.getTransactionId(), validationWithdrawRequest.getValidationCode());
//            return ResponseEntity.ok("Transaction validated successfully.");
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }



    @PostMapping("/validate")
    public ResponseEntity<?> validerTransaction(@RequestBody ValidationRequest validationRequest) {
        try {
            transactionService.validerTransaction(validationRequest.getTransactionId(), validationRequest.getValidationCode());
            return ResponseEntity.ok("Transaction validated successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }















//    @PostMapping("/retrait")
//    public ResponseEntity<?> retrait(@RequestParam Long compteDestinataire, @RequestParam float montant , @RequestParam String type_transaction) {
//        try {
//            transactionService.retrait(compteDestinataire, montant, type_transaction);
//            return ResponseEntity.ok().build();
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
//    }

    @PostMapping("/transfert")
    public ResponseEntity<?> transfert(@RequestParam Long compteEmetteur, @RequestParam Long compteDestinataire, @RequestParam float montant , @RequestParam String type_transaction) {
        try {
            transactionService.transfert(compteEmetteur, compteDestinataire, montant , type_transaction);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }




  /*
    @PostMapping("/{compteSrcId}/{compteDestId}/{montant}")
    public ResponseEntity<String> transfert(@PathVariable("compteSrcId") Long compteSrcId,
                                            @PathVariable("compteDestId") Long compteDestId,
                                            @PathVariable("montant") float montant) {
        try {
            transactionService.transfert(compteSrcId, compteDestId, montant);
            return new ResponseEntity<>("Transfert effectué avec succès", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

   */



    @GetMapping("/all_transactions")
    public ResponseEntity<List<Transaction>> findAllTransaction() {
        List<Transaction> transactions = transactionService.findAllTransaction();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<Transaction> findTransactionById(@PathVariable("id") Long id) {
        Transaction transaction = transactionService.findTransactionById(id);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTransactionById(@PathVariable("id") Long id) {
        transactionService.deleteTransactionById(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }


}
