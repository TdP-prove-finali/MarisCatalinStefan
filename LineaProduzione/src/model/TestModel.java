package model;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;


public class TestModel {

	public static void main(String[] args) {
		Model model=new Model();
		
		Linea linea= new Linea();
		
		WorkStation ws1= new WorkStation("ws1", 24, 1.3, 2);
		
		WorkStation ws2= new WorkStation("ws2", 26, 0.1, 1);
		
		ws1.setSetup(true);
		ws1.setNs(new Parametro("Ns",30,29, ws1));
		ws1.setTs(new Parametro("Ts", 10,9, ws1));
		ws1.setCs(new Parametro("Cs", 0.9, 0.8, ws1));
		
		linea.addWS(ws1);
		linea.addWS(ws2);
		OptimizationResult res = model.ottimizza("P_B", linea);
		 
		
		
		//per individuare criticità valuto valori medi su tutto l'anno
	
		for(WorkStation ws: res.getRisultatiOttimiSimulazione().getDiagnosiU().keySet()) {
			double somma=0;
			for(DiagnosiU du:res.getRisultatiOttimiSimulazione().getDiagnosiU().get(ws)) {
				
				somma+=du.getUtilizzazione();
			}
			
			
			
			double media= somma/res.getRisultatiOttimiSimulazione().getDiagnosiU().get(ws).size();
				System.out.println("La workstation "+ws+" ha un' utilizzazione media di "+media+"\n");
			
		}
		
		BenchmarkOutput benchMark=res.getRisultatiOttimiSimulazione().getBo();
		double count2= benchMark.getCountBoth();
		double countTH= benchMark.getCountTH();
		double countCT= benchMark.getCountCT();
		System.out.println("Benchmarking interno: \n la linea è stata al di sotto di THpwc e CTpwc "+count2+" giorni, "
				+ "solo al di sotto di THpwc "+countTH+" giorni, e solo al di sotto di CTpwc "+countCT+" giorni \n");
		
		
		 System.out.println("Parametri ottimi: \n");
		 List<Parametro> parametri=res.getParametriOttimi();
         Collections.sort(parametri);
         for(Parametro p:parametri) {
        	 System.out.println(p.getWs()+" "+p.getNome()+" "+p.getCurrent()+"\n"); 
         }
	}
	


}
