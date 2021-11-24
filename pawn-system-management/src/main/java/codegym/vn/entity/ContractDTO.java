package codegym.vn.entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.sql.Date;

public class ContractDTO {
    private String contractId;
    @NotBlank(message = "Ảnh đồ cầm không được để trống,")
    private String productImg;
    @NotBlank(message = "Tên sản phẩm không được để trống.")
    private String productName;
    @NotEmpty(message ="Tiền lãi không được để trống." )
    @Min(value = 50000,message = "Tiền lãi phải tối thiểu 500000 vnđ.")
    private int interestMoney;
    private int receiveMoney;
    @NotEmpty(message ="Tiền cho vay không được để trống." )
    @Min(value = 500000,message = "Tiền cho vay phải tối thiểu 5000000 vnđ.")
    private int loanMoney;
    private Date liquidationDate;
    private Date startDate;
    private Date endDate;
    @NotEmpty(message = "Số lượng không được để trống.")
    @Positive(message = "Số lượng phải lớn hơn 0.")
    private int quantity;
    private StatusContract statusContract;
    private TypeProduct typeProduct;
    private TypeContract typeContract;

    private String employeeId;

    private String customerId;

    public ContractDTO() {
    }

    public ContractDTO(String contractId, @NotBlank(message = "Ảnh đồ cầm không được để trống,") String productImg, @NotBlank(message = "Tên sản phẩm không được để trống.") String productName, @NotEmpty(message = "Tiền lãi không được để trống.") @Min(value = 50000, message = "Tiền lãi phải tối thiểu 500000 vnđ.") int interestMoney, int receiveMoney, @NotEmpty(message = "Tiền cho vay không được để trống.") @Min(value = 500000, message = "Tiền cho vay phải tối thiểu 5000000 vnđ.") int loanMoney, Date liquidationDate, Date startDate, Date endDate, @NotEmpty(message = "Số lượng không được để trống.") @Positive(message = "Số lượng phải lớn hơn 0.") int quantity, StatusContract statusContract, TypeProduct typeProduct, TypeContract typeContract, String employeeId, String customerId) {
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
        this.typeProduct = typeProduct;
        this.typeContract = typeContract;
        this.employeeId = employeeId;
        this.customerId = customerId;
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

    public int getInterestMoney() {
        return interestMoney;
    }

    public void setInterestMoney(int interestMoney) {
        this.interestMoney = interestMoney;
    }

    public int getReceiveMoney() {
        return receiveMoney;
    }

    public void setReceiveMoney(int receiveMoney) {
        this.receiveMoney = receiveMoney;
    }

    public int getLoanMoney() {
        return loanMoney;
    }

    public void setLoanMoney(int loanMoney) {
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public StatusContract getStatusContract() {
        return statusContract;
    }

    public void setStatusContract(StatusContract statusContract) {
        this.statusContract = statusContract;
    }

    public TypeProduct getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(TypeProduct typeProduct) {
        this.typeProduct = typeProduct;
    }

    public TypeContract getTypeContract() {
        return typeContract;
    }

    public void setTypeContract(TypeContract typeContract) {
        this.typeContract = typeContract;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }
}
