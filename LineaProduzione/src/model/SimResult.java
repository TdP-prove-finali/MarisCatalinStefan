package model;

import java.util.HashMap;
import java.util.List;

public class SimResult {
	

	
	private HashMap<WorkStation,List<DiagnosiU>> diagnosiU;
	private List<Prestazioni> prestazioni;
	private BenchmarkOutput bo;

	
	public SimResult(HashMap<WorkStation, List<DiagnosiU>> diagnosiU, List<Prestazioni> prestazioni) {
		
		this.diagnosiU = diagnosiU;
		this.prestazioni=prestazioni;
		this.bo=null;
	}



	public HashMap<WorkStation, List<DiagnosiU>> getDiagnosiU() {
		return diagnosiU;
	}



	public List<Prestazioni> getPrestazioni() {
		return prestazioni;
	}



	public void setBo(BenchmarkOutput bo) {
		this.bo = bo;
	}



	public BenchmarkOutput getBo() {
		return bo;
	}
	
	
	
	



	
	
	
	
	
	
	

}
