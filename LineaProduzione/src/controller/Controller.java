/**
 * Sample Skeleton for 'InterfacciaGrafica.fxml' Controller Class
 */

package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import model.Linea;
import model.Model;
import model.SimResult;
import model.WorkStation;

public class Controller {
	
	 private Model model;
	 private Linea linea;
	

    @FXML // fx:id="hA"
    private HBox hA; // Value injected by FXMLLoader
    

    @FXML // fx:id="hB"
    private HBox hB; // Value injected by FXMLLoader
    

    @FXML // fx:id="hC"
    private HBox hC; // Value injected by FXMLLoader
    

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="creaWS"
    private Button creaWS; // Value injected by FXMLLoader

    @FXML // fx:id="nameWS"
    private TextField nameWS; // Value injected by FXMLLoader

    @FXML // fx:id="t0"
    private TextField t0; // Value injected by FXMLLoader

    @FXML // fx:id="c0"
    private TextField c0; // Value injected by FXMLLoader

    @FXML // fx:id="m"
    private TextField m; // Value injected by FXMLLoader

    @FXML // fx:id="check1"
    private CheckBox check1; // Value injected by FXMLLoader

    @FXML // fx:id="h1"
    private HBox h1; // Value injected by FXMLLoader

    @FXML // fx:id="minMf"
    private TextField minMf; // Value injected by FXMLLoader

    @FXML // fx:id="maxMf"
    private TextField maxMf; // Value injected by FXMLLoader

    @FXML // fx:id="h2"
    private HBox h2; // Value injected by FXMLLoader

    @FXML // fx:id="minMr"
    private TextField minMr; // Value injected by FXMLLoader

    @FXML // fx:id="maxMr"
    private TextField maxMr; // Value injected by FXMLLoader

    @FXML // fx:id="h3"
    private HBox h3; // Value injected by FXMLLoader

    @FXML // fx:id="minCf"
    private TextArea minCf; // Value injected by FXMLLoader

    @FXML // fx:id="MaxCf"
    private TextField MaxCf; // Value injected by FXMLLoader

    @FXML // fx:id="h4"
    private HBox h4; // Value injected by FXMLLoader

    @FXML // fx:id="minCr"
    private TextArea minCr; // Value injected by FXMLLoader

    @FXML // fx:id="maxCr"
    private TextField maxCr; // Value injected by FXMLLoader

    @FXML // fx:id="check2"
    private CheckBox check2; // Value injected by FXMLLoader

    @FXML // fx:id="h5"
    private HBox h5; // Value injected by FXMLLoader

    @FXML // fx:id="minNs"
    private TextField minNs; // Value injected by FXMLLoader

    @FXML // fx:id="maxNs"
    private TextField maxNs; // Value injected by FXMLLoader

    @FXML // fx:id="h6"
    private HBox h6; // Value injected by FXMLLoader

    @FXML // fx:id="minTs"
    private TextField minTs; // Value injected by FXMLLoader

    @FXML // fx:id="maxTs"
    private TextField maxTs; // Value injected by FXMLLoader

    @FXML // fx:id="h7"
    private HBox h7; // Value injected by FXMLLoader

    @FXML // fx:id="minCs"
    private TextField minCs; // Value injected by FXMLLoader

    @FXML // fx:id="maxCs"
    private TextField maxCs; // Value injected by FXMLLoader

    @FXML // fx:id="check3"
    private CheckBox check3; // Value injected by FXMLLoader

    @FXML // fx:id="h8"
    private HBox h8; // Value injected by FXMLLoader

    @FXML // fx:id="minP"
    private TextField minP; // Value injected by FXMLLoader

    @FXML // fx:id="maxP"
    private TextField maxP; // Value injected by FXMLLoader

    @FXML // fx:id="setLinea"
    private Button setLinea; // Value injected by FXMLLoader

    @FXML // fx:id="wss"
    private ChoiceBox<WorkStation> wss; // Value injected by FXMLLoader

    @FXML // fx:id="aggiungiWS"
    private Button aggiungiWS; // Value injected by FXMLLoader

    @FXML // fx:id="creaLinea"
    private Button creaLinea; // Value injected by FXMLLoader

    @FXML // fx:id="simulazione"
    private Button simulazione; // Value injected by FXMLLoader

    @FXML // fx:id="ottimizza"
    private Button ottimizza; // Value injected by FXMLLoader

    @FXML // fx:id="result"
    private TextArea txtResult; // Value injected by FXMLLoader
    
    @FXML // fx:id="comboProdotto"
    private ComboBox<String> comboProdotto; // Value injected by FXMLLoader

 

    @FXML
    void aggiungiWs(ActionEvent event) {
    	
    	linea.addWS(wss.getSelectionModel().getSelectedItem());
    }
    

