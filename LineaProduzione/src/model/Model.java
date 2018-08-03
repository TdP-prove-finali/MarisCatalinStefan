package model;

import java.util.ArrayList;
import java.util.List;

import db.Dao;

public class Model {
	
	private Dao dao;
	
	
	private List<WorkStation> listaWS;
	private List<Linea> linee;
	private List<Domanda> domande;

	public Model()
	{
		listaWS=new ArrayList<>();
		linee=new ArrayList<>();
		dao=new Dao();
	}
	
	public void simula(String prodotto, Linea linea) {
		
		domande=dao.getDomande(prodotto);
		Simulazione sim=new Simulazione(domande,linea);
		sim.init();
		sim.run();
	}
	
	public List<String> getProdotti(){
		return dao.getProdotti();
	}
	
	public List<WorkStation> addWS(WorkStation ws)
	{
		listaWS.add(ws);
		return listaWS;
		
	}

	public void addLinea(Linea linea) {
		linee.add(linea);
	}
}
