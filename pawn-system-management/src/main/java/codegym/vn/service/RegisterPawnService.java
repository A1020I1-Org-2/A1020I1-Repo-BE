package codegym.vn.service;

import codegym.vn.entity.PawnType;
import codegym.vn.dto.RegisterRequest;

import java.util.List;

public interface RegisterPawnService {
    List<PawnType> getAllPawnTypes();

    void createNewRegister(RegisterRequest registerRequest);
}
