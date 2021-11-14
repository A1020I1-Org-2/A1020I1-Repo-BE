package codegym.vn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class TypeContract {
    @Id
    private Integer typeContractId;
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "typeContract")
    private Set<Contract> contracts;

    public TypeContract() {
    }

    public Integer getTypeContractId() {
        return typeContractId;
    }

    public void setTypeContractId(Integer typeContractId) {
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
