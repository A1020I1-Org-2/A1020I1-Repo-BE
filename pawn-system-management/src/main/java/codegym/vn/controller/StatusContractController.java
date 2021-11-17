package codegym.vn.controller;

import codegym.vn.entity.StatusContract;
import codegym.vn.service.StatusContractService;
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
@RequestMapping(value = "/statusContract")
public class StatusContractController {
    @Autowired
    StatusContractService statusContractService;
    @GetMapping("/listStatusContract")
    public ResponseEntity<List<StatusContract>> getStatusContractList(){
        List<StatusContract> statusContractList = statusContractService.getStatusContractList();
        if(statusContractList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(statusContractList, HttpStatus.OK);
    }
}
