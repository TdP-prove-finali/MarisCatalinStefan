package model;

import java.util.Collections;
import java.util.List;


public class TestModel {

	public static void main(String[] args) {
		Model model=new Model();
		
		Linea linea= new Linea();
		
		WorkStation ws1= new WorkStation("ws1", 10, 0.9, 1);
		
		WorkStation ws2= new WorkStation("ws2", 15.6, 1.1, 2);
		
		/*ws1.setSetup(true);
		ws1.setNs(new Parametro("Ns",10,7, ws1));
		ws1.setTs(new Parametro("Ts", 2,1, ws1));
		ws1.setCs(new Parametro("Cs", 0.9,0.8 , ws1));*/
		
		/*ws2.setGuasti(true);
		ws2.setMf(new Parametro("Mf",130,130, ws2));
		ws2.setMr(new Parametro("Mr", 4,4, ws2));
		ws2.setCf(new Parametro("Cf", 1.1, 1.1, ws2));
		ws2.setCr(new Parametro("Cr", 1.1, 1.1, ws2));*/
		
		ws2.setRilavorazioni(true);
		ws2.setP(new Parametro("P", 0.3, 0.1, ws2));
	
		
		
		
		
		linea.addWS(ws1);
		linea.addWS(ws2);
		OptimizationResult res = model.ottimizza("P_B", linea);
		 
		if(res.getLock() != null) {
			System.out.println("Utilizzazione eccessiva su una o più ws della linea!!! \nAlmeno un giorno per ogni anno di simulazione supera il valore massimo di utilizzazione \nValori medi di utilizzazione per ws: \n");
			for(WorkStation ws: res.getLock().keySet()) {
				if(res.getLock().get(ws) > 0)
				System.out.println(ws+": "+ res.getLock().get(ws)+"\n");
				else
					System.out.println(ws+" utilizzazione troppo elevata fin dal primo giorno di simulazione");
					
			}
			
		}
		else {
		
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
	


}
