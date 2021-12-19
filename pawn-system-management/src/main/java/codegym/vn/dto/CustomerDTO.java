package codegym.vn.dto;

import codegym.vn.validate.CheckAge;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
@Entity
public class CustomerDTO {
    @Id
    private String customerId;
    @NotBlank(message = "Name can't be blank.")
    @Pattern(regexp = "^[a-z0-9A-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ ]*$", message = "Name can't contain symbols.")
    private String name;
    @CheckAge
    private String dateOfBirth;
    @Email(message = "Email must follow format abc@abc.com")
    private String email;
    @NotBlank(message = "Address can't be blank.")
    @Pattern(regexp = "^[a-z0-9A-Z,_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ ]*$", message = "Address can't contain symbols.")
    private String address;
    @Pattern(regexp = "^(\\d{10,12})$",
            message = "Phone number must contain 10 - 12 digits.")
    private String phone;
    private boolean gender;
    @Pattern(regexp = "^(\\d{9,11})$",
            message = "Id card number must contain 9 - 11 digits.")
    private String idCard;
//    @NotEmpty(message = "Image can't empty")
    private String img;

    public CustomerDTO() {
    }

    public CustomerDTO(String customerId, String name, String dateOfBirth, String email,  String address,
            String phone, boolean gender, String idCard, String img) {
        this.customerId = customerId;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
        this.idCard = idCard;
        this.img = img;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
