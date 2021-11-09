package codegym.vn.dto;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class EditContract implements Validator {
    private String contractId;
    @NotEmpty
    private String customerName;
    @NotEmpty
    private String productName;
    @NotEmpty
    private String productType;
    @NotEmpty
    private Date startDate;
    @NotEmpty
    private Date endDate;
    @NotEmpty
    private String contractType;
    @NotEmpty
    private String status;

    public EditContract() {
    }

    public EditContract(String contractId, @NotEmpty String customerName, @NotEmpty String productName, @NotEmpty String productType, @NotEmpty Date startDate, @NotEmpty Date endDate, @NotEmpty String contractType, @NotEmpty String status) {
        this.contractId = contractId;
        this.customerName = customerName;
        this.productName = productName;
        this.productType = productType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.contractType = contractType;
        this.status = status;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
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

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return EditContract.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
