package model;

import java.time.LocalDate;

public class Domanda implements Comparable<Domanda> {

	private LocalDate data;
	private int quantita;
	
	
	public Domanda(LocalDate data, int quantita) {
		
		this.data = data;
		this.quantita = quantita;
	}


	public LocalDate getData() {
		return data;
	}


	public int getQuantita() {
		return quantita;
	}


	@Override
	public int compareTo(Domanda d) {
		
		return this.data.compareTo(d.data);
	}
	
	
	
	
	
	
}
