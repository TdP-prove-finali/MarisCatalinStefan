package model;

import java.time.LocalDate;


public class TestModel {

	public static void main(String[] args) {
		Model model=new Model();
		
		Linea linea= new Linea();
		
		WorkStation ws1= new WorkStation("ws1", 28, 0.3, 2);
		
		WorkStation ws2= new WorkStation("ws2", 26, 0.1, 1);
		
		ws1.setSetup(true);
		ws1.setNsMAX(30);
		ws1.setNsMIN(29);
		ws1.setTsMAX(10);
		ws1.setTsMIN(2);
		ws1.setCsMAX(0.8);
		ws1.setCsMIN(0.2);
		
		linea.addWS(ws1);
		linea.addWS(ws2);
		SimResult res= model.simula("P_B", linea);
		
		//per individuare criticità valuto valori medi su tutto l'anno
	
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
				System.out.println("Si verifica un blocco il giorno "+lockDay+" alla workstation "+lockWS +" per via di una u di "+lockU+"\n");
				break;
			}
			else {
			double media= somma/res.getDiagnosiU().get(ws).size();
			if(media <0.4)
				System.out.println("La workstation "+ws+" ha un' utilizzazione media troppo bassa ("+media+") \n");
			}
		}
		
		BenchmarkOutput benchMark=res.getBo();
		double count2= benchMark.getCountBoth();
		double countTH= benchMark.getCountTH();
		double countCT= benchMark.getCountCT();
		System.out.println("Benchmarking interno: \n la linea è stata al di sotto di THpwc e CTpwc "+count2+" giorni, "
				+ "solo al di sotto di THpwc "+countTH+" giorni, e solo al di sotto di CTpwc "+countCT+" giorni");

	}

}
