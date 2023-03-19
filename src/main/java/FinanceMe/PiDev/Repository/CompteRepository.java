package FinanceMe.PiDev.Repository;

import FinanceMe.PiDev.Enteties.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte , Long> {
}
