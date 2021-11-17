package codegym.vn.controller;

import codegym.vn.entity.PawnType;
import codegym.vn.entity.RegisterPawn;
import codegym.vn.http_request.RegisterRequest;
import codegym.vn.service.RegisterPawnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(value = {"/home", "/"})
public class HomeController {
    private final RegisterPawnService registerPawnService;

    @Autowired
    private HomeController(RegisterPawnService registerPawnService){
        this.registerPawnService = registerPawnService;
    }

    @PostMapping("/")
    public ResponseEntity<RegisterPawn> createRegister(@Validated @RequestBody RegisterRequest registerRequest,
                                                       BindingResult bindingResult){
        if (!bindingResult.hasErrors()){
            registerPawnService.createNewRegister(registerRequest);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public List<PawnType> getPawnTypes(){
        return registerPawnService.getAllPawnTypes();
    }
}
