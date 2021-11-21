package codegym.vn.service;

import codegym.vn.dto.AccountResponse;
import codegym.vn.dto.ChangePasswordForm;

public interface LoginService {
    AccountResponse doLogin(String userName, String password);

    boolean doChangePassword(ChangePasswordForm form);
}
