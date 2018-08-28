package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import db.Dao;

public class Model {
	
	private Dao dao;
	private WorkStationIDMAP map;
	
	private List<WorkStation> listaWS;
	private List<Domanda> domande;
	private Linea linea;
	
	private HashMap<WorkStation, Double> uOttime;
	private HashMap<WorkStation, Double> uMedieLock;
	private HashMap<WorkStation, Integer> countLock;

	public Model()
	{
		map=new WorkStationIDMAP();
		listaWS=new ArrayList<>();
		dao=new Dao(map);
		uOttime=new HashMap<>();
		countLock = new HashMap<>();
		uMedieLock= new HashMap<>();
		
		
	}
	
	public SimResult simula(String prodotto, Linea linea) {
		
		//setto come valori dei parametri current i valori peggiori
		
		for(WorkStation ws: linea.getListaWS()) {
			
			if(ws.isGuasti()) {
				ws.getMf().setCurrent(ws.getMf().getMin());
				ws.getMr().setCurrent(ws.getMr().getMax());
				ws.getCf().setCurrent(ws.getCf().getMax());
				ws.getCf().setCurrent(ws.getCf().getMax());
			}
			
			if(ws.isSetup()) {
				ws.getNs().setCurrent(ws.getNs().getMin());
				ws.getTs().setCurrent(ws.getTs().getMax());
				ws.getCs().setCurrent(ws.getCs().getMax());
			}
			
			if(ws.isRilavorazioni()) {
				ws.getP().setCurrent(ws.getP().getMax());
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
			
			double THpwc= this.THpwc(p.getWorkInProcess(), linea);
			double CTpwc= this.CTpwc(p.getWorkInProcess(), linea);
			
			double TH= p.getThroughput();
			double CT= p.getCycleTime();
			
			
			
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
	
	public void createWS(WorkStation ws) {
		dao.addWS(ws);
	}
	
	public void deleteWS(WorkStation ws) {
		dao.deleteWS(ws);
	}

	public void addLinea(Linea linea) {
		this.linea=linea;	
	}
	
	public List<WorkStation> inizializzazioneWS(){
		return dao.getWSs();
	}
	
	
	
	private OptimizationResult soluzioneOttima;
	
	public OptimizationResult ottimizza(String prodotto, Linea linea) {
			
		List<Parametro> parametri= new ArrayList<>();
		
		 for(WorkStation ws: linea.getListaWS()) {
				
				if(ws.isGuasti()) {
					ws.getMf().setCurrent(ws.getMf().getMin());
					ws.getMr().setCurrent(ws.getMr().getMin());
					ws.getCf().setCurrent(ws.getCf().getMin());
					ws.getCf().setCurrent(ws.getCf().getMin());
				}
				
				if(ws.isSetup()) {
					ws.getNs().setCurrent(ws.getNs().getMin());
					ws.getTs().setCurrent(ws.getTs().getMin());
					ws.getCs().setCurrent(ws.getCs().getMin());
				}
				
				if(ws.isRilavorazioni()) {
					ws.getP().setCurrent(ws.getP().getMin());
				}
					
			}
		
		 for(WorkStation ws: linea.getListaWS()) {
				
				if(ws.isGuasti()) {
					parametri.add(ws.getMf());
					parametri.add(ws.getMr());
					parametri.add(ws.getCf());
					parametri.add(ws.getCr());
				}
				
				if(ws.isSetup()) {
					parametri.add(ws.getNs());
					parametri.add(ws.getTs());
					parametri.add(ws.getCs());
				}
				
				if(ws.isRilavorazioni()) {
					parametri.add(ws.getP());
				}
				
				uOttime.put(ws, 0.0);
				countLock.put(ws, 0);
				uMedieLock.put(ws, 0.0);
				
			}
		 
		 
		 domande=dao.getDomande(prodotto);
		 List<List<Parametro>> passati= new ArrayList<>();
		 
		 this.recursive(parametri, linea, domande, parametri);
		 
		 if(soluzioneOttima == null) {
			 for(WorkStation ws: linea.getListaWS()) {
				 uMedieLock.put(ws, uMedieLock.get(ws)/countLock.get(ws));
			 }
			 soluzioneOttima= new OptimizationResult(uMedieLock);
		 }
		 
		 
		 return soluzioneOttima;
		 
	}

	private void recursive(List<Parametro> parametri, Linea linea, List<Domanda> domande, List<Parametro> parametriDaSalvare) {
		
		if(parametri.size() == 0) {
		Simulazione sim= new Simulazione(domande, linea);
		 sim.init();
		 SimResult res=sim.run();
		 BenchmarkOutput countOverPWC=this.benchmark(linea,res.getPrestazioni());
		 res.setBo(countOverPWC);
		
		 
		 if(checkResult(res, parametriDaSalvare))
			soluzioneOttima= new OptimizationResult(res, parametriDaSalvare);
		}
		
		for(Parametro p: parametri) {
				
			  List<Parametro> parametriNew=new ArrayList<>(parametri);
			  parametriNew.remove(p);
				
			  for(double i=0; i+p.getMin() <= p.getMax()+0.01; i = i + 0.1 ) {  //+0.01 for IEEE Arithmetic
				
				 p.setCurrent(p.getMin()+i);
				recursive(parametriNew,linea, domande, parametriDaSalvare);
				p.setCurrent(p.getMin());
				
			}
				
			
		}
	}
	
	private boolean checkResult(SimResult res, List<Parametro> parametriDaSalvare) {
		   boolean flag2 = false;
		
			HashMap<WorkStation, Double> uMedie= new HashMap<>();
			for(WorkStation ws: res.getDiagnosiU().keySet()) {
				if(res.getDiagnosiU().get(ws).size() == 0) {
					flag2 = true;
				}
				
				else if(res.getDiagnosiU().get(ws).size() < 365) {
					flag2 = true;  // c'è stato un blocco
					double sum=0;
					countLock.put(ws,countLock.get(ws)+1);
					for(DiagnosiU du:res.getDiagnosiU().get(ws)) {
						
						sum+=du.getUtilizzazione();
					}
					
					double media = sum/res.getDiagnosiU().get(ws).size();
					uMedieLock.put(ws,uMedieLock.get(ws)+media);
				}
				else{
				double sum=0;
				for(DiagnosiU du:res.getDiagnosiU().get(ws)) {
					
					sum+=du.getUtilizzazione();
				}
				
				double media = sum/res.getDiagnosiU().get(ws).size();
				
				uMedie.put(ws,media);
				}
			}
			
			if(flag2)
				return false;
			
			
			
			boolean flag= true;
			
				for(WorkStation ws: res.getDiagnosiU().keySet() ) {
					if(uMedie.get(ws) >=  uOttime.get(ws)) {
						uOttime.put(ws,uMedie.get(ws));
					}
					else
						flag=false;
				}
				
				if(flag) {
					return true;
					
				}
					
			
		
			
		return false;
	}

}
