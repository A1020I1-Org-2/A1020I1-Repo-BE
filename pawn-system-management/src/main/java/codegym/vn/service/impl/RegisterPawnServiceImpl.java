package codegym.vn.service.impl;

<<<<<<< HEAD
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
                                    RegisterPawnRepository registerPawnRepository){
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
        registerPawn.setPawnType(pawnTypeRepository.findById(registerRequest.getPawTypeId()).orElse(null));
        registerPawn.setName(registerRequest.getName());
        registerPawn.setPhone(registerRequest.getPhone());
        registerPawn.setEmail(registerRequest.getEmail());
        registerPawn.setAddress(registerRequest.getAddress());
        registerPawn.setNote(registerRequest.getNote());
        registerPawnRepository.save(registerPawn);
    }
=======
import codegym.vn.service.RegisterPawnService;
import org.springframework.stereotype.Service;

@Service
public class RegisterPawnServiceImpl implements RegisterPawnService {
>>>>>>> c75c02e65d56a857f83378e056f34a1cd923a02d
}
