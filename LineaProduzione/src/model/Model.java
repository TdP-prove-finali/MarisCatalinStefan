package model;

import java.util.ArrayList;
import java.util.List;

public class Model {
	List<WorkStation> listaWS;
	Linea linea;

	public Model()
	{
		listaWS=new ArrayList<>();
	}
	
	public List<WorkStation> addWS(WorkStation ws)
	{
		listaWS.add(ws);
		return listaWS;
		
	}

	public Linea getLinea() {
		return linea;
	}

	public void setLinea(Linea linea) {
		this.linea = linea;
	}
}
