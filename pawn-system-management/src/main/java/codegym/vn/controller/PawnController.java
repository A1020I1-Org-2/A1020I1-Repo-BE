package codegym.vn.controller;

import codegym.vn.entity.Contract;
import codegym.vn.entity.TypeProduct;
import codegym.vn.service.ContractService;
import codegym.vn.service.TypeProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(value = "/pawn")
public class PawnController {
    @Autowired
    ContractService contractService;

    @Autowired
    TypeProductService typeProductService;

    @RequestMapping(value = "/typeProductList", method = RequestMethod.GET)
    public ResponseEntity<List<TypeProduct>> getAllTypeProduct() {
        List<TypeProduct> typeProducts = typeProductService.getAll();
        if (typeProducts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(typeProducts, HttpStatus.OK);
    }

    @RequestMapping(value = "/pawnList", method = RequestMethod.GET)
    public ResponseEntity<Page<Contract>> getAllPawn(@PageableDefault(size = 5) Pageable pageable) {
        Page<Contract> pawnLists = contractService.getAllPawn(pageable);
        if (pawnLists.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pawnLists, HttpStatus.OK);
    }

    @RequestMapping(value = "/pawnView/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contract> getPawnById(@PathVariable String id) {
        Contract pawn = contractService.findPawnById(id);
        if (pawn == null) {
            return new ResponseEntity<Contract>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Contract>(pawn, HttpStatus.OK);
    }

    @RequestMapping(value = "/pawnSearch", method = RequestMethod.GET)
    public ResponseEntity<Page<Contract>> getAllPawnSearch(@RequestParam("search") String search,
                                                           @RequestParam("typeSearch") String typeSearch,
                                                           @PageableDefault(size = 5) Pageable pageable) {
        Page<Contract> pawnLists = contractService.searchPawn(search,typeSearch,pageable);
        if (pawnLists.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pawnLists, HttpStatus.OK);
    }

}
