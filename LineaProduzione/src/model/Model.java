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
		
		//setto come valori dei parametri current i valori peggiori
		
		for(WorkStation ws: linea.getListaWS()) {
			
			if(ws.isGuasti()) {
				ws.setCurrentMf(ws.getMfMIN());
				ws.setCurrentMr(ws.getMrMAX());
				ws.setCurrentCf(ws.getCfMAX());
				ws.setCurrentCr(ws.getCrMAX());
			}
			
			if(ws.isSetup()) {
				ws.setCurrentNs(ws.getNsMIN());
				ws.setCurrentTs(ws.getTsMAX());
				ws.setCurrentCs(ws.getCsMAX());
			}
			
			if(ws.isRilavorazioni()) {
				ws.setCurrentP(ws.getpMAX());
			}
			
			
		}
		
		domande=dao.getDomande(prodotto);
		Simulazione sim=new Simulazione(domande,linea);
		sim.init();
		SimResult result=sim.run();
		BenchmarkOutput countOverPWC=this.benchmark(linea,result.getPrestazioni());
		result.setBo(countOverPWC);
		return result;
		
	}
	
	private BenchmarkOutput benchmark(Linea linea, List<Prestazioni> prestazioni) {
		
		int countBOTH=0;
		int countTH=0;
		int countCT=0;
		
		for(Prestazioni p: prestazioni) {
			System.out.println("Giorno "+p.getData()+"\n");
			double THpwc= this.THpwc(p.getWorkInProcess(), linea);
			double CTpwc= this.CTpwc(p.getWorkInProcess(), linea);
			
			double TH= p.getThroughput();
			double CT= p.getCycleTime();
			
			System.out.println(TH+"-"+THpwc+"   "+CT+"-"+CTpwc+"\n");
			
			if(TH<THpwc && CT>CTpwc)
				countBOTH++;
			if(TH<THpwc && CT<=CTpwc)
				countTH++;
			if(TH>= THpwc && CT>CTpwc)
				countCT++;
			
		}
		return new BenchmarkOutput(countBOTH, countTH, countCT);
	}
	
	private double THpwc(double wip, Linea linea) {
		
		double W0= this.getCriticalWIP(linea);
		double a= W0+wip-1;
		double rb= this.getBottleneckRate(linea);
		
		double THpwc= (wip/a)*rb;
		
		
		return THpwc;
	}
	
    private double CTpwc(double wip, Linea linea) {
    	
    	double a= wip-1;
    	double rb= this.getBottleneckRate(linea);
    	double T0= this.getRawProcessTime(linea);
    	
    	double CTpwc= T0+(a/rb);
		
		
		return CTpwc;
	}
    
    private double getRawProcessTime(Linea linea) {
    	
           double T0=0;
		
		for(WorkStation ws: linea.getListaWS()) {
			T0+=ws.getTe();
		}
		
		return T0;
    	
    }
	
	private double getCriticalWIP(Linea linea) {
		
		double T0= this.getRawProcessTime(linea);
		
		double rb= this.getBottleneckRate(linea);
		
		double W0= T0*rb;
		
		
		return W0;
	}
	
	private double getBottleneckRate(Linea linea) {
		
		double max=0;
		
		
		for(WorkStation ws: linea.getListaWS()) {
			//per semplicità considero collo di bottiglia la ws con tempo di processo maggiore
			double tp= ws.getTe()/ws.getM();
			
			if(tp> max)
				max=tp;
		}
		
		double bottleNeckRate= 1/max;
		
		return bottleNeckRate;
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
	
	public void ottimizza(String prodotto, Linea linea) {
		
		double counter=0;
		
      for(WorkStation ws: linea.getListaWS()) {
			
			if(ws.isGuasti()) {
				ws.setCurrentMf(ws.getMfMIN());
				ws.setCurrentMr(ws.getMrMIN());
				ws.setCurrentCf(ws.getCfMIN());
				ws.setCurrentCr(ws.getCrMIN());
			}
			
			if(ws.isSetup()) {
				ws.setCurrentNs(ws.getNsMIN());
				ws.setCurrentTs(ws.getTsMIN());
				ws.setCurrentCs(ws.getCsMIN());
			}
			
			if(ws.isRilavorazioni()) {
				ws.setCurrentP(ws.getpMIN());
			}	
		}
      
      
      
    
    
		
		
		
		
	}

}
