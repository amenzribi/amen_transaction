import FinanceMe.PiDev.Exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserService {
    private UserRepository userRepository  ;

    @Autowired
    public UserService(UserRepository userRepository  ){
        this.userRepository = userRepository;
    }

    public User addUser(User user){

        return userRepository.save(user);
    }
    public List<User> findAllUsers(){
        return userRepository.findAll();
    }



    public User updateUser(User user )
    {
        return userRepository.save(user);
    }




    public User findUserById(Integer id){
        // return transactionRepo.findTransactionById(id).orElseThrow(()-> new UserNotFoundException("user bot found"));
        return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User Not Found"));

    }

    public void deleteUser(Integer id){
        //  transactionRepo.deleteTransactionById(id);
        userRepository.deleteById(id);
    }
}
