package codegym.vn.service;

import codegym.vn.dto.EditContract;
import codegym.vn.entity.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface ContractService {
    Contract findById(String id);
    List<Contract> contractListTop10();
    void contractUpdate(Contract contract);
    List<Contract> contractListTop10Search(String name);

    void contractDelete(Contract contract);
}
