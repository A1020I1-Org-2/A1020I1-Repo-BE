package codegym.vn.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "pawn_type")
public class PawnType {
    @Id
    private Integer pawnTypeId;
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "pawnType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RegisterPawn> registerPawns;

    public PawnType() {
    }

    public Integer getPawnTypeId() {
        return pawnTypeId;
    }

    public void setPawnTypeId(Integer pawnTypeId) {
        this.pawnTypeId = pawnTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RegisterPawn> getRegisterPawns() {
        return registerPawns;
    }

    public void setRegisterPawns(List<RegisterPawn> registerPawns) {
        this.registerPawns = registerPawns;
    }
}
