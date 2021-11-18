package codegym.vn.service;

import codegym.vn.dto.AccountResponse;

public interface LoginService {
    AccountResponse doLogin(String userName, String password);
}
