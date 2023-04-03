package FinanceMe.PiDev.Repository;

import FinanceMe.PiDev.Enteties.Compte;
import FinanceMe.PiDev.Enteties.Transaction;
import javafx.scene.effect.SepiaTone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface CompteRepository extends JpaRepository<Compte , Long> {
    //Compte  findCompteByEmail(String Email);
//    @Query("SELECT t FROM Transaction t , Compte c WHERE t.id :=c.id_compte")
//    Transaction getBiId(Long id);
}
