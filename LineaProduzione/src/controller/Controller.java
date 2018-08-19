/**
 * Sample Skeleton for 'InterfacciaGrafica.fxml' Controller Class
 */

package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import model.BenchmarkOutput;
import model.DiagnosiU;
import model.Linea;
import model.Model;
import model.OptimizationResult;
import model.Parametro;
import model.SimResult;
import model.WorkStation;

public class Controller {
	
	 private Model model;
	 private Linea linea = new Linea();
	

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
    private TextField minCf; // Value injected by FXMLLoader

    @FXML // fx:id="MaxCf"
    private TextField MaxCf; // Value injected by FXMLLoader

    @FXML // fx:id="h4"
    private HBox h4; // Value injected by FXMLLoader

    @FXML // fx:id="minCr"
    private TextField minCr; // Value injected by FXMLLoader

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
    private ComboBox<WorkStation> wss; // Value injected by FXMLLoader

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
    void doEditWS(ActionEvent event) {
    WorkStation ws = wss.getValue();
    
    if(ws != null) {
    	linea.getListaWS().remove(ws);
    	this.creaWS(event);
    	wss.getItems().remove(ws);
    	
    	
    	/*String nome= ws.getNome();
    	double t0=ws.getTe();
    	double c0= ws.getCe();
    	int m= ws.getM();
    	WorkStation newWS=new WorkStation()
    	if(ws.isGuasti()) {
    		
    	}
    	
    	if(ws.isSetup()) {
    		
    	}
    	
    	if(ws.isRilavorazioni()) {
    		
    	}*/
    	
    	
    }
    else
    	txtResult.setText("Selezionare una workstation");
    	
    }
    
    

    @FXML
    void aggiungiWs(ActionEvent event) {
    	WorkStation ws= wss.getValue();
    	if(ws != null) {
    		if(ws.getNome() != null) {
    	linea.addWS(ws);
    	txtResult.setText("Aggiunta workstation "+ws+" alla linea");
    	}
    		else
    			txtResult.setText("SELEZIONARE UNA WORKSTATION");		
    	}
    	else
    		txtResult.setText("SELEZIONARE UNA WORKSTATION");
    }
    

    boolean SET=false;
    
    @FXML
    void creaLinea(ActionEvent event) {
    	if(SET) {
    	nameWS.clear();
    	t0.clear();
    	c0.clear();
    	m.clear();
    	check1.setSelected(false);
    	maxMf.clear();
    	minMf.clear();
    	maxMr.clear();
    	minMr.clear();
    	MaxCf.clear();
    	minCf.clear();
    	maxCr.clear();
    	minCr.clear();
    	check2.setSelected(false);
    	maxNs.clear();
    	minNs.clear();
    	maxTs.clear();
    	minTs.clear();
    	maxCs.clear();
    	minCs.clear();
    	check3.setSelected(false);
    	maxP.clear();
    	minP.clear();
    	
    	h1.setDisable(true);
    	h2.setDisable(true);
    	h3.setDisable(true);
    	h4.setDisable(true);
    	h5.setDisable(true);
    	h6.setDisable(true);
    	h7.setDisable(true);
    	h8.setDisable(true);
    	
    	if(linea.getListaWS().size()>0) {
    	txtResult.setText("Linea creata");
    	model.addLinea(linea);
    	hA.setDisable(false);
    	hB.setDisable(false);
    	hC.setDisable(false);
    	txtResult.setDisable(false);
    	
    	SET=false;
    	}
    	else
    		txtResult.setText("INSERIRE ALMENO UNA WORKSTATION ");
    	}
    	else
    		txtResult.setText("Eseguire prima il SET della linea \n");

    }

