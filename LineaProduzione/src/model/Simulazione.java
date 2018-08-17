package model;

import java.util.ArrayList;
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
	private HashMap<WorkStation,List<DiagnosiU>> diagnosiU;
	private List<Prestazioni> listaPrestazioni;
	
	
	
	
	
	public Simulazione(List<Domanda> domande, Linea linea) {
		
		this.domande = domande;
		this.linea = linea;
	}



	public void init() {
		diagnosiU=new HashMap<>();
		listaPrestazioni= new ArrayList<>();
		queue=new PriorityQueue<>();
		
		for(Domanda d:domande) {
			queue.add(d);
		}
		
		for(WorkStation ws:linea.getListaWS()) {
			diagnosiU.put(ws, new ArrayList<>());
		}
		
	}
	
	
	
	public SimResult run() {
		
       Domanda d;
       SimResult result = null;
	while((d = queue.poll()) != null)  {
		boolean x= processDemand(d);
		if(!x) {
			break;
			
		}
	} 
	
	return new SimResult(diagnosiU, listaPrestazioni);

	}


     //suppongo linea che lavori 24/24 h
	private boolean processDemand(Domanda d) {
		Prestazioni prestazioni=new Prestazioni(d.getData());
		
		
		double ra=((double)d.getQuantita())/(24*60*60); //tasso di arrivo al secondo
		double ca=1; //suppongo tasso arrivo mediamente variabile
		
		for(WorkStation ws:linea.getListaWS()) {
	
			
			double te=ws.getTe();
			double ce=ws.getCe();
			int m=ws.getM();
			
			
			
			//scelgo parametri peggiori per simulazione semplice
			//l'ordine di applicazioni delle variazioni è questo (supposizione)
			
			if(ws.isGuasti()) {
			
				
				double A = (ws.getMf().getCurrent())/(ws.getMf().getCurrent()+ws.getMr().getCurrent());
				double t0=te;
				te=t0/A;
				
				double a= ce*ce;
				double b= A*(1-A)*(ws.getMr().getCurrent()/t0);
				double c= (ws.getCr().getCurrent()*ws.getCr().getCurrent())*A*(1-A)*(ws.getMr().getCurrent()/t0);
				
				ce=Math.sqrt(a+b+c);
				
				
			}
			
			if(ws.isSetup()) {
			
				double t0= te;
				
				te= t0+(ws.getTs().getCurrent()/ws.getNs().getCurrent());
				
				double s0=  ce*t0;
				double ss= ws.getCs().getCurrent()*ws.getTs().getCurrent();
				
				double a= s0*s0;
				double b= (ss*ss)/ws.getNs().getCurrent();
				double c= ((ws.getNs().getCurrent()-1)*(ws.getTs().getCurrent()*ws.getTs().getCurrent()))/(ws.getNs().getCurrent()*ws.getNs().getCurrent());
				
				double se= Math.sqrt(a+b+c);
				
				ce= se/te;
				
			}
			
			if(ws.isRilavorazioni()) {
				
		
				
				double t0= te;
				
				te= t0/(1-ws.getP().getCurrent());
				
				double a= ce*ce;
				double b= (1-a)*ws.getP().getCurrent();
				
				ce=Math.sqrt(a+b);
				
			}
			
			
			// suppongo di inserire parametri in secondi
			
			double u= (ra*te)/m;
			
			
			
			
			// aggiorno elenco utilizzazioni
			List<DiagnosiU> temp=diagnosiU.get(ws);
			temp.add(new DiagnosiU(ws, d.getData(), u));
			diagnosiU.put(ws, temp);
			
			if(u>1)
				return false; // perchè si blocca la linea
			
			
			//calcolo le prestazioni parziali della singola workstation con la formula più generale possibile
			
			//CT
			double x= ((ca*ca)+(ce*ce))/2;
			double y= (Math.sqrt(2*(m+1)))-1;
			double y2= Math.pow(u, y);
			double z= m*(1-u);
			
			double CTq=x*(y2/z)*te;
			double CT= CTq+te;
			
			
			//TH
			double TH= ra;
			
			
			//WIP
			double WIP= CT*TH;
			
			prestazioni.setCycleTime(CT);
			prestazioni.setWorkInProcess(WIP);
			
			//bisogna ricalcolare la variabilità in uscita (e quindi in entrata per la prossima ws) generata dalla workstation attuale
				
				if(m==1) {
					ca= Math.sqrt((u*u*ce*ce) + (1-u*u)*(ca*ca));
				}
				else if(m > 1) {
					
					double a= (1-u*u)*(ca*ca -1);
					double b= (u*u)/(Math.sqrt(m));
					double c= (ce*ce -1);
					
					ca= 1 + a + b*c;
				}
				
		}
		
		 
		listaPrestazioni.add(prestazioni);
		return true;
		
	}

	

}
