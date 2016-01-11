/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autobot.customs;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Shakib
 */
public class FXMLCheckoutController implements Initializable, ControlledScreen {
    
    ScreensController myController; 
    List<Product> products = new ArrayList<Product>();
    ProductCart cart = new ProductCart(products, new BigDecimal(0));
    Customer customer = new Customer(cart);
    @FXML TextArea cartTextArea;
    @FXML TextArea personalTextArea;
    @FXML Button validateButton;
    @FXML Button returnToCartButton;
    @FXML Button confirmCheckoutButton;
   
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField ;
    @FXML private TextField addressField;
    @FXML private TextField cityField;
    @FXML private ComboBox stateField;
 
    @FXML private TextField zipCodeField;
    @FXML private TextField phoneNumberField;
    @FXML private ComboBox paymentMethodField;
    @FXML private TextField cardNumberField;
    @FXML private ComboBox expYearField; 
    @FXML private ComboBox expMonthField;
    @FXML private PasswordField secCodeField;
    
    databaseManagement db = new databaseManagement();
    
    @FXML
    private void returnToCart(ActionEvent event)
    {
        myController.setScreen("FXMLCartController");
    }

    @FXML
    public void setCartTextArea(ActionEvent event)
    {
        setTheCartTextArea();
    }
    
    @FXML
    private void setTheCartTextArea()
    {
        String text = "";
        cart = Context.getInstance().getCart();
        for (Product p : cart.getProductList())
        {
            text += p.getName() + " @ $"+p.getUnitPrice() + " x " + p.getQuantity() + " = $" + p.getTotal().setScale(2) + "\n";
        }
        text += "\nTotal:$" + cart.getTotal().setScale(2);
        cartTextArea.setText(text);
    }
    
     public void setPersonalTextArea()
    {       
        String text = "";
        
        text = "Name: " + customer.getName()
           + "\nAddress: " + customer.getAddress()
           + "\nPhone Number: " + customer.getPhoneNumber()
           + "\nCard Type: " + customer.getCardType()
           + "\nCard Number: " + customer.getCardNumber()
           + "\nCard Expiration: " + customer.getCardExp();
        
        personalTextArea.setText(text);
    }
    
    @FXML
    private void validate(ActionEvent event)
    {
        customer.setCart(Context.getInstance().getCart());
        validateCustomerInfo();
        setPersonalTextArea();
        
        Alert alert = new Alert(Alert.AlertType.WARNING);
        
        if(errors.equals(""))
        {
            setTheCartTextArea();
            alert.setTitle("Info");
            alert.setHeaderText("");
            alert.setContentText("Please review your order and information before clicking \"Confirm Checkout\".");
            alert.showAndWait();
            confirmCheckoutButton.setDisable(false);
            
        }
        else
        {
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("");
            alert.setContentText(errors);
            alert.showAndWait();
        }
    }
    
