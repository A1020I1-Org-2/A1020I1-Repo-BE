package codegym.vn.dto;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class EditContract implements Validator {
    private String contractID;
    private String customerID;
    @NotEmpty
    private String customerName;
    @NotEmpty
    private String productName;
    @NotEmpty
    private int productTypeID;
    @NotEmpty
    private Date startDate;
    @NotEmpty
    private Date endDate;

    @NotEmpty
    private int statusTypeID;

    public EditContract() {
    }

    public EditContract(String contractID, String customerID, String customerName, String productName, int productTypeID, Date startDate, Date endDate, int statusTypeID) {
        this.contractID = contractID;
        this.customerID = customerID;
        this.customerName = customerName;
        this.productName = productName;
        this.productTypeID = productTypeID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.statusTypeID = statusTypeID;
    }

    public String getContractID() {
        return contractID;
    }

    public void setContractID(String contractID) {
        this.contractID = contractID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
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

    public int getProductTypeID() {
        return productTypeID;
    }

    public void setProductTypeID(int productTypeID) {
        this.productTypeID = productTypeID;
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

    public int getStatusTypeID() {
        return statusTypeID;
    }

    public void setStatusTypeID(int statusTypeID) {
        this.statusTypeID = statusTypeID;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
