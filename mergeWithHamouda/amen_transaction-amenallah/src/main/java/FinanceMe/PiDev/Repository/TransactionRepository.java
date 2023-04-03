package FinanceMe.PiDev.Repository;

import FinanceMe.PiDev.Enteties.Transaction;
import FinanceMe.PiDev.Enteties.TransactionState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction , Long> {

    Transaction findTransactionById(Long id);

    @Query("SELECT t FROM Transaction t WHERE t.etat =?1")
    List<Transaction> findTransactionsEnAttente(String etat);
   // List<Transaction> findByEtat(String etat);
   List<Transaction> findAllByDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Transaction> findByEtat(TransactionState pending);

    //List<Transaction> findByStatus(TransactionStatus status);

    Optional<Transaction> findByValidationCode(String validationCode);

  /*  @Query("SELECT t FROM Transaction t WHERE t.numeroCompteDestinataire = :numeroCompteDestinataire AND t.codeValidation = :codeValidation")
    Transaction findByNumeroCompteDestinataireAndCodeValidation(@Param("numeroCompteDestinataire") String numeroCompteDestinataire, @Param("codeValidation") String codeValidation);

   */


}
