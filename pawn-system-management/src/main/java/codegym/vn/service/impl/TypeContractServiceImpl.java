package codegym.vn.service.impl;

import codegym.vn.entity.TypeContract;
import codegym.vn.repository.TypeContractRepository;
import codegym.vn.service.TypeContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TypeContractServiceImpl implements TypeContractService {
    @Autowired
    TypeContractRepository typeContractRepository;
    @Override
    public List<TypeContract> getTypeContractList() {
        return typeContractRepository.findAll();
    }
}
