package model;

import java.util.ArrayList;
import java.util.List;

public class Linea {
	
	private String nome;
	private List<WorkStation> listaWS;
	
	public Linea()
	{
		listaWS=new ArrayList<>();
	}
	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return nome;
	}

	public void addWS(WorkStation ws)
	{
		listaWS.add(ws);
	}
	
	public List<WorkStation> getListaWS()
	{
		return listaWS;
	}
	
	
	

}
