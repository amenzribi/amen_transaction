import FinanceMe.PiDev.Exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AccountService {
    private AccountRepository accountRepository  ;

    @Autowired
    public AccountService(AccountRepository accountRepository ){
        this.accountRepository = accountRepository;
    }

    public Account addAccount(Account account ){

        return accountRepository.save(account);
    }

    public List<Account> findAllAccount(){
        return accountRepository.findAll();
    }



    public Account updateAccount(Account account )
    {
        return accountRepository.save(account);
    }




    public Account findAccountById(Long id){
        // return transactionRepo.findTransactionById(id).orElseThrow(()-> new UserNotFoundException("user bot found"));
        return accountRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User Not Found"));

    }

    public void deleteAccount(Long id){
        //  transactionRepo.deleteTransactionById(id);
        accountRepository.deleteById(id);
    }
}