    String errors;
    @FXML
    private void validateCustomerInfo()
    {
        errors = "";
        if(firstNameField.getText().trim().equals(""))
            errors += "Please enter a first name.\n";                       
        else
            customer.setName(firstNameField.getText().trim()); 
            
                            
        if(lastNameField.getText().trim().equals(""))
            errors += "Please enter a last name. \n";            
        else
            customer.setName(customer.getName() + " " + lastNameField.getText().trim());            
        
        if(addressField.getText().trim().equals(""))
            errors += "Please enter an address. \n";            
        else
            customer.setAddress(addressField.getText().trim());
        
        if(cityField.getText().trim().equals(""))
            errors += "Please enter a city. \n";
        else
            customer.setAddress(customer.getAddress() + " " + cityField.getText().trim() + ", ");
            
        
        if(stateField.getSelectionModel().getSelectedIndex()==-1)
            errors += "Please select a state. \n";            
        else
            customer.setAddress(customer.getAddress() + stateField.getValue() + " ");
                    
        if(zipCodeField.getText().length()==0)
             errors += "Please enter a zip code. \n";
        else if(!(zipCodeField.getText().trim().length()==5 && zipCodeField.getText().trim().matches("[0-9][0-9][0-9][0-9][0-9]"))  )
            errors += "The zip code must be a 5 digit number. \n";       
        else
           customer.setAddress(customer.getAddress() + zipCodeField.getText().trim());

        if(phoneNumberField.getText() == null || phoneNumberField.getText().length()==0)
            errors += "Please enter a phone number. \n";
        else if(!(phoneNumberField.getText().trim().length() == 10 || phoneNumberField.getText().trim().length() == 11))
            errors += "Your phone number must be 10 or 11 digits long. \n";
        else if(!(phoneNumberField.getText().matches("([0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9])[0-9]?")))                                
            errors += "Your phone number must be 10 or 11 digits long. \n";
        else
            customer.setPhoneNumber(phoneNumberField.getText().trim());
        
        if(paymentMethodField.getSelectionModel().getSelectedIndex()==-1)
            errors += "Please select a payment method. \n"; 
        else
            customer.setCardType(paymentMethodField.getValue().toString());

        if(!cardNumberField.getText().trim().equals("") && paymentMethodField.getSelectionModel().getSelectedIndex() == -1)
            errors += "Please select a payment method before entering your card number. \n";
        else if(cardNumberField.getText().trim().equals(""))
            errors += "Please enter a card number. \n";
        else if(paymentMethodField.getSelectionModel().getSelectedIndex() ==0)
        {
            if(!(cardNumberField.getText().trim().matches("^4[0-9]{12}(?:[0-9]{3})?$")))
                errors += "Please enter a valid Visa number. \n";
            else
             customer.setCardNumber(cardNumberField.getText().trim());
        }
        else if(paymentMethodField.getSelectionModel().getSelectedIndex() ==1)
        {
            if(!cardNumberField.getText().trim().matches("5[1-5][0-9]{14}$"))
                errors += "Please enter a valid MasterCard number. \n";
            else
             customer.setCardNumber(cardNumberField.getText().trim());
        }
        else if(paymentMethodField.getSelectionModel().getSelectedIndex() ==2)
        {
            if(!cardNumberField.getText().trim().matches("6(?:011|5[0-9]{2})[0-9]{12}$"))
                errors += "Please enter a valid Discover number. \n";
            else
             customer.setCardNumber(cardNumberField.getText().trim());
        }
        else if(paymentMethodField.getSelectionModel().getSelectedIndex() ==3)
        {
            if(!cardNumberField.getText().trim().matches("3[47][0-9]{13}$"))
                errors += "Please enter a valid AmericanExpress number. \n";
            else
             customer.setCardNumber(cardNumberField.getText().trim());
        }
        
        
        if(expMonthField.getSelectionModel().getSelectedIndex()==-1)
            errors += "Please select a month. \n"; 
        else
            customer.setCardExp(expMonthField.getValue().toString() + "\\");
        
        if(expYearField.getSelectionModel().getSelectedIndex()==-1)
            errors += "Please select a year. \n"; 
        else
            customer.setCardExp(customer.getCardExp()+ expYearField.getValue().toString());
        
        if(secCodeField.getText().length()==0)
             errors += "Please enter a security code. \n";
        else if(!(secCodeField.getText().trim().length()==3 && secCodeField.getText().trim().matches("[0-9][0-9][0-9]"))  )
            errors += "The security code must be a 3 digit number. \n";       
        else
           customer.setSecCode(secCodeField.getText().trim());
    }
    
    
    @FXML
    private void confirmCheckout(ActionEvent event) throws SQLException
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Congratulations!");
        alert.setHeaderText("");
        alert.setContentText("Your order has been submitted! Press OK return to the main page.");
        alert.showAndWait();
        
        customer.setId(db.getLastId()+1);
        Context.getInstance().addCustomer(customer);
             
        db.insertSale(Context.getInstance().getCustomerList().get(0));
        AutobotCustoms ac = new AutobotCustoms();
        if(!alert.isShowing())
        {           
            Stage primaryStage = (Stage) cartTextArea.getScene().getWindow();
            ac.start(primaryStage);
        }
        
        
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
         myController = screenParent; 
    }
    
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cartTextArea.setEditable(false);
        personalTextArea.setEditable(false);
    }    
    
}
