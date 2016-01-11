package autobot.customs;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ProductCart 
{
    private List<Product> products;
    private BigDecimal total;

    public ProductCart(List<Product> products, BigDecimal total) {
        this.products = products;
        this.total = total;
    }

    public List<Product> getCart() {
        return products;
    }
    public void setProducts(List<Product> products)
    {
        this.products = products;
    }
    
    public List<Product> getProductList()
    {
        return products;
    }

    public void addToCart(Product product) {
        products.add(product);
    }
    
    public void increaseQuantity(int index)
    {
        int newQuantity = products.get(index).getQuantity() + 1;
        products.get(index).setQuantity(newQuantity);
    }
    
    public void decreaseQuantity(int index)
    {
        int newQuantity = products.get(index).getQuantity() - 1;
        products.get(index).setQuantity(newQuantity);
    }
    
    public void removeProduct(int index)
    {
        products.remove(index);
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal() {
        total = new BigDecimal(0);
        for(Product p : products)
        {
         //   total += p.getQuantity()*p.getUnitPrice();
            total = total.add(p.getUnitPrice().multiply(new BigDecimal(p.getQuantity()).setScale(2))); 
            //total = Math.round(total*100/100.0d);
        }
    }
    
    
}