    @FXML
    void creaLinea(ActionEvent event) {
    	
    	model.addLinea(linea);
    	hA.setDisable(false);
    	hB.setDisable(false);
    	hC.setDisable(false);
    	txtResult.setDisable(false);
    	

    }

    @FXML
    void creaWS(ActionEvent event) {
    	String nome=nameWS.getText();
    	double t=Double.parseDouble(t0.getText());//ipotizzo valori in secondi
    	double c=Double.parseDouble(c0.getText());
    	int macchine=Integer.parseInt(m.getText());
    	
    	WorkStation ws=new WorkStation(nome,t,c, macchine);
    	
    	if(check1.isSelected())
    	{
    		ws.setGuasti(true);
    		ws.setMfMAX(Integer.parseInt(maxMf.getText()));
    		ws.setMfMIN(Integer.parseInt(minMf.getText()));
    		ws.setMrMAX(Integer.parseInt(maxMr.getText()));
    		ws.setMrMIN(Integer.parseInt(maxMr.getText()));
    		
    		ws.setCfMAX(Double.parseDouble(MaxCf.getText()));
    		ws.setCfMIN(Double.parseDouble(minCf.getText()));
    		ws.setCrMAX(Double.parseDouble(maxCr.getText()));
    		ws.setCrMIN(Double.parseDouble(minCr.getText()));
    	}
    	
    	if(check2.isSelected())
    	{
    		ws.setSetup(true);
    		ws.setNsMAX(Integer.parseInt(maxNs.getText()));
    		ws.setNsMIN(Integer.parseInt(minNs.getText()));
    		ws.setTsMAX(Integer.parseInt(maxTs.getText()));
    		ws.setTsMIN(Integer.parseInt(minTs.getText()));
    		
    		ws.setCsMAX(Double.parseDouble(maxCs.getText()));
    		ws.setCsMIN(Double.parseDouble(minCs.getText()));
    	}
    	
    	if(check3.isSelected())
    	{
    		ws.setRilavorazioni(true);
    		
    		ws.setpMAX(Double.parseDouble(maxP.getText()));
    		ws.setpMIN(Double.parseDouble(minP.getText()));
    	
    	}
    	
    	wss.setItems(FXCollections.observableArrayList(model.addWS(ws)));

    }

   

    @FXML
    void ottimizza(ActionEvent event) {

    }

    
    @FXML
    void simulazione(ActionEvent event) {
    	String prodotto= comboProdotto.getValue();
    	
    	SimResult result= model.simula(prodotto, linea);
    	
    	if(result == null)
    		txtResult.setText("Tutto ok");
    	else {
    		txtResult.setText("Utilizzazione maggiore di 1 alla ws"+result.getWsUOver1()+" in data "+result.getDataUOver1());
    	}

    }
    
    
    @FXML
    void setLinea(ActionEvent event) {
    	linea=new Linea();
    	
    	hA.setDisable(true);
    	hB.setDisable(true);
    	hC.setDisable(true);
    	txtResult.setDisable(true);
    	
    	

    }

   
    
    
    @FXML
    void doCheck1(ActionEvent event) {
    	if(check1.isSelected())
    	{
    		h1.setDisable(false);
    		h2.setDisable(false);
    		h3.setDisable(false);
    		h4.setDisable(false);
    	}
    	else
    	{
    		h1.setDisable(true);
    		h2.setDisable(true);
    		h3.setDisable(true);
    		h4.setDisable(true);
    	}

    }

    @FXML
    void doCheck2(ActionEvent event) {
    	if(check2.isSelected())
    	{
    		h5.setDisable(false);
    		h6.setDisable(false);
    		h7.setDisable(false);
    		
    	}
    	else
    	{
    		h5.setDisable(true);
    		h6.setDisable(true);
    		h7.setDisable(true);
    		
    	}


    }

