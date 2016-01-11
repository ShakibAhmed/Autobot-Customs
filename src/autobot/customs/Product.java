package autobot.customs;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Product 
{
    private int number;
    private String name;
    private BigDecimal unitPrice;
    private int quantity;
    private BigDecimal total;
    private String category;
    private String car;
    private String image;
 

    public Product(int number, String name, BigDecimal unitPrice, int quantity) 
    {
        this.number = number;
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.total = unitPrice;
    }
    
    public Product(int number, String name, BigDecimal unitPrice, int quantity, String category, String car, String image) 
    {
        this.number = number;
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.category = category;
        this.car = car;
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name =  name;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(int quantity, BigDecimal price) {
        total = price.multiply(new BigDecimal(quantity).setScale(2)).setScale(2);               
    }

    
}
