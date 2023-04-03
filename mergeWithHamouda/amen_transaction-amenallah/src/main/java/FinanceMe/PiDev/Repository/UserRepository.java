package FinanceMe.PiDev.Repository;


import FinanceMe.PiDev.Enteties.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
   // User findByUsername(String username);
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String Email);

    User findByEmail(String email);

    User findByIdUser(Long IdUser);

 List<User> findByBanned(Boolean isBanned);


}
