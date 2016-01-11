/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autobot.customs;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Shakib
 */
public class FXMLProductSelectionController implements Initializable, ControlledScreen {
    
    ScreensController myController; 

    //Car Selection
    @FXML private RadioButton car1Radio;
    @FXML private RadioButton car2Radio;
    
    //Product Selection
    @FXML private Button returnButton;
    @FXML private Button searchButton;
    @FXML private Button loginButton;
    @FXML private Button viewCartButton;
    @FXML private Button showProductInfoButton;
    @FXML private Button addToCartButton;
    @FXML private TextField searchField;
    @FXML private AnchorPane productAnchor;
    @FXML private AnchorPane cat1Anchor;
    @FXML private ImageView imageView1;
    @FXML private ImageView imageView2;
    @FXML private Tab tab1, tab2, tab3, tab4, tab5;
    @FXML TextArea productTextArea;


    //Admin Login
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginConfirmButton;
    @FXML private Label loginErrorLabel;
    @FXML private AnchorPane loginAnchor;
    //Admin Options
    @FXML private AnchorPane productOptionsAnchor;
    @FXML private Button addProductButton, addConfirmButton;
    @FXML private Button editButton;
    @FXML private Button deleteProductButton;
    
    @FXML private TextField inputNameField, inputPriceField,
            inputQuantityField, inputImageField;
    @FXML private ComboBox inputCategoryField, inputCarField;

    
    databaseManagement db = new databaseManagement();
    

    
    @FXML
    private void returnToCarSelection(ActionEvent event)
    {
       myController.setScreen("FXMLCarSelectionController");
    }
    
    @FXML
    private void carSelection(ActionEvent event)
    {
        cat1Items.clear(); cat2Items.clear(); cat3Items.clear(); cat4Items.clear(); cat5Items.clear();
        if(car1Radio.isSelected())
        {            
            cat1Items.addAll(db.listProductNamesByCar("Spoilers", "BMW"));
            cat2Items.addAll(db.listProductNamesByCar("Headlights", "BMW"));
            cat3Items.addAll(db.listProductNamesByCar("Rims", "BMW"));
            cat4Items.addAll(db.listProductNamesByCar("Paint Color", "BMW"));
            cat5Items.addAll(db.listProductNamesByCar("Other", "BMW"));
            File file = new File("images/bmw.jpg");
            Image image = new Image(file.toURI().toString());
            imageView1.setImage(image);
        }
        else
        {
            
            cat1Items.addAll(db.listProductNamesByCar("Spoilers", "Audi"));
            cat2Items.addAll(db.listProductNamesByCar("Headlights", "Audi"));
            cat3Items.addAll(db.listProductNamesByCar("Rims", "Audi"));
            cat4Items.addAll(db.listProductNamesByCar("Paint Color", "Audi"));
            cat5Items.addAll(db.listProductNamesByCar("Other", "Audi"));
            File file = new File("images/audi.png");
            Image image = new Image(file.toURI().toString());
            imageView1.setImage(image);
        }
            
            
            
       
       cat1listView.itemsProperty().bind(listProperty1);
       cat2listView.itemsProperty().bind(listProperty2);
       cat3listView.itemsProperty().bind(listProperty3);
       cat4listView.itemsProperty().bind(listProperty4);
       cat5listView.itemsProperty().bind(listProperty4);
       listProperty1.set(FXCollections.observableArrayList(cat1Items));
       listProperty2.set(FXCollections.observableArrayList(cat2Items));
       listProperty3.set(FXCollections.observableArrayList(cat3Items));
       listProperty4.set(FXCollections.observableArrayList(cat4Items));
       listProperty5.set(FXCollections.observableArrayList(cat5Items));
            
    }
    
   
   
    @FXML
    private void searchProducts(ActionEvent event){}
    
    
    
