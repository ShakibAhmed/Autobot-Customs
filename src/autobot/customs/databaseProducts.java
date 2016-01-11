package autobot.customs;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class databaseProducts 
{
    public int number;
    public String name;
    public BigDecimal price;
    public int quantity;
    
    public Product p;
    public List<Product> databaseProducts = new ArrayList<Product>();
    
    databaseManagement db = new databaseManagement();
    
    public databaseProducts()
    {
        
    }
    public void updateDatabaseProducts()
    {
        
    }
    
    public List<Product> getDatabaseProducts()
    {
        return databaseProducts;
    }
    
}
