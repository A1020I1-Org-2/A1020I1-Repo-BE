package codegym.vn.controller;

import codegym.vn.entity.StatusContract;
import codegym.vn.entity.TypeContract;
import codegym.vn.service.StatusContractService;
import codegym.vn.service.TypeContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(value = "/typeContract")
public class TypeContractController {
    @Autowired
    TypeContractService typeContractService;
    @GetMapping("/listTypeContract")
    public ResponseEntity<List<TypeContract>> getTypeContractList(){
        List<TypeContract> typeContractList = typeContractService.getTypeContractList();
        if(typeContractList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(typeContractList, HttpStatus.OK);
    }
}
