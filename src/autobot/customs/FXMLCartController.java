/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autobot.customs;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Shakib
 */
public class FXMLCartController implements Initializable, ControlledScreen {
    
    ScreensController myController; 
    List<Product> products = new ArrayList<Product>();
    ProductCart cart = new ProductCart(products, new BigDecimal(0));
    @FXML Button button1;
    @FXML Button updateCartButton;
    @FXML Button incButton, decButton, removeButton;
    @FXML Button checkoutButton;
    @FXML Label totalLabel;
    @FXML TableView<Product> cartTable;
    @FXML TableColumn<Product,Integer> numberCol;
    @FXML TableColumn<Product,String> nameCol;
    @FXML TableColumn<Product,BigDecimal> priceCol;
    @FXML TableColumn<Product,Integer> quantityCol;
    @FXML TableColumn<Product,BigDecimal> totalPriceCol;
    
    
    public void setCart(ProductCart inputCart)
    {
        cart = inputCart;
    }

    @FXML
    public void updateCart(ActionEvent event)
    {
        updateTheCart();           
    }
    
    public void updateTheCart()
    {
        cart = Context.getInstance().getCart();
        numberCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("number"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<Product, BigDecimal>("unitPrice"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantity"));
        totalPriceCol.setCellValueFactory(new PropertyValueFactory<Product, BigDecimal>("total"));
        cartTable.getItems().setAll(getProduct());
        cart.setTotal();
        totalLabel.setText("Total: $" + cart.getTotal().setScale(2));
        
        if(cart.getCart().isEmpty())
            checkoutButton.setDisable(true);
        else
            checkoutButton.setDisable(false);
            
    }
    
   
    
    @FXML
    public void handleCartOptions(ActionEvent event)
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText("");
        int selectedItem = cartTable.getSelectionModel().getSelectedIndex();
        //the product selected
        
        if(event.getSource()==incButton)
        {
            if(selectedItem == -1)
            {
                alert.setContentText("Please select an item.");
                alert.showAndWait();
            }
            else
            {
                Product p = cart.getProductList().get(selectedItem);
                cart.increaseQuantity(selectedItem);
                cart.setTotal();
                p.setTotal(p.getQuantity(), p.getUnitPrice());
                Context.getInstance().setCart(cart);
                updateTheCart();
            }
        }
        else if(event.getSource()==decButton)
        {
            if(selectedItem == -1)
            {
                alert.setContentText("Please select an item.");
                alert.showAndWait();
            }
            else if(cart.getCart().get(selectedItem).getQuantity() <= 1)
            {                
                alert.setContentText("You can't decrease the quantity anymore.");
                alert.showAndWait();
            }
            
            else
            {
                Product p = cart.getProductList().get(selectedItem);
                cart.decreaseQuantity(selectedItem);
                cart.setTotal();
                p.setTotal(p.getQuantity(), p.getUnitPrice());
                Context.getInstance().setCart(cart);
                updateTheCart();
            }
        }
        else if(event.getSource()==removeButton)
        {
            if(selectedItem == -1)
            {
                alert.setContentText("Please select an item to remove it.");
                alert.showAndWait();
            }
            else
            {
                Product p = cart.getProductList().get(selectedItem);
                cart.removeProduct(selectedItem);
                cart.setTotal();
                p.setTotal(p.getQuantity(), p.getUnitPrice());
                Context.getInstance().setCart(cart);
                updateTheCart();
            }
        }
    }
    
    
    
    public ObservableList<Product> getProduct()
    {
        ObservableList<Product> products = FXCollections.observableArrayList();
        for(Product p : cart.getCart())
            products.add(p);
        return products;
    }
    
    @FXML
    private void returnToProductSelection(ActionEvent event)
    {
        myController.setScreen("FXMLProductSelectionController");
    }
    
    @FXML
    private void checkout(ActionEvent event){
       
      Context.getInstance().setCart(cart);
       myController.setScreen("FXMLCheckoutController");
    }
    

    @Override
    public void setScreenParent(ScreensController screenParent) {
         myController = screenParent; 
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       cartTable.setPlaceholder(new Label("No items in cart."));
         
    }    
    
}
