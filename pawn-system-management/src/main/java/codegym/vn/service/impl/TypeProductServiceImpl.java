package codegym.vn.service.impl;

import codegym.vn.entity.TypeProduct;
import codegym.vn.repository.TypeProductRepository;
import codegym.vn.service.TypeProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TypeProductServiceImpl implements TypeProductService {
    @Autowired
    TypeProductRepository typeProductRepository;

    @Override
    public List<TypeProduct> getTypeProductList() {
        return typeProductRepository.findAll();
    }

    @Override
    public List<TypeProduct> getAll() {
        return typeProductRepository.findAll();
    }
}
