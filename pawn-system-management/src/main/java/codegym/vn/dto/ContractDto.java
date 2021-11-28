package codegym.vn.dto;

import codegym.vn.entity.*;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

public class ContractDto implements Validator {
    @Pattern(regexp = "^HD-[0-9]{4}$",message = "Mời nhập đúng định dạng HD-XXXX với X là số từ 0-9")
    private String contractId;
    private String productImg;
    private String productName;
    private Integer interestMoney;
    @NotNull(message = "Tổng tiền không để trống!")
    @Max(value = 9999999,message = "Chiều dài kí tự nhỏ hơn 9 ")
    private Integer receiveMoney;
    private Integer loanMoney;
    @NotNull(message = "Ngày thanh lý không để trống!")
    private Date liquidationDate;
    private Date startDate;
    private Date endDate;
    private Integer quantity;
    private StatusContract statusContract;
    @NotBlank(message = "Khách hàng không được để trống!")
    private String customerId;
    private TypeContract typeContract;
    private TypeProduct typeProduct;
    private String employeeId;

    public ContractDto() {
    }

    public ContractDto(@Pattern(regexp = "^HD-[0-9]{4}$",
            message = "Mời nhập đúng định dạng HD-XXXX với X là số từ 0-9")
                               String contractId, String productImg, String productName,
                       Integer interestMoney, @NotNull(message = "Tổng tiền không để trống!")
                       @Max(value = 9999999, message = "Chiều dài kí tự nhỏ hơn 9 ")
                               Integer receiveMoney, Integer loanMoney, Date liquidationDate,
                       @NotNull(message = "Ngày thanh lý không để trống!") Date startDate,
                       Date endDate, Integer quantity, StatusContract statusContract,
                       @NotBlank(message = "Khách hàng không được để trống!") String customerId,
                       TypeContract typeContract, TypeProduct typeProduct, String employeeId) {
        this.contractId = contractId;
        this.productImg = productImg;
        this.productName = productName;
        this.interestMoney = interestMoney;
        this.receiveMoney = receiveMoney;
        this.loanMoney = loanMoney;
        this.liquidationDate = liquidationDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.quantity = quantity;
        this.statusContract = statusContract;
        this.customerId = customerId;
        this.typeContract = typeContract;
        this.typeProduct = typeProduct;
        this.employeeId = employeeId;
    }



    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getInterestMoney() {
        return interestMoney;
    }

    public void setInterestMoney(Integer interestMoney) {
        this.interestMoney = interestMoney;
    }

    public Integer getReceiveMoney() {
        return receiveMoney;
    }

    public void setReceiveMoney(Integer receiveMoney) {
        this.receiveMoney = receiveMoney;
    }

    public Integer getLoanMoney() {
        return loanMoney;
    }

    public void setLoanMoney(Integer loanMoney) {
        this.loanMoney = loanMoney;
    }

    public Date getLiquidationDate() {
        return liquidationDate;
    }

    public void setLiquidationDate(Date liquidationDate) {
        this.liquidationDate = liquidationDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public StatusContract getStatusContract() {
        return statusContract;
    }

    public void setStatusContract(StatusContract statusContract) {
        this.statusContract = statusContract;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String  customerId) {
        this.customerId = customerId;
    }

    public TypeContract getTypeContract() {
        return typeContract;
    }

    public void setTypeContract(TypeContract typeContract) {
        this.typeContract = typeContract;
    }

    public TypeProduct getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(TypeProduct typeProduct) {
        this.typeProduct = typeProduct;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ContractDto contractDto = (ContractDto) target;
        Date dateNow = new Date();
        long diff = (dateNow.getTime()- contractDto.getLiquidationDate().getTime())/(1000*60*60*24);
        if (diff !=0){
            errors.rejectValue("liquidationDate","liquidationDate.noMulti",
                    "Ngày thanh lý phải là ngày hiện tại!");
        }

    }
}
