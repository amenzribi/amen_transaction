package FinanceMe.PiDev.Repository;

import FinanceMe.PiDev.Enteties.Compte;
import javafx.scene.effect.SepiaTone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CompteRepository extends JpaRepository<Compte , Long> {
    //Compte  findCompteByEmail(String Email);
}
