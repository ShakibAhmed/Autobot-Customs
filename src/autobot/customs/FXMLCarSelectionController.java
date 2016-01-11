/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autobot.customs;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Shakib
 */
public class FXMLCarSelectionController implements Initializable, ControlledScreen
{    
    ScreensController myController; 
    @FXML Button car1btn;
    
        //Selecting a car from the initial screen, and returning from product page to initial screen

    public int carSelected;
    public int getCarSelected()
    {
        return carSelected;
    }
    @FXML
    private void selectCar(ActionEvent event) throws IOException
    { 
            myController.setScreen(AutobotCustoms.PRODUCT_SELECTION);   

    }
    
    @FXML 
   private void goToMain(ActionEvent event){ 
     myController.setScreen(AutobotCustoms.CAR_SELECTION); 
   } 
   
    @FXML
    private void changeScreen(ActionEvent event)
    {
       myController.setScreen("FXMLProductSelectionController");
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
         myController = screenParent; 
    }
    
     /**
     * Initializes the controller class.
     */
    
  
    @FXML private ImageView logoImageView;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File file = new File("images/AutoBotsLogo.png");
        Image image = new Image(file.toURI().toString());
        logoImageView.setImage(image);
    }    
    
}
