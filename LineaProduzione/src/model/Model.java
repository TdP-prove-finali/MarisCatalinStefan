package model;

import java.util.ArrayList;
import java.util.List;

import db.Dao;

public class Model {
	
	private Dao dao;
	
	
	private List<WorkStation> listaWS;
	private List<Domanda> domande;
	private Linea linea;

	public Model()
	{
		listaWS=new ArrayList<>();
		dao=new Dao();
	}
	
	public SimResult simula(String prodotto, Linea linea) {
		
		
		
		domande=dao.getDomande(prodotto);
		Simulazione sim=new Simulazione(domande,linea);
		sim.init();
		SimResult result=sim.run();
		int countOverPWC=this.benchmark(linea,result.getPrestazioni()) ;
		return result;
	}
	
	private int benchmark(Linea linea, List<Prestazioni> prestazioni) {
		//fai benchmark
		
		
		
		
		return 0;
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
		this.linea=linea;
		
	}

}
