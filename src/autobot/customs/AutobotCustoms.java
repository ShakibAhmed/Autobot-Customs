package autobot.customs;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AutobotCustoms extends Application {
    
     public static final String CAR_SELECTION = "FXMLCarSelectionController"; 
     public static final String CAR_SELECTION_FXML = "FXMLCarSelection.fxml"; 
     public static final String PRODUCT_SELECTION = "FXMLProductSelectionController"; 
     public static final String PRODUCT_SELECTION_FXML = "FXMLProductSelection.fxml"; 
     public static final String CART = "FXMLCartController";  
     public static final String CART_FXML = "FXMLCart.fxml";
     public static final String CHECKOUT = "FXMLCheckoutController";
     public static final String CHECKOUT_FXML = "FXMLCheckout.fxml";
     
     databaseManagement db = new databaseManagement();

     @Override 
     public void start(Stage primaryStage) { 
       ScreensController mainContainer = new ScreensController(); 
       mainContainer.loadScreen(AutobotCustoms.CAR_SELECTION, AutobotCustoms.CAR_SELECTION_FXML); 
       mainContainer.loadScreen(AutobotCustoms.PRODUCT_SELECTION, AutobotCustoms.PRODUCT_SELECTION_FXML); 
       mainContainer.loadScreen(AutobotCustoms.CART, AutobotCustoms.CART_FXML);
       mainContainer.loadScreen(AutobotCustoms.CHECKOUT, AutobotCustoms.CHECKOUT_FXML);

       mainContainer.setScreen(AutobotCustoms.CAR_SELECTION); 

       Group root = new Group(); 
       root.getChildren().addAll(mainContainer); 
       Scene scene = new Scene(root, 900, 600, Color.rgb(238,238,209)); 
       primaryStage.setScene(scene); 
       primaryStage.show(); 
     }
     

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
