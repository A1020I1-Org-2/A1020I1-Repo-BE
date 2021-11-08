package codegym.vn.service;

import codegym.vn.entity.Contract;
import codegym.vn.entity.ContractDTO;

import javax.mail.MessagingException;

public interface ContractService {
    void createPawnContract(ContractDTO contract) throws MessagingException;
}
