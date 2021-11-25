package codegym.vn.service.impl;

import codegym.vn.entity.PawnType;
import codegym.vn.entity.RegisterPawn;
import codegym.vn.http_request.RegisterRequest;
import codegym.vn.repository.PawnTypeRepository;
import codegym.vn.repository.RegisterPawnRepository;
import codegym.vn.service.RegisterPawnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegisterPawnServiceImpl implements RegisterPawnService {
    private final PawnTypeRepository pawnTypeRepository;
    private final RegisterPawnRepository registerPawnRepository;

    @Autowired
    private RegisterPawnServiceImpl(PawnTypeRepository pawnTypeRepository,
                                    RegisterPawnRepository registerPawnRepository) {
        this.registerPawnRepository = registerPawnRepository;
        this.pawnTypeRepository = pawnTypeRepository;
    }

    @Override
    public List<PawnType> getAllPawnTypes() {
        return new ArrayList<>(pawnTypeRepository.findAll());
    }

    @Override
    public void createNewRegister(RegisterRequest registerRequest) {
        RegisterPawn registerPawn = new RegisterPawn();
        registerPawn.setPawnType(pawnTypeRepository.findById(registerRequest.getPawnTypeId()).orElse(null));
        registerPawn.setName(registerRequest.getName().trim());
        registerPawn.setPhone(registerRequest.getPhone());
        registerPawn.setEmail(registerRequest.getEmail().trim());
        registerPawn.setAddress(registerRequest.getAddress().trim());
        registerPawn.setNote(registerRequest.getNote().trim());
        registerPawnRepository.save(registerPawn);
    }
}
