package codegym.vn.service.impl;

import codegym.vn.entity.Finance;
import codegym.vn.repository.FinanceRepository;
import codegym.vn.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinanceServiceImpl implements FinanceService {
    @Autowired
    FinanceRepository financeRepository;

    @Override
    public Finance findAllFinance(int i) {
        return financeRepository.findById(i).orElse(null);
    }
}
