package FinanceMe.PiDev.Repository;

import FinanceMe.PiDev.Enteties.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransactionRepository extends JpaRepository<Transaction , Long> {

    Transaction findTransactionById(Long id);

  /*  @Query("SELECT t FROM Transaction t WHERE t.numeroCompteDestinataire = :numeroCompteDestinataire AND t.codeValidation = :codeValidation")
    Transaction findByNumeroCompteDestinataireAndCodeValidation(@Param("numeroCompteDestinataire") String numeroCompteDestinataire, @Param("codeValidation") String codeValidation);

   */


}
