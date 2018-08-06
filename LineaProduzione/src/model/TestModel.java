package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		Model model=new Model();
		
		Linea linea= new Linea();
		
		WorkStation ws1= new WorkStation("ws1", 80, 1.3, 1);
		
		WorkStation ws2= new WorkStation("ws2", 15, 1.3, 1);
		
		ws1.setSetup(true);
		ws1.setNsMAX(12);
		ws1.setNsMIN(10);
		ws1.setTsMAX(12);
		ws1.setTsMIN(10);
		ws1.setCsMAX(1.4);
		ws1.setCsMIN(1.3);
		
		linea.addWS(ws1);
		linea.addWS(ws2);
		SimResult res= model.simula("P_B", linea);
		//per individuare criticit� valuto valori medi su tutto l'anno
	
		for(WorkStation ws: res.getDiagnosiU().keySet()) {
			boolean flag=false;
			double somma=0;
			LocalDate lockDay = null;
			WorkStation lockWS = null;
			double lockU = 0;
			for(DiagnosiU du:res.getDiagnosiU().get(ws)) {
				if(du.getUtilizzazione()>1) {
					//ad un certo punto dell'anno si blocca la linea, non � ammissibile
					lockDay=du.getData();
					lockWS=du.getWs();
					lockU=du.getUtilizzazione();
					flag=true;
					break;
				}
				somma+=du.getUtilizzazione();
			}
			
			if(flag) {
				System.out.println("Si verifica un blocco il giorno "+lockDay+" alla workstation "+lockWS +" per via di una u di "+lockU+"\n");
				break;
			}
			else {
			double media= somma/res.getDiagnosiU().get(ws).size();
			if(media <0.4)
				System.out.println("La workstation "+ws+" ha un' utilizzazione media troppo bassa ("+media+") \n");
			}
			
			
		}

	}

}
