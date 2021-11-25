package codegym.vn.service.impl;

import codegym.vn.config_sercurity.JwtTokenUtil;
import codegym.vn.dto.AccountResponse;
import codegym.vn.dto.ChangePasswordForm;
import codegym.vn.entity.Account;
import codegym.vn.service.AccountService;
import codegym.vn.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AccountService accountService;

    @Override
    public AccountResponse doLogin(String userName, String password) {
        Authentication authentication;
        try {
            // Xác thực từ username và password.
            authentication = authenticationManager.
                    authenticate(new UsernamePasswordAuthenticationToken(userName, password));
        }catch (Exception e){
            return null;
        }

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Account account = this.accountService.findById(userName);
        String jwt = jwtTokenUtil.generateJwtToken(userName);
        String role = "";
        for (GrantedAuthority authority : authentication.getAuthorities()){
            if("ROLE_ADMIN".equals(authority.getAuthority())){
                role = "ROLE_ADMIN";
                break;
            }else if ("ROLE_EMPLOYEE".equals(authority.getAuthority())){
                role = "ROLE_EMPLOYEE";
                break;
            }
        }
        if("".equals(role)){
            return null;
        }
        return new AccountResponse(userName, account.getLastUpdate(), jwt, role);
    }

    @Override
    public boolean doChangePassword(ChangePasswordForm form) {
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(form.getUsername(),
                    form.getPassword()));
        }catch (Exception e){
            return false;
        }
        Account account = this.accountService.findById(form.getUsername());
        if(account == null){
            return false;
        }
        account.setPassword(encode.encode(form.getNewPassword()));
        account.setLastUpdate(new Date());
        return this.accountService.save(account) != null;
    }
}