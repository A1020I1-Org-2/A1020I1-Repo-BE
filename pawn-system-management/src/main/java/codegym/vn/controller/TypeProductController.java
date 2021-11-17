package codegym.vn.controller;

import codegym.vn.entity.TypeContract;
import codegym.vn.entity.TypeProduct;
import codegym.vn.service.TypeContractService;
import codegym.vn.service.TypeProductService;
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
@RequestMapping(value = "/typeProduct")
public class TypeProductController {
    @Autowired
    TypeProductService typeProductService;
    @GetMapping("/listTypeProduct")
    public ResponseEntity<List<TypeProduct>> getTypeProductList(){
        List<TypeProduct> typeProductList = typeProductService.getTypeProductList();
        if(typeProductList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(typeProductList, HttpStatus.OK);
    }
}
