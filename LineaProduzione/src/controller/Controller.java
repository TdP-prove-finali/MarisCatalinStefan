package controller;

import java.net.URL;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;

public class Controller {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="radio1"
    private RadioButton radio1; // Value injected by FXMLLoader

    @FXML // fx:id="h1"
    private HBox h1; // Value injected by FXMLLoader

    @FXML // fx:id="radio2"
    private RadioButton radio2; // Value injected by FXMLLoader

    @FXML // fx:id="radio3"
    private RadioButton radio3; // Value injected by FXMLLoader
    
    

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert radio1 != null : "fx:id=\"radio1\" was not injected: check your FXML file 'InterfacciaGrafica.fxml'.";
        assert h1 != null : "fx:id=\"h1\" was not injected: check your FXML file 'InterfacciaGrafica.fxml'.";
        assert radio2 != null : "fx:id=\"radio2\" was not injected: check your FXML file 'InterfacciaGrafica.fxml'.";
        assert radio3 != null : "fx:id=\"radio3\" was not injected: check your FXML file 'InterfacciaGrafica.fxml'.";

    }
}

