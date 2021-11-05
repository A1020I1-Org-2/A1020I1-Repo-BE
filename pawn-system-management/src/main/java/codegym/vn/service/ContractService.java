package codegym.vn.service;

import codegym.vn.entity.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContractService {
    Contract findById(String id);
    List<Contract> contractListTop10();
    void contractEdit(Contract contract);
    List<Contract> contractListTop10Search(String name);
}
