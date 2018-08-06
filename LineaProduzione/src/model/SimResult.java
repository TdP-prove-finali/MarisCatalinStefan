package model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class SimResult {
	

	
	private HashMap<WorkStation,List<DiagnosiU>> diagnosiU;
	private List<Prestazioni> prestazioni;

	
	public SimResult(HashMap<WorkStation, List<DiagnosiU>> diagnosiU, List<Prestazioni> prestazioni) {
		
		this.diagnosiU = diagnosiU;
		this.prestazioni=prestazioni;
	}



	public HashMap<WorkStation, List<DiagnosiU>> getDiagnosiU() {
		return diagnosiU;
	}



	public List<Prestazioni> getPrestazioni() {
		return prestazioni;
	}
	
	



	
	
	
	
	
	
	

}