    @FXML
    void doCheck3(ActionEvent event) {
    	if(check3.isSelected())
    		h8.setDisable(false);
    	else
    		h8.setDisable(true);

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert creaWS != null : "fx:id=\"creaWS\" was not injected: check your FXML file 'InterfacciaGrafica.fxml'.";
        assert nameWS != null : "fx:id=\"nameWS\" was not injected: check your FXML file 'InterfacciaGrafica.fxml'.";
        assert t0 != null : "fx:id=\"t0\" was not injected: check your FXML file 'InterfacciaGrafica.fxml'.";
        assert c0 != null : "fx:id=\"c0\" was not injected: check your FXML file 'InterfacciaGrafica.fxml'.";
        assert m != null : "fx:id=\"m\" was not injected: check your FXML file 'InterfacciaGrafica.fxml'.";
        assert check1 != null : "fx:id=\"check1\" was not injected: check your FXML file 'InterfacciaGrafica.fxml'.";
        assert h1 != null : "fx:id=\"h1\" was not injected: check your FXML file 'InterfacciaGrafica.fxml'.";
        assert minMf != null : "fx:id=\"minMf\" was not injected: check your FXML file 'InterfacciaGrafica.fxml'.";
        assert maxMf != null : "fx:id=\"maxMf\" was not injected: check your FXML file 'InterfacciaGrafica.fxml'.";
        assert h2 != null : "fx:id=\"h2\" was not injected: check your FXML file 'InterfacciaGrafica.fxml'.";
        assert minMr != null : "fx:id=\"minMr\" was not injected: check your FXML file 'InterfacciaGrafica.fxml'.";
        assert maxMr != null : "fx:id=\"maxMr\" was not injected: check your FXML file 'InterfacciaGrafica.fxml'.";
        assert h3 != null : "fx:id=\"h3\" was not injected: check your FXML file 'InterfacciaGrafica.fxml'.";
        assert minCf != null : "fx:id=\"minCf\" was not injected: check your FXML file 'InterfacciaGrafica.fxml'.";
        assert MaxCf != null : "fx:id=\"MaxCf\" was not injected: check your FXML file 'InterfacciaGrafica.fxml'.";
        assert h4 != null : "fx:id=\"h4\" was not injected: check your FXML file 'InterfacciaGrafica.fxml'.";
        assert minCr != null : "fx:id=\"minCr\" was not injected: check your FXML file 'InterfacciaGrafica.fxml'.";
        assert maxCr != null : "fx:id=\"maxCr\" was not injected: check your FXML file 'InterfacciaGrafica.fxml'.";
        assert check2 != null : "fx:id=\"check2\" was not injected: check your FXML file 'InterfacciaGrafica.fxml'.";
        assert h5 != null : "fx:id=\"h5\" was not injected: check your FXML file 'InterfacciaGrafica.fxml'.";
        assert minNs != null : "fx:id=\"minNs\" was not injected: check your FXML file 'InterfacciaGrafica.fxml'.";
        assert maxNs != null : "fx:id=\"maxNs\" was not injected: check your FXML file 'InterfacciaGrafica.fxml'.";
        assert h6 != null : "fx:id=\"h6\" was not injected: check your FXML file 'InterfacciaGrafica.fxml'.";
        assert minTs != null : "fx:id=\"minTs\" was not injected: check your FXML file 'InterfacciaGrafica.fxml'.";
        assert maxTs != null : "fx:id=\"maxTs\" was not injected: check your FXML file 'InterfacciaGrafica.fxml'.";
        assert h7 != null : "fx:id=\"h7\" was not injected: check your FXML file 'InterfacciaGrafica.fxml'.";
        assert minCs != null : "fx:id=\"minCs\" was not injected: check your FXML file 'InterfacciaGrafica.fxml'.";
        assert maxCs != null : "fx:id=\"maxCs\" was not injected: check your FXML file 'InterfacciaGrafica.fxml'.";
        assert check3 != null : "fx:id=\"check3\" was not injected: check your FXML file 'InterfacciaGrafica.fxml'.";
        assert h8 != null : "fx:id=\"h8\" was not injected: check your FXML file 'InterfacciaGrafica.fxml'.";
        assert minP != null : "fx:id=\"minP\" was not injected: check your FXML file 'InterfacciaGrafica.fxml'.";
        assert maxP != null : "fx:id=\"maxP\" was not injected: check your FXML file 'InterfacciaGrafica.fxml'.";
        assert setLinea != null : "fx:id=\"setLinea\" was not injected: check your FXML file 'InterfacciaGrafica.fxml'.";
        assert wss != null : "fx:id=\"wss\" was not injected: check your FXML file 'InterfacciaGrafica.fxml'.";
        assert aggiungiWS != null : "fx:id=\"aggiungiWS\" was not injected: check your FXML file 'InterfacciaGrafica.fxml'.";
        assert creaLinea != null : "fx:id=\"creaLinea\" was not injected: check your FXML file 'InterfacciaGrafica.fxml'.";
        assert simulazione != null : "fx:id=\"simulazione\" was not injected: check your FXML file 'InterfacciaGrafica.fxml'.";
        assert ottimizza != null : "fx:id=\"ottimizza\" was not injected: check your FXML file 'InterfacciaGrafica.fxml'.";
        assert txtResult != null : "fx:id=\"result\" was not injected: check your FXML file 'InterfacciaGrafica.fxml'.";
        assert comboProdotto != null : "fx:id=\"comboProdotto\" was not injected: check your FXML file 'InterfacciaGrafica.fxml'.";

    }
    
   

	public void setModel(Model model) {
		this.model=model;
		comboProdotto.getItems().addAll(model.getProdotti());
		
		
	}
}