    @FXML
    void creaWS(ActionEvent event) {
    	
    	if(nameWS.getText().equals("") || t0.getText().equals("") || c0.getText().equals("") || m.getText().equals(""))
    		txtResult.setText("INSERIRE TUTTI I PARAMETRI DI PROGETTAZIONE");
    	else {
    		try {
    		String nome=nameWS.getText();
        	double t=Double.parseDouble(t0.getText()); //ipotizzo valori in secondi
        	double c=Double.parseDouble(c0.getText());
        	int macchine=Integer.parseInt(m.getText());
    	
    	WorkStation ws=new WorkStation(nome,t,c, macchine);
    	
    	if(check1.isSelected())
    	{
    		
    		if(!maxMf.getText().equals("") && !minMf.getText().equals("") && !maxMr.getText().equals("") && !minMr.getText().equals("") && !MaxCf.getText().equals("") && !minCf.getText().equals("") && !maxCr.getText().equals("") && !minCr.getText().equals("") )
    		{
    		try {	
    		ws.setGuasti(true);
    		ws.setMf(new Parametro("Mf",Double.parseDouble(maxMf.getText()),Double.parseDouble(minMf.getText()), ws ));
    		ws.setMr(new Parametro("Mr",Double.parseDouble(maxMr.getText()),Double.parseDouble(minMr.getText()), ws ));
    		ws.setCf(new Parametro("Cf",Double.parseDouble(MaxCf.getText()),Double.parseDouble(minCf.getText()), ws ));
    		ws.setCr(new Parametro("Cr",Double.parseDouble(maxCr.getText()),Double.parseDouble(minCr.getText()), ws ));
    		}
    		catch(NumberFormatException e) {
    			txtResult.setText("FORMATO PARAMETRI SUPPLEMENTARI ERRATO, i parametri in questione saranno ignorati");
    		}
    		}
    		
    	}
    	
    	if(check2.isSelected())
    	{
    		if(!maxNs.getText().equals("") && !minNs.getText().equals("") && !maxTs.getText().equals("") && !minTs.getText().equals("") && !maxCs.getText().equals("") && !minCs.getText().equals("") )
    		{
    			try {
    		ws.setSetup(true);
    		ws.setNs(new Parametro("Ns",Double.parseDouble(maxNs.getText()),Double.parseDouble(minNs.getText()), ws ));
    		ws.setTs(new Parametro("Ts",Double.parseDouble(maxTs.getText()),Double.parseDouble(minTs.getText()), ws ));
    		ws.setCs(new Parametro("Cs",Double.parseDouble(maxCs.getText()),Double.parseDouble(minCs.getText()), ws ));
    			}
    			catch(NumberFormatException e) {
        			txtResult.setText("FORMATO PARAMETRI SUPPLEMENTARI ERRATO, i parametri in questione saranno ignorati");
    			}
    		}
    	}
    	
    	if(check3.isSelected())
    	{
    		if(!maxP.getText().equals("") && !minP.getText().equals("")) {
    			try {
    		ws.setRilavorazioni(true);
    		ws.setP(new Parametro("P",Double.parseDouble(maxP.getText()),Double.parseDouble(minP.getText()), ws ));
    			}
    			catch(NumberFormatException e) {
        			txtResult.setText("FORMATO PARAMETRI SUPPLEMENTARI ERRATO, i parametri in questione saranno ignorati");

    			}
    		}
    	
    	}
    	
    	wss.getItems().add(ws);
    	
    	nameWS.clear();
    	t0.clear();
    	c0.clear();
    	m.clear();
    	check1.setSelected(false);
    	maxMf.clear();
    	minMf.clear();
    	maxMr.clear();
    	minMr.clear();
    	MaxCf.clear();
    	minCf.clear();
    	maxCr.clear();
    	minCr.clear();
    	check2.setSelected(false);
    	maxNs.clear();
    	minNs.clear();
    	maxTs.clear();
    	minTs.clear();
    	maxCs.clear();
    	minCs.clear();
    	check3.setSelected(false);
    	maxP.clear();
    	minP.clear();
    	
    	h1.setDisable(true);
    	h2.setDisable(true);
    	h3.setDisable(true);
    	h4.setDisable(true);
    	h5.setDisable(true);
    	h6.setDisable(true);
    	h7.setDisable(true);
    	h8.setDisable(true);
    	
    		}
    		catch(NumberFormatException e) {
    			txtResult.setText("FORMATO DEI PARAMETRI DI PROGETTAZIONE ERRATO");
    		}
    	}
    }

   

