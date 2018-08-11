package model;

import java.util.ArrayList;
import java.util.List;

public class OptimizationResult {
	
	private SimResult risultatiOttimiSimulazione;
	private List<Parametro> parametriOttimi;
	
	
	public OptimizationResult(SimResult risultatiOttimiSimulazione, List<Parametro> parametri) {
		
		this.risultatiOttimiSimulazione = risultatiOttimiSimulazione;
		
		parametriOttimi= new ArrayList<>();
		for(Parametro p:parametri) {
			parametriOttimi.add(new Parametro(p));
		}
		
	}


	public SimResult getRisultatiOttimiSimulazione() {
		return risultatiOttimiSimulazione;
	}


	public List<Parametro> getParametriOttimi() {
		return parametriOttimi;
	}


	
	
	

}
