package autobot.customs;

public class Customer 
{
    private ProductCart cart;

    private int id;
    private String name;
    private String address;
    private String phoneNumber;
    private String cardType;
    private String cardNumber;
    private String cardExp;
    private String secCode;
    private int carSelected;

    public Customer(ProductCart cart){
        this.id = 0;
        this.cart = cart;
        this.name = "";
        this.address = "";
        this.phoneNumber = "";
        this.cardType = "";
        this.cardNumber = "";
        this.cardExp = "";
        this.secCode = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
    public Customer(int num)
    {
        this.carSelected = num;
    }

    public int getCarSelected() {
        return carSelected;
    }

    public void setCarSelected(int carSelected) {
        this.carSelected = carSelected;
    }

    public ProductCart getCart() {
        return cart;
    }

    public void setCart(ProductCart cart) {
        this.cart = cart;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardExp() {
        return cardExp;
    }

    public void setCardExp(String cardExp) {
        this.cardExp = cardExp;
    }

    public String getSecCode() {
        return secCode;
    }

    public void setSecCode(String secCode) {
        this.secCode = secCode;
    }        
}
