package codegym.vn.controller;

import codegym.vn.entity.Finance;
import codegym.vn.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(value = "/finance")
public class FinanceController {

//    @Autowired
//    FinanceService financeService;
//
//    @RequestMapping(value = "/financeView", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Finance> getFinance() {
//        Finance finance = financeService.findAllFinance(1);
//        if (finance == null) {
//            return new ResponseEntity<Finance>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<Finance>(finance, HttpStatus.OK);
//    }
}
