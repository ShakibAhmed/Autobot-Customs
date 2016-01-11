package autobot.customs;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Sale 
{
    private int id;
    private String customerName, customerAddress, customerPhone, customerCardType,
            customerCardNumber,  customerCardExp, secCode, cartDetails;
    private BigDecimal cartTotal;
    private java.sql.Timestamp time;

    public Sale(int id, String customerName, String customerAddress, String customerPhone, String customerCardType, String customerCardNumber, String customerCardExp, String secCode, String cartDetails, BigDecimal cartTotal, Timestamp time) {
        this.id = id;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
        this.customerCardType = customerCardType;
        this.customerCardNumber = customerCardNumber;
        this.customerCardExp = customerCardExp;
        this.secCode = secCode;
        this.cartDetails = cartDetails;
        this.cartTotal = cartTotal;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerCardType() {
        return customerCardType;
    }

    public void setCustomerCardType(String customerCardType) {
        this.customerCardType = customerCardType;
    }

    public String getCustomerCardNumber() {
        return customerCardNumber;
    }

    public void setCustomerCardNumber(String customerCardNumber) {
        this.customerCardNumber = customerCardNumber;
    }

    public String getCustomerCardExp() {
        return customerCardExp;
    }

    public void setCustomerCardExp(String customerCardExp) {
        this.customerCardExp = customerCardExp;
    }

    public String getSecCode() {
        return secCode;
    }

    public void setSecCode(String secCode) {
        this.secCode = secCode;
    }

    public String getCartDetails() {
        return cartDetails;
    }

    public void setCartDetails(String cartDetails) {
        this.cartDetails = cartDetails;
    }

    public BigDecimal getCartTotal() {
        return cartTotal;
    }

    public void setCartTotal(BigDecimal cartTotal) {
        this.cartTotal = cartTotal;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
    
    
    
    
}
