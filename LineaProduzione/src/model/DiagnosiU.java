package model;

import java.time.LocalDate;

public class DiagnosiU implements Comparable<DiagnosiU> {
	
	private WorkStation ws;
	private LocalDate data;
	private double utilizzazione;
	
	public DiagnosiU(WorkStation ws, LocalDate data, double utilizzazione) {
	
		this.ws = ws;
		this.data = data;
		this.utilizzazione = utilizzazione;
	}

	public WorkStation getWs() {
		return ws;
	}

	public LocalDate getData() {
		return data;
	}

	public double getUtilizzazione() {
		return utilizzazione;
	}

	@Override
	public int compareTo(DiagnosiU d0) {
		
		return this.data.compareTo(d0.data);
	}
	
	

}
