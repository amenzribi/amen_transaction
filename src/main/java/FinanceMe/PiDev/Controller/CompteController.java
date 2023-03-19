/*
package FinanceMe.PiDev.Controller;


import FinanceMe.PiDev.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
    @RequestMapping("/comptes")
    public class CompteController {
        @Autowired
        private TransactionService transactionService;

        @PostMapping("/{compteId}/deposer")
        public void deposer(@PathVariable Long compteId, @RequestParam float montant) {
            transactionService.deposer(compteId, montant);
        }

        @PostMapping("/{compteId}/retirer")
        public void retirer(@PathVariable Long compteId, @RequestParam float montant) {
            transactionService.retirer(compteId, montant);
        }
    }


*/