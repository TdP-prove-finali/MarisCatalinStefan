package model;

import java.util.ArrayList;
import java.util.List;

public class Linea {
	private List<WorkStation> listaWS;
	
	public Linea()
	{
		listaWS=new ArrayList<>();
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
