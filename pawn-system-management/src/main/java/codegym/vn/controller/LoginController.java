package codegym.vn.controller;

import codegym.vn.dto.AccountResponse;
import codegym.vn.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private LoginService loginService;

    @GetMapping(value = "/login")
    public ResponseEntity<AccountResponse> doLogin(@Param("userName") String userName,
                                                   @Param("password") String password){
        AccountResponse account = this.loginService.doLogin(userName, password);
        if (account == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @GetMapping("/random")
    public ResponseEntity<String> randomStuff(){
        return new ResponseEntity<>("Kiểm tra jwt thành công", HttpStatus.OK);
    }

}
