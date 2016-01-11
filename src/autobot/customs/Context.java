/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autobot.customs;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Context {
    private final static Context instance = new Context();

    public static Context getInstance() {
        return instance;
    }

    List<Product> products = new ArrayList<Product>();
    ProductCart cart = new ProductCart(products, new BigDecimal(0));
    Customer customer = new Customer(cart);
    List<Customer> customerList = new ArrayList<Customer>();
    
    public void setDatabase(List<Product> products)
    {
        this.products = products;
        
    }
    
    public List<Product> getDatabase()
    {
        return products;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }
    
    public void addCustomer(Customer customer)
    {
        customerList.add(customer);
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }
    
    
    
    public void setCart(ProductCart inputCart)
    {
        cart = inputCart;
    }
    public ProductCart getCart()
    {
        return cart;
    }
}