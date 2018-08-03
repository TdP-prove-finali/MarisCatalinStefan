package model;

import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class Simulazione {
	
	// coda degli eventi
	private PriorityQueue<Domanda> queue;
	
	//parametri
	private List<Domanda> domande;
	private Linea linea;
	
	//modello del mondo
	HashMap<Domanda,List<Float>> diagnosiU;
	
	
	
	
	public Simulazione(List<Domanda> domande, Linea linea) {
		
		this.domande = domande;
		this.linea = linea;
	}



	public void init() {
		diagnosiU=new HashMap<>();
		queue=new PriorityQueue<>();
		
		for(Domanda d:domande) {
			queue.add(d);
		}
		
	}
	
	
	
	public void run() {
		
       Domanda d;
	while((d = queue.poll()) != null)  {
		processDemand(d);
	}  

	}


     //suppongo linea che lavori 24/24 h
	private void processDemand(Domanda d) {
		
		double tassoDiArrivo=d.getQuantita()/24;
		double ca=1; //suppongo tasso arrivo mediamente variabile
		
		for(WorkStation ws:linea.getListaWS()) {
			
			double te=ws.getTe();
			double ce=ws.getCe();
			int m=ws.getM();
			
			//scelgo parametri peggiori per simulazione semplice
			
			if(ws.isGuasti()) {
				
				float A = (ws.getMfMIN())/(ws.getMfMIN()+ws.getMrMAX());
				double t0=te;
				te=t0/A;
				
				double a= ce*ce;
				double b= A*(1-A)*(ws.getMrMAX()/t0);
				double c= (ws.getCrMAX()*ws.getCrMAX())*A*(1-A)*(ws.getMrMAX()/t0);
				
				ce=Math.sqrt(a+b+c);
				
				
			}
			
			if(ws.isSetup()) {
				
				double t0= te;
				
				te= t0+(ws.getTsMAX()/ws.getNsMIN());
				
				double s0=  ce*t0;
				double ss= ws.getCsMAX()*ws.getTsMAX();
				
				double a= s0*s0;
				double b= (ss*ss)/ws.getNsMIN();
				double c= ((ws.getNsMIN()-1)*(ws.getTsMAX()*ws.getTsMAX()))/(ws.getNsMIN()*ws.getNsMIN());
				
				double se= Math.sqrt(a+b+c);
				
				ce= se/te;
				
			}
			
			if(ws.isRilavorazioni()) {
				
				double t0= te;
				
				te= t0/(1-ws.getpMAX());
				
				double a= ce*ce;
				double b= (1-a)*ws.getpMAX();
				
				ce=Math.sqrt(a+b);
				
			}
			
		}
		
	}

	

}
