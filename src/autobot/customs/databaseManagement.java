package autobot.customs;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class databaseManagement 
{     
    Connection con;
    public databaseManagement()
    {
        try
        {
            String host = "jdbc:derby://localhost:1527/Autobot Customs";
            String username = "autobot";
            String password = "customs";
        
            con = DriverManager.getConnection(host, username, password);
        }
        catch ( SQLException err )
        {
            System.out.println( err.getMessage() );
        }
    }
    
    public List<Product> getAllProducts()
    {
        List<Product> products = new ArrayList<Product>();
        try
        {
            Statement stmt = con.createStatement();
            String SQL = "select * from APP.PRODUCTS";
            
            ResultSet rs = stmt.executeQuery(SQL);
                       
            while( rs.next() )
            {   
                int number = rs.getInt("Number");
                String name = rs.getString("Name");
                BigDecimal price = rs.getBigDecimal("Price").setScale(2);
                int quantity = rs.getInt("Quantity");
                String category = rs.getString("Category");
                String car = rs.getString("Car");
                String image = rs.getString("Image");
                
                Product p = new Product(number, name, price, quantity, category, car, image);
                products.add(p);
            }            
        }
        catch ( SQLException err )
        {
            System.out.println( err.getMessage() );
        } 
        return products;
    }
    
    public List<Sale> getAllSales()
    {
         List<Sale> sales = new ArrayList<Sale>();
        try
        {
            Statement stmt = con.createStatement();
            String SQL = "select * from APP.Sales";
            
            ResultSet rs = stmt.executeQuery(SQL);
                       
            while( rs.next() )
            {   
                int id = rs.getInt("Id");
                String name = rs.getString("CustomerName");
                String address = rs.getString("CustomerAddress");
                String phone = rs.getString("CustomerPhone");
                String cardType = rs.getString("CustomerCardType");
                String cardNumber = rs.getString("CustomerCardNumber");
                String cardExp = rs.getString("CustomerCardExpiration");
                String secCode = rs.getString("CustomerSecurityCode");
                String cartDetails =rs.getString("CartDetails");               
                BigDecimal cartTotal = rs.getBigDecimal("CartTotal").setScale(2);
                java.sql.Timestamp time = rs.getTimestamp("Time");                
                
                Sale s = new Sale(id, name, address, phone, cardType, cardNumber, cardExp, secCode, cartDetails, cartTotal, time);
                sales.add(s);
            }            
        }
        catch ( SQLException err )
        {
            System.out.println( err.getMessage() );
        } 
        return sales;
    }
    
    public List<String> listProductNamesByCar(String category, String car)
    {
        List<String> names = new ArrayList<>();
        try
        {
            Statement stmt = con.createStatement();
            String SQL = "select * from APP.PRODUCTS where CATEGORY='"+category+"' and CAR='"+car+"'";
            
            ResultSet rs = stmt.executeQuery(SQL);
            
            int i = 0;            
            while( rs.next() )
            {               
                String name = rs.getString("Name");
                names.add(name);
                i++;                       
            }            
        }
        catch ( SQLException err )
        {
            System.out.println( err.getMessage() );
        } 
        return names;
    }
    
    public String getImageURL(String inputName)
    {
        String URL = "images/no_image.png";
        try
        {
            Statement stmt = con.createStatement();
            String SQL = "select * from APP.PRODUCTS where NAME='"+inputName+"'";
            
            ResultSet rs = stmt.executeQuery(SQL);
            
            while(rs.next())
                if (rs.getString("Image")!= null)
                     URL = rs.getString("Image");                                
        }
        catch ( SQLException err )
        {
            System.out.println( err.getMessage() );
        } 
        return URL;
    }
    
    public Product getProductForCart(String inputName)
    {
        int number = 0, quantity = 1;
        String name = "";
        BigDecimal price = new BigDecimal(0);
        
        try
        {
            Statement stmt = con.createStatement();
            String SQL = "select * from APP.PRODUCTS where NAME='"+inputName+"'";
            
            ResultSet rs = stmt.executeQuery(SQL);
            
            while(rs.next())
            {
                if(inputName != null)
                {
                    number = rs.getInt("Number");
                    name = rs.getString("Name");
                    price = rs.getBigDecimal("Price").setScale(2);
                }
            }
        }
        catch ( SQLException err )
        {
            System.out.println( err.getMessage() );
        
        }
        Product product = new Product(number, name, price, quantity);
        return product;
    }
    
    public void addProductToDatabase(Product p) throws SQLException
    {
        int number = p.getNumber();
        String name = p.getName();
        BigDecimal price = p.getUnitPrice();
        int quantity = p.getQuantity();
        String category = p.getCategory();
        String car = p.getCar();
        String image = p.getImage();
        Statement stmt = con.createStatement();
        
        String sql = "INSERT INTO APP.PRODUCTS " +
                   "VALUES ("+number+",'"+name+"',"+quantity+",'"+category+"','"+image+"','"+car+"',"+price+")";
        stmt.executeUpdate(sql);
    }
    
    public void removeProductFromDatabase(Product p) throws SQLException
    {
        int number = p.getNumber();
        String name = p.getName();
        BigDecimal price = p.getUnitPrice();
        int quantity = p.getQuantity();
        String category = p.getCategory();
        String car = p.getCar();
        String image = p.getImage();
        Statement stmt = con.createStatement();
        
        String sql =  "DELETE FROM App.Products WHERE number="+number;
        stmt.executeUpdate(sql);
    }
 
    public void insertSale(Customer c) throws SQLException            
    {
        int id = c.getId();
        String name = c.getName();
        String address = c.getAddress();
        String phone = c.getPhoneNumber();
        String cardType = c.getCardType();
        String cardNumber = c.getCardNumber();
        String cardExp = c.getCardExp();
        String secCode = c.getSecCode();
        String cartDetails ="";
        for(Product p : c.getCart().getProductList())
            cartDetails += p.getName() + "@" + p.getUnitPrice() + "x"+p.getQuantity() + " "; 
        BigDecimal cartTotal = c.getCart().getTotal().setScale(2);
        
        Statement stmt = con.createStatement();
        
        String sql =  "INSERT INTO App.SALES VALUES("+id+",'"+name+"','"+address+ "','"+phone+"','"+cardType+ "','"+cardNumber+"','"+cardExp+"','"+secCode+ "','"+cartDetails+"',"+cartTotal+",'"+getCurrentTimeStamp()+"')";
        stmt.executeUpdate(sql);
    }
    
    private static java.sql.Timestamp getCurrentTimeStamp() {

	java.util.Date today = new java.util.Date();
	return new java.sql.Timestamp(today.getTime());

}
    
    public int getLastId() throws SQLException
    {
        Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select id from app.sales");
        
        resultSet.last();
        int last = resultSet.getInt("Id");
        return last;
    }
    
    public void subtractQuantity(List<Product> products) throws SQLException
    {
        Statement stmt = con.createStatement();
        
       // String sql = "ALTER APP.Products"
       // stmt.executeUpdate(sql);
    }
     /*public Product getProductFromDatabase(int inputNumber) throws SQLException
    {
        Statement stmt = con.createStatement();
        String SQL = "select * from APP.PRODUCTS where NUMBER=" + inputNumber;
            
        ResultSet rs = stmt.executeQuery(SQL);  
        
        int number = rs.getInt("Number");
        String name = rs.getString("Name");
        BigDecimal price = rs.getBigDecimal("Price").setScale(2);
        int quantity = rs.getInt("Quantity");
        String category = rs.getString("Category");
        String car = rs.getString("Car");
        String image = rs.getString("Image");

        Product p = new Product(number, name, price, quantity, category, car, image);
        return p;
    }*/
}
    
    /*
    public static void viewTable(Connection con, String dbName)
            throws SQLException {

        Statement stmt = null;
        String query
                = "select COF_NAME, SUP_ID, PRICE, "
                + "SALES, TOTAL "
                + "from " + dbName + ".COFFEES";

        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String coffeeName = rs.getString("COF_NAME");
                int supplierID = rs.getInt("SUP_ID");
                float price = rs.getFloat("PRICE");
                int sales = rs.getInt("SALES");
                int total = rs.getInt("TOTAL");
                System.out.println(coffeeName + "\t" + supplierID
                        + "\t" + price + "\t" + sales
                        + "\t" + total);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
    
    public static void alternateViewTable(Connection con)
            throws SQLException {

        Statement stmt = null;
        String query
                = "select COF_NAME, SUP_ID, PRICE, "
                + "SALES, TOTAL from COFFEES";

        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String coffeeName = rs.getString(1);
                int supplierID = rs.getInt(2);
                float price = rs.getFloat(3);
                int sales = rs.getInt(4);
                int total = rs.getInt(5);
                System.out.println(coffeeName + "\t" + supplierID
                        + "\t" + price + "\t" + sales
                        + "\t" + total);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
    
    public void modifyPrices(float percentage) throws SQLException {

        Statement stmt = null;
        try {
            stmt = con.createStatement();
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet uprs = stmt.executeQuery(
                    "SELECT * FROM " + dbName + ".COFFEES");

            while (uprs.next()) {
                float f = uprs.getFloat("PRICE");
                uprs.updateFloat("PRICE", f * percentage);
                uprs.updateRow();
            }

        } catch (SQLException e) {
            JDBCTutorialUtilities.printSQLException(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
    
    public static void printSQLException(SQLException ex) {

        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                if (ignoreSQLException(
                        ((SQLException) e).
                        getSQLState()) == false) {

                    e.printStackTrace(System.err);
                    System.err.println("SQLState: "
                            + ((SQLException) e).getSQLState());

                    System.err.println("Error Code: "
                            + ((SQLException) e).getErrorCode());

                    System.err.println("Message: " + e.getMessage());

                    Throwable t = ex.getCause();
                    while (t != null) {
                        System.out.println("Cause: " + t);
                        t = t.getCause();
                    }
                }
            }
        }
    }
    */
