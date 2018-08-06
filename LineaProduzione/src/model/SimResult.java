package model;

import java.time.LocalDate;

public class SimResult {
	
	private boolean uOver1;
	private WorkStation wsUOver1;
	private LocalDate dataUOver1;
	
	public SimResult(boolean uOver1, WorkStation wsUOver1, LocalDate dataUOver1) {
		
		this.uOver1 = uOver1;
		this.wsUOver1 = wsUOver1;
		this.dataUOver1 = dataUOver1;
	}

	public boolean isuOver1() {
		return uOver1;
	}

	public WorkStation getWsUOver1() {
		return wsUOver1;
	}

	public LocalDate getDataUOver1() {
		return dataUOver1;
	}
	
	
	
	
	
	
	

}
