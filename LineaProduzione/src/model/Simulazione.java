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
			System.out.println("Utilizzazione eccessiva!!!!!!! \n");
			break;
			
		}
	} 
	
	return new SimResult(diagnosiU, listaPrestazioni);

	}


     //suppongo linea che lavori 24/24 h
	private boolean processDemand(Domanda d) {
		Prestazioni prestazioni=new Prestazioni(d.getData());
		
		System.out.println("Giorno "+d.getData()+"\n");
		
		double ra=((double)d.getQuantita())/(24*60*60); //tasso di arrivo al secondo
		double ca=1; //suppongo tasso arrivo mediamente variabile
		
		for(WorkStation ws:linea.getListaWS()) {
			System.out.println("Worksation: "+ws.toString()+" ");
			
			double te=ws.getTe();
			double ce=ws.getCe();
			int m=ws.getM();
			
			//System.out.println("Parametri: te= "+te+" ce= "+ce+" m= "+m+"\n");
			
			//scelgo parametri peggiori per simulazione semplice
			//l'ordine di applicazioni delle variazioni è questo (supposizione)
			
			if(ws.isGuasti()) {
			
				
				double A = (ws.getMfMIN())/(ws.getMfMIN()+ws.getMrMAX());
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
			
			System.out.println("Variabilità di processo: "+ce+"\n");
			
			// suppongo di inserire parametri in secondi
			
			double u= (ra*te)/m;
			
			
			
			
			// aggiorno elenco utilizzazioni
			List<DiagnosiU> temp=diagnosiU.get(ws);
			temp.add(new DiagnosiU(ws, d.getData(), u));
			diagnosiU.put(ws, temp);
			
			if(u>1)
				return false; // perchè si blocca la linea
			
			System.out.println("Tasso di arrivo: "+ra+"\n");
			System.out.println("Tempo di processo: "+te+"\n");
			System.out.println("Utilizzazione: "+u+"\n");
			
			//calcolo le prestazioni parziali della singola workstation con la formula più generale possibile
			
			//CT
			double x= ((ca*ca)+(ce*ce))/2;
			double y= (Math.sqrt(2*(m+1)))-1;
			double y2= Math.pow(u, y);
			double z= m*(1-u);
			
			double CTq=x*(y2/z)*te;
			double CT= CTq+te;
			System.out.println("CT: "+CT+"\n");
			
			//TH
			double TH= ra;
			System.out.println("TH: "+TH+"\n");
			
			//WIP
			double WIP= CT*TH;
			System.out.println("WIP: "+WIP+"\n");
			
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
				
				System.out.println("Variabilità in uscita: "+ ca +"\n");
				
				
			
		}
		
		 
		listaPrestazioni.add(prestazioni);
		return true;
		
	}

	

}
