package codegym.vn.entity;

import javax.persistence.*;

@Entity
public class RegisterPawn {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer registerPawnId;
    private String name;
    private String email;
    private String address;
    private String phone;
<<<<<<< HEAD
    //private Boolean status;
=======
    private boolean status;
>>>>>>> c75c02e65d56a857f83378e056f34a1cd923a02d
    private String note;

    @ManyToOne(targetEntity = PawnType.class)
    @JoinColumn(name = "pawn_type_id", referencedColumnName = "pawnTypeId")
    private PawnType pawnType;

    public RegisterPawn() {
    }

    public Integer getRegisterPawnId() {
        return registerPawnId;
    }

    public void setRegisterPawnId(Integer registerPawnId) {
        this.registerPawnId = registerPawnId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

<<<<<<< HEAD
=======
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

>>>>>>> c75c02e65d56a857f83378e056f34a1cd923a02d
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public PawnType getPawnType() {
        return pawnType;
    }

    public void setPawnType(PawnType pawnType) {
        this.pawnType = pawnType;
    }
}
