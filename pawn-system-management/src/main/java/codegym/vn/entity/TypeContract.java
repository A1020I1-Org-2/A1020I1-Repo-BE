package codegym.vn.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class TypeContract {
    @Id
    private int typeContractId;
    private String name;

    @OneToMany(mappedBy = "typeContract")
    @JsonIgnore
    private Set<Contract> contracts;

    public TypeContract() {
    }

    public TypeContract(int typeContractId, String name) {
        this.typeContractId = typeContractId;
        this.name = name;
    }

    public int getTypeContractId() {
        return typeContractId;
    }

    public void setTypeContractId(int typeContractId) {
        this.typeContractId = typeContractId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(Set<Contract> contracts) {
        this.contracts = contracts;
    }
}
