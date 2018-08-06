package model;

public class TestModel {

	public static void main(String[] args) {
		Model model=new Model();
		
		Linea linea= new Linea();
		
		WorkStation ws1= new WorkStation("ws1", 500, 1.3, 2);
		
		WorkStation ws2= new WorkStation("ws2", 50, 1.3, 2);
		
		ws1.setSetup(true);
		ws1.setNsMAX(12);
		ws1.setNsMIN(10);
		ws1.setTsMAX(12);
		ws1.setTsMIN(10);
		ws1.setCsMAX(1.4);
		ws1.setCsMIN(1.3);
		
		linea.addWS(ws1);
		linea.addWS(ws2);
		SimResult res= model.simula("P_B", linea);
		if(res != null) {
			System.out.println("Utilizzazione in eccesso! ");
		}

	}

}
