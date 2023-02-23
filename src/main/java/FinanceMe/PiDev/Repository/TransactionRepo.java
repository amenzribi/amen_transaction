package FinanceMe.PiDev.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface Transaction extends JpaRepository<Transaction,Long> {
}