    @FXML
    private void showProductInfo(ActionEvent event)
    {   
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText("");
        String selection = null;
        String imageURL = "";
        if(tab1.isSelected())
        {    
            selection = cat1listView.getSelectionModel().getSelectedItem();
            imageURL = db.getImageURL(selection);            
            File file = new File(imageURL);
            Image image = new Image(file.toURI().toString());
            imageView2.setImage(image);
              
            
        }else if(tab2.isSelected())
        {
            selection = cat2listView.getSelectionModel().getSelectedItem();
            imageURL = db.getImageURL(selection);            
            File file = new File(imageURL);
            Image image = new Image(file.toURI().toString());
            imageView2.setImage(image);
            
        }else if(tab3.isSelected())
        {
            selection = cat3listView.getSelectionModel().getSelectedItem();
            imageURL = db.getImageURL(selection);
            File file = new File(imageURL);
            Image image = new Image(file.toURI().toString());
            imageView2.setImage(image);
        }else if(tab4.isSelected())
        {
            selection = cat4listView.getSelectionModel().getSelectedItem();
            imageURL = db.getImageURL(selection);
            File file = new File(imageURL);
            Image image = new Image(file.toURI().toString());
            imageView2.setImage(image);
        }else if(tab5.isSelected())
        {
            selection = cat5listView.getSelectionModel().getSelectedItem();
            imageURL = db.getImageURL(selection);
            File file = new File(imageURL);
            Image image = new Image(file.toURI().toString());
            imageView2.setImage(image);
        }
        
        if(selection == null)
        {
           alert.setContentText("You must select an item before its info can be displayed.");
           alert.showAndWait();
        }
        else
            System.out.println(selection);
        
        Product p = db.getProductForCart(selection);
            productTextArea.setText("Product Number: " + p.getNumber()
                                   +"\nProduct Name: " + p.getName()
                                   +"\nPrice: $" + p.getUnitPrice());  
         
    }
    
    
    List<Product> products = new ArrayList<Product>();
    ProductCart cart = new ProductCart(products, new BigDecimal(0));
    Customer customer = new Customer(cart);

    public boolean contains(List<Product> products, String name) {
    for (Product p : products) {
        if (p.getName().equals(name)) {
            return true;
        }
    }
    return false;
}
    
    @FXML
    private void addToCart(ActionEvent event)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText("");
         String selection = null;
        if(tab1.isSelected())
        {    
            selection = cat1listView.getSelectionModel().getSelectedItem();
            Product p = db.getProductForCart(selection);
            if(contains(cart.getProductList(), p.getName()))
            {
                alert.setAlertType(AlertType.WARNING);
                alert.setContentText("You have already added this item to your cart");
                alert.showAndWait();
            }
            else
            {
                cart.addToCart(p); 
                alert.setContentText("You have added this item to your cart.");
                alert.showAndWait();
            }           
        }else if(tab2.isSelected())
        {
            selection = cat2listView.getSelectionModel().getSelectedItem();
            Product p = db.getProductForCart(selection);
            if(contains(cart.getProductList(), p.getName()))
            {
                alert.setAlertType(AlertType.WARNING);
                alert.setContentText("You have already added this item to your cart");
                alert.showAndWait();
            }
            else
            {
                cart.addToCart(p); 
                alert.setContentText("You have added this item to your cart.");
                alert.showAndWait();
            } 
        }else if(tab3.isSelected())
        {
            selection = cat3listView.getSelectionModel().getSelectedItem();
           Product p = db.getProductForCart(selection);
            if(contains(cart.getProductList(), p.getName()))
            {
                alert.setAlertType(AlertType.WARNING);
                alert.setContentText("You have already added this item to your cart");
                alert.showAndWait();
            }
            else
            {
                cart.addToCart(p); 
                alert.setContentText("You have added this item to your cart.");
                alert.showAndWait();
            } 

        }else if(tab4.isSelected())
        {
            selection = cat4listView.getSelectionModel().getSelectedItem();
            Product p = db.getProductForCart(selection);
            if(contains(cart.getProductList(), p.getName()))
            {
                alert.setAlertType(AlertType.WARNING);
                alert.setContentText("You have already added this item to your cart");
                alert.showAndWait();
            }
            else
            {
                cart.addToCart(p); 
                alert.setContentText("You have added this item to your cart.");
                alert.showAndWait();
            } 

        }else if(tab5.isSelected())
        {
            selection = cat5listView.getSelectionModel().getSelectedItem();
           Product p = db.getProductForCart(selection);
            if(contains(cart.getProductList(), p.getName()))
            {
                alert.setAlertType(AlertType.WARNING);
                alert.setContentText("You have already added this item to your cart");
                alert.showAndWait();
            }
            else
            {
                cart.addToCart(p); 
                alert.setContentText("You have added this item to your cart.");
                alert.showAndWait();
            } 
        }
        
