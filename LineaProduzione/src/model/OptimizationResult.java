package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OptimizationResult {
	
	private SimResult risultatiOttimiSimulazione;
	private List<Parametro> parametriOttimi;
	private HashMap<WorkStation, Double> lock;
	
	
	public OptimizationResult(SimResult risultatiOttimiSimulazione, List<Parametro> parametri) {
		
		this.risultatiOttimiSimulazione = risultatiOttimiSimulazione;
		lock = null;
		
		parametriOttimi= new ArrayList<>();
		for(Parametro p:parametri) {
			parametriOttimi.add(new Parametro(p));
		}
		
	}
	
	public OptimizationResult(HashMap<WorkStation, Double> lock) {
		risultatiOttimiSimulazione = null;
		parametriOttimi = null;
		this.lock=lock;
	}


	public HashMap<WorkStation, Double> getLock() {
		return lock;
	}

	public SimResult getRisultatiOttimiSimulazione() {
		return risultatiOttimiSimulazione;
	}


	public List<Parametro> getParametriOttimi() {
		return parametriOttimi;
	}


	
	
	

}
