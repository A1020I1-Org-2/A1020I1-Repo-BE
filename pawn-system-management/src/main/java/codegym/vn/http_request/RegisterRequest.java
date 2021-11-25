package codegym.vn.http_request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class RegisterRequest {
    private Integer registerPawnId;
    @NotBlank(message = "Name can't be blank.")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Name can't contain symbols.")
    private String name;
    @Email(message = "Email must follow format abc@abc.com")
    private String email;
    @NotBlank(message = "Address can't be blank.")
    private String address;
    @Pattern(regexp = "^(\\d{10}|\\d{12})$",
            message = "Phone number must contain 9 or 12 digits.")
    private String phone;
    private String note;
    private Integer pawnTypeId;

    public RegisterRequest() {
    }

    public RegisterRequest(Integer registerPawnId, String name, String email, String address, String phone,
                            String note, Integer pawnTypeId) {
        this.registerPawnId = registerPawnId;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.note = note;
        this.pawnTypeId = pawnTypeId;
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


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getPawnTypeId() {
        return pawnTypeId;
    }

    public void setPawnTypeId(Integer pawTypeId) {
        this.pawnTypeId = pawTypeId;
    }
}
