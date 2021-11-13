package codegym.vn.service;

import codegym.vn.entity.PawnType;
import codegym.vn.http_request.RegisterRequest;

import java.util.List;

public interface RegisterPawnService {
    List<PawnType> getAllPawnTypes();

    void createNewRegister(RegisterRequest registerRequest);
}