        if(selection == null)
        {
            alert.setContentText("You must select an item before adding it to your cart.");
            alert.showAndWait();
        } 
    }
    public ProductCart getCart()
    {
        return cart;
    }
    
    //Accessing the administrator login
    @FXML
    private void openLoginForm(ActionEvent event) throws IOException
    {       
        Parent root = FXMLLoader.load(getClass().getResource("FXMLLoginScreen.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 300, 400));
        stage.setResizable(false);
        
        //make the product selection page inaccesible while login is open
        Stage productStage = (Stage) productAnchor.getScene().getWindow();
        stage.initOwner(productStage);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();
    }
    
    //Show admin options
    @FXML
    private void showAdminOptions(ActionEvent event) throws IOException
    {
        
        if(usernameField.getText().equals("autobot") && passwordField.getText().equals("customs"))
        {      
            loginErrorLabel.setVisible(false);
            Stage loginStage = (Stage) loginAnchor.getScene().getWindow();
            loginStage.close();
            
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDatabaseOptions.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 800, 400));
            stage.setResizable(false);            
            stage.show();
        }
        else
        {
            loginErrorLabel.setVisible(true);
        }                    
    }
    
    @FXML
    private void loadInventory(ActionEvent event) throws SQLException
    {
        setDatabaseTable();
    }
    
    @FXML TableView productTable;
    @FXML TableColumn<Product,Integer> numberCol;
    @FXML TableColumn<Product,String> nameCol;
    @FXML TableColumn<Product,BigDecimal> priceCol;
    @FXML TableColumn<Product,Integer> quantityCol;
    @FXML TableColumn<Product,String> categoryCol;
    @FXML TableColumn<Product,String> carCol;
    @FXML TableColumn<Product,String> imageCol;
    
    @FXML 
    private void setDatabaseTable() throws SQLException
    {
         numberCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("number"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<Product, BigDecimal>("unitPrice"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantity"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<Product, String>("category"));
        carCol.setCellValueFactory(new PropertyValueFactory<Product, String>("car"));
        imageCol.setCellValueFactory(new PropertyValueFactory<Product, String>("image"));
        productTable.getItems().setAll(getProduct());
        
    }
    
    @FXML
    private void loadSales(ActionEvent event) throws SQLException, IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLSales.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 800, 400));
        stage.setResizable(false);            
        stage.show();
    }
    
    @FXML
    private void loadSales1(ActionEvent event) throws SQLException, IOException
    {
        setSalesTable();
    }
    
    @FXML TableView salesTable;
    @FXML TableColumn<Sale,Integer> idCol;
    @FXML TableColumn<Sale,String> custNameCol;
    @FXML TableColumn<Sale,String> addressCol;
    @FXML TableColumn<Sale, String> phoneCol;
    @FXML TableColumn<Sale,String> cardTypeCol;
    @FXML TableColumn<Sale,String> cardNumberCol;
    @FXML TableColumn<Sale,String> cardExpCol;
    @FXML TableColumn<Sale,String> secCodeCol;
    @FXML TableColumn<Sale,String> cartDetailsCol;
    @FXML TableColumn<Sale,BigDecimal> cartTotalCol;
    @FXML TableColumn<Sale,java.sql.Timestamp> timeCol;
    
    @FXML 
    private void setSalesTable() throws SQLException
    {
        idCol.setCellValueFactory(new PropertyValueFactory<Sale, Integer>("id"));
        custNameCol.setCellValueFactory(new PropertyValueFactory<Sale, String>("customerName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<Sale, String>("customerAddress"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<Sale, String>("customerPhone"));
        cardTypeCol.setCellValueFactory(new PropertyValueFactory<Sale, String>("customerCardType"));
        cardNumberCol.setCellValueFactory(new PropertyValueFactory<Sale, String>("customerCardNumber"));
        cardExpCol.setCellValueFactory(new PropertyValueFactory<Sale, String>("customerCardExp"));
        secCodeCol.setCellValueFactory(new PropertyValueFactory<Sale, String>("secCode"));
        cartDetailsCol.setCellValueFactory(new PropertyValueFactory<Sale, String>("cartDetails"));
        cartTotalCol.setCellValueFactory(new PropertyValueFactory<Sale, BigDecimal>("cartTotal"));
        timeCol.setCellValueFactory(new PropertyValueFactory<Sale, java.sql.Timestamp>("time"));
        salesTable.getItems().setAll(getSales());
        
    }
    
    public ObservableList<Product> getProduct()
    {
        ObservableList<Product> products = FXCollections.observableArrayList();
        for(Product p : db.getAllProducts())
            products.add(p);
        return products;
    }
    
    public ObservableList<Sale> getSales()
    {
        ObservableList<Sale> sales = FXCollections.observableArrayList();
        for(Sale s : db.getAllSales())
            sales.add(s);
        return sales;
    }
    
    @FXML
    private void handleProductOptions(ActionEvent event) throws IOException, SQLException
    {
        if (event.getSource()==addProductButton)
        {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLAddProduct.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 300, 400));
            stage.setResizable(false);

            //make the product selection page inaccesible while login is open
            Stage productStage = (Stage) productOptionsAnchor.getScene().getWindow();
            stage.initOwner(productStage);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.show();
        }
        else if(event.getSource()==deleteProductButton)
        {
            int selectedItem = productTable.getSelectionModel().getSelectedIndex();
            if(selectedItem == -1)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Info");
                alert.setHeaderText("");
                alert.setContentText("Please select an item to remove it.");
                alert.showAndWait();
            }
            else
            {
                Context.getInstance().setDatabase(db.getAllProducts());
                Product p = Context.getInstance().getDatabase().get(selectedItem);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Warning");
                alert.setHeaderText("");
                alert.setContentText("Are you sure you want to remove this item?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK)
                    db.removeProductFromDatabase(p);
            }
        }    
    }
    
    @FXML
    private void addProduct(ActionEvent event) throws SQLException
    {
        validateProductInfo();
        if(errors.equals(""))
        {
            int number = db.getAllProducts().size()+1;
            db.addProductToDatabase(new Product(number, name, price, quantity, category, car, image));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("");
            alert.setContentText("You have successfully added this item to the inventory");
            alert.showAndWait();
            if(alert.isShowing()==false)
                addConfirmButton.getScene().getWindow().hide();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("");
            alert.setContentText(errors);
            alert.showAndWait();
                }
    }
  
    String errors;
    String name, category, car, image;
    BigDecimal price; 
    int quantity; 
    @FXML
    private void validateProductInfo()
    {
        errors = "";
        if(inputNameField.getText().trim().equals(""))
            errors += "Please enter a product name.\n";                       
        else
            name = inputNameField.getText().trim();             
                            
        if(inputPriceField.getText().trim().equals(""))
            errors += "Please enter a price. \n";  
        else if(!inputPriceField.getText().trim().matches("^(\\$?\\d{1,3}(?:,?\\d{3})*(?:\\.\\d{2})?|\\.\\d{2})?$"))
            errors += "You must enter a valid price. \n";
        else
            price = new BigDecimal(inputPriceField.getText().trim()).setScale(2);
        
        if(inputQuantityField.getText().trim().equals(""))
            errors += "Please enter a quantity. \n";
        else if(!inputQuantityField.getText().trim().matches("[0-9]+"))
            errors += "You must enter an integer for the quantity. \n";
        else
            quantity = Integer.parseInt(inputQuantityField.getText().trim());
        
        if(inputCategoryField.getSelectionModel().getSelectedIndex()==-1)
            errors += "Please select a category. \n"; 
        else
            category = inputCategoryField.getValue().toString();
        
        if(inputCarField.getSelectionModel().getSelectedIndex()==-1)
            errors += "Please select a car. \n"; 
        else
            car = inputCarField.getValue().toString();
        
        if(inputImageField.getText().trim().equals(""))
            errors += "Please enter an image URL. \n";
        else
            image = "images/"+inputImageField.getText().trim();
        
    }
    
    
    
    @FXML
    private void openCart(ActionEvent event) throws IOException
    {
        Context.getInstance().setCart(cart);
        myController.setScreen("FXMLCartController");
    }
    

    @Override
    public void setScreenParent(ScreensController screenParent) {
         myController = screenParent; 
    }
    

    public void setCustomer(Customer tCustomer) {
        customer = tCustomer;
    }
    
    

    @FXML private ListView<String> cat1listView = new ListView<String>();
    @FXML private ListView<String> cat2listView = new ListView<String>();
    @FXML private ListView<String> cat3listView = new ListView<String>();
    @FXML private ListView<String> cat4listView = new ListView<String>();
    @FXML private ListView<String> cat5listView = new ListView<String>();
    protected List<String> cat1Items = new ArrayList<>();
    protected List<String> cat2Items = new ArrayList<>();
    protected List<String> cat3Items = new ArrayList<>();
    protected List<String> cat4Items = new ArrayList<>();
    protected List<String> cat5Items = new ArrayList<>();
    protected ListProperty<String> listProperty1 = new SimpleListProperty<>();
    protected ListProperty<String> listProperty2 = new SimpleListProperty<>();
    protected ListProperty<String> listProperty3 = new SimpleListProperty<>();
    protected ListProperty<String> listProperty4 = new SimpleListProperty<>();
    protected ListProperty<String> listProperty5 = new SimpleListProperty<>();
    
    
    private void setProducts()
    {
        cat1Items.addAll(db.listProductNamesByCar("Spoilers", "BMW"));
        cat2Items.addAll(db.listProductNamesByCar("Headlights", "BMW"));
        cat3Items.addAll(db.listProductNamesByCar("Rims", "BMW"));
        cat4Items.addAll(db.listProductNamesByCar("Paint Color", "BMW"));
        cat5Items.addAll(db.listProductNamesByCar("Other", "BMW"));
    }
    
   
    @FXML private ImageView logoImageView;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File file = new File("images/bmw.jpg");
        Image image = new Image(file.toURI().toString());
        imageView1.setImage(image);
        
        File file1 = new File("images/AutoBotsLogo.png");
        Image image1 = new Image(file1.toURI().toString());
        logoImageView.setImage(image1);
        
        setProducts();
       cat1listView.itemsProperty().bind(listProperty1);
       cat2listView.itemsProperty().bind(listProperty2);
       cat3listView.itemsProperty().bind(listProperty3);
       cat4listView.itemsProperty().bind(listProperty4);
       cat5listView.itemsProperty().bind(listProperty4);
       listProperty1.set(FXCollections.observableArrayList(cat1Items));
       listProperty2.set(FXCollections.observableArrayList(cat2Items));
       listProperty3.set(FXCollections.observableArrayList(cat3Items));
       listProperty4.set(FXCollections.observableArrayList(cat4Items));
       listProperty5.set(FXCollections.observableArrayList(cat5Items));
       
       
    }    
    
}
