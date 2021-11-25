package codegym.vn.service.impl;

import codegym.vn.entity.Account;
import codegym.vn.repository.AccountRepository;
import codegym.vn.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository repository;

    @Override
    public Account findById(String userName) {
        return this.repository.findById(userName).orElse(null);
    }

    @Override
    public Account save(Account account) {
        return this.repository.save(account);
    }
}
