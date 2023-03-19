package FinanceMe.PiDev.Repository;

import FinanceMe.PiDev.Enteties.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction , Long> {

    Transaction findTransactionById(Long id);

}
