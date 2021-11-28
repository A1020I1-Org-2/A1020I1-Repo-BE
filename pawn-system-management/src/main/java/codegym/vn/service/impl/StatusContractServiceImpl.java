package codegym.vn.service.impl;

import codegym.vn.entity.StatusContract;
import codegym.vn.repository.StatusContractRepository;
import codegym.vn.service.StatusContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StatusContractServiceImpl implements StatusContractService {
    @Autowired
    StatusContractRepository statusContractRepository;
    @Override
    public List<StatusContract> getStatusContractList() {
        return statusContractRepository.findAll();
    }
}