    @FXML
    void ottimizza(ActionEvent event) {
    	txtResult.clear();
    	if(linea.getListaWS().size() > 0) {
    	String prodotto = comboProdotto.getValue();
    	if(prodotto != null) {
    	OptimizationResult res= model.ottimizza(prodotto, linea);
    	
    	if(res.getLock() != null) {
			txtResult.appendText("Utilizzazione eccessiva su una o più ws della linea!!! Valori medi di utilizzazione per ws: \n");
			for(WorkStation ws: res.getLock().keySet()) {
				if(res.getLock().get(ws) > 0)
					txtResult.appendText(ws+": "+ res.getLock().get(ws)+"\n");
					else
						txtResult.appendText(ws+" utilizzazione troppo elevata fin dal primo giorno di simulazione \n");
			}
			txtResult.appendText("Si consiglia di abbassare i valori dei parametri con le utilizzazioni medie maggiori \n");
			
		}
    	else {
    		
    		//per individuare criticità valuto valori medi su tutto l'anno
    		for(WorkStation ws: res.getRisultatiOttimiSimulazione().getDiagnosiU().keySet()) {
    			double somma=0;
    			for(DiagnosiU du:res.getRisultatiOttimiSimulazione().getDiagnosiU().get(ws)) {
    				
    				somma+=du.getUtilizzazione();
    			}
    			
    			
    			
    			double media= somma/res.getRisultatiOttimiSimulazione().getDiagnosiU().get(ws).size();
    				txtResult.appendText("La workstation "+ws+" ha un' utilizzazione media di "+media+"\n");
    			
    		}
    		
    		BenchmarkOutput benchMark=res.getRisultatiOttimiSimulazione().getBo();
    		double count2= benchMark.getCountBoth();
    		double countTH= benchMark.getCountTH();
    		double countCT= benchMark.getCountCT();
    		txtResult.appendText("Benchmarking interno: \nla linea ha avuto performances peggiori di THpwc e CTpwc "+count2+" giorni, \n"
    				+ "solo di THpwc "+countTH+" giorni,\ne solo di CTpwc "+countCT+" giorni \n");
    		
    		
    		 System.out.println("Parametri ottimi: \n");
    		 List<Parametro> parametri=res.getParametriOttimi();
             Collections.sort(parametri);
             for(Parametro p:parametri) {
            	 txtResult.appendText(p.getWs()+" "+p.getNome()+" "+ (double)Math.round(p.getCurrent() * 100)/100+"\n"); 
             }

    	}
    	}
    	else
    		txtResult.setText("SELEZIONARE UN PRODOTTO");
    	}
    	else
    		txtResult.setText("PER EFFETTUARE LA SIMULAZIONE BISOGNA PRIMA CREARE UNA LINEA");
    }

    
    @FXML
    void simulazione(ActionEvent event) {
    	txtResult.clear();
    	if(linea.getListaWS().size() > 0) {
    	String prodotto= comboProdotto.getValue();
    	if(prodotto != null) {
    	SimResult res= model.simula(prodotto, linea);
    	
    	for(WorkStation ws: res.getDiagnosiU().keySet()) {
			boolean flag=false;
			double somma=0;
			LocalDate lockDay = null;
			WorkStation lockWS = null;
			double lockU = 0;
			for(DiagnosiU du:res.getDiagnosiU().get(ws)) {
				
				if(du.getUtilizzazione()>1) {
					
					//ad un certo punto dell'anno si blocca la linea, non è ammissibile
					
					lockDay=du.getData();
					lockWS=du.getWs();
					lockU=du.getUtilizzazione();
					flag=true;
					break;
				}
				somma+=du.getUtilizzazione();
			}
			
			if(flag) {
				txtResult.setText("Si verifica un blocco il giorno "+lockDay+" alla workstation "+lockWS +" per via di una u di "+lockU+"\n");
				break;
			}
			else {
			double media= somma/res.getDiagnosiU().get(ws).size();
			if(media <0.4) {
				txtResult.setText("La workstation "+ws+" ha un' utilizzazione media troppo bassa ("+media+") \n");
				
			}
			}
		}
		
		BenchmarkOutput benchMark=res.getBo();
		double count2= benchMark.getCountBoth();
		double countTH= benchMark.getCountTH();
		double countCT= benchMark.getCountCT();
		txtResult.appendText("Benchmarking interno: \nla linea ha avuto performances peggiori di THpwc e CTpwc "+count2+" giorni,\n"
				+ "solo di THpwc "+countTH+" giorni,\ne solo di CTpwc "+countCT+" giorni \n");
		
    	}
    	else {
    		txtResult.setText("SELEZIONARE UN PRODOTTO");
    	}
    	}
    	else
    		txtResult.setText("PER EFFETTUARE LA SIMULAZIONE BISOGNA PRIMA CREARE UNA LINEA");
    }
    
    
    @FXML
    void setLinea(ActionEvent event) {
    	SET=true;
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
    
    
    @FXML
    void showWs(ActionEvent event) {

    	nameWS.clear();
    	t0.clear();
    	c0.clear();
    	m.clear();
    	check1.setSelected(false);
    	maxMf.clear();
    	minMf.clear();
    	maxMr.clear();
    	minMr.clear();
    	MaxCf.clear();
    	minCf.clear();
    	maxCr.clear();
    	minCr.clear();
    	check2.setSelected(false);
    	maxNs.clear();
    	minNs.clear();
    	maxTs.clear();
    	minTs.clear();
    	maxCs.clear();
    	minCs.clear();
    	check3.setSelected(false);
    	maxP.clear();
    	minP.clear();
    	
    WorkStation ws= wss.getValue();
    if(ws != null) {
    nameWS.setText(ws.getNome());
    t0.setText(ws.getTe()+"");
    c0.setText(ws.getCe()+"");
    m.setText(ws.getM()+"");
    
    if(ws.isGuasti())
    {
    	check1.setSelected(true);
    	h1.setDisable(false);
    	h2.setDisable(false);
    	h3.setDisable(false);
    	h4.setDisable(false);
    	minMf.setText(ws.getMf().getMin()+"");
    	maxMf.setText(ws.getMf().getMax()+"");
    	minMr.setText(ws.getMr().getMin()+"");
    	maxMr.setText(ws.getMr().getMax()+"");
    	minCf.setText(ws.getCf().getMin()+"");
    	MaxCf.setText(ws.getCf().getMax()+"");
    	minCr.setText(ws.getCr().getMin()+"");
    	maxCr.setText(ws.getCr().getMax()+"");
    }
    
    if(ws.isSetup()) {
    	check2.setSelected(true);
    	h5.setDisable(false);
    	h6.setDisable(false);
    	h7.setDisable(false);
    	minNs.setText(ws.getNs().getMin()+"");
    	maxNs.setText(ws.getNs().getMax()+"");
    	minTs.setText(ws.getTs().getMin()+"");
    	maxTs.setText(ws.getTs().getMax()+"");
    	minCs.setText(ws.getCs().getMin()+"");
    	maxCs.setText(ws.getCs().getMax()+"");
    }
    
    if(ws.isRilavorazioni()) {
    	check3.setSelected(true);
    	h8.setDisable(false);
    	minP.setText(ws.getP().getMin()+"");
    	maxP.setText(ws.getP().getMax()+"");
    }
    }
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
		wss.getItems().add(new WorkStation());
		
		
	}
}
