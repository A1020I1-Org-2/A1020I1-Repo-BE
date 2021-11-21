package codegym.vn.service;

import codegym.vn.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;

public interface AccountService {
    Account findById(String userName);

    Account save(Account account);

    Account findByUserNameAndPassword(String username, String password);
}
