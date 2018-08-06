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
	
	
	
	public SimResult run() {
		
       Domanda d;
       SimResult result = null;
	while((d = queue.poll()) != null)  {
		 result= processDemand(d);
		 
		 if(result != null && result.isuOver1()) 
			 break;
	} 
	
	return result;

	}


     //suppongo linea che lavori 24/24 h
	private SimResult processDemand(Domanda d) {
		System.out.println("Giorno "+d.getData()+"\n");
		
		double ra=((double)d.getQuantita())/(24*60*60); //tasso di arrivo al secondo
		double ca=1; //suppongo tasso arrivo mediamente variabile
		
		for(WorkStation ws:linea.getListaWS()) {
			System.out.println("Worksation: "+ws.toString()+" ");
			
			double te=ws.getTe();
			double ce=ws.getCe();
			int m=ws.getM();
			
			System.out.println("Parametri: te= "+te+" ce= "+ce+" m= "+m+"\n");
			
			//scelgo parametri peggiori per simulazione semplice
			
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
			
			// suppongo di inserire parametri in secondi
			
			double u= (ra*te)/m;
			System.out.println("Tasso di arrivo: "+ra+"\n");
			System.out.println("Tempo di processo: "+te+"\n");
			System.out.println("Utilizzazione: "+u+"\n");
			
			if(u>1) {
				//situazione di eccessivo input di materiale, la linea è in overflow
				return new SimResult(true,ws,d.getData());
			}
			else {
				//situazione positiva, bisogna valutare la variabilità in uscita generata dalla workstation attuale
				
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
			
		}
		
		
		return null;
		
	}

	

}
