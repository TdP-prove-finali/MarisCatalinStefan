package model;

public class WorkStation {
	private String nome;
	
	private int m;
	private double te;
	private double ce;
	
	private boolean guasti;
	private double MfMAX;
	private double MfMIN;
	private double MrMAX;
	private double MrMIN;
	
	private double CfMAX;
	private double CfMIN;
	private double CrMAX;
	private double CrMIN;
	
	private boolean setup;
	private double NsMAX;
	private double NsMIN;
	private double TsMAX;
	private double TsMIN;
	
	private double CsMAX;
	private double CsMIN;
	
	
	private boolean rilavorazioni;
	
	private double pMAX;
	private double pMIN;
	
	
	
	
	
	
	
	public WorkStation(String nome,  double t0, double c0, int m) {
		this.nome=nome;
		this.m = m;
		this.te = t0;
		this.ce = c0;
		
		guasti=false;
		setup=false;
		rilavorazioni=false;
	}







	public int getM() {
		return m;
	}







	public double getTe() {
		return te;
	}







	public double getCe() {
		return ce;
	}







	public boolean isGuasti() {
		return guasti;
	}







	public void setGuasti(boolean guasti) {
		this.guasti = guasti;
	}







	public boolean isSetup() {
		return setup;
	}







	public void setSetup(boolean setup) {
		this.setup = setup;
	}







	public boolean isRilavorazioni() {
		return rilavorazioni;
	}







	public void setRilavorazioni(boolean rilavorazioni) {
		this.rilavorazioni = rilavorazioni;
	}







	public String getNome() {
		return nome;
	}







	public void setNome(String nome) {
		this.nome = nome;
	}







	public double getMfMAX() {
		return MfMAX;
	}







	public void setMfMAX(int mfMAX) {
		MfMAX = mfMAX;
	}







	public double getMfMIN() {
		return MfMIN;
	}







	public void setMfMIN(int mfMIN) {
		MfMIN = mfMIN;
	}







	public double getMrMAX() {
		return MrMAX;
	}







	public void setMrMAX(int mrMAX) {
		MrMAX = mrMAX;
	}







	public double getMrMIN() {
		return MrMIN;
	}







	public void setMrMIN(int mrMIN) {
		MrMIN = mrMIN;
	}







	public double getCfMAX() {
		return CfMAX;
	}







	public void setCfMAX(double d) {
		CfMAX = d;
	}







	public double getCfMIN() {
		return CfMIN;
	}







	public void setCfMIN(double d) {
		CfMIN = d;
	}







	public double getCrMAX() {
		return CrMAX;
	}







	public void setCrMAX(double d) {
		CrMAX = d;
	}







	public double getCrMIN() {
		return CrMIN;
	}







	public void setCrMIN(double d) {
		CrMIN = d;
	}







	public double getNsMAX() {
		return NsMAX;
	}







	public void setNsMAX(int nsMAX) {
		NsMAX = nsMAX;
	}







	public double getNsMIN() {
		return NsMIN;
	}







	public void setNsMIN(int nsMIN) {
		NsMIN = nsMIN;
	}







	public double getTsMAX() {
		return TsMAX;
	}







	public void setTsMAX(int tsMAX) {
		TsMAX = tsMAX;
	}







	public double getTsMIN() {
		return TsMIN;
	}







	public void setTsMIN(int tsMIN) {
		TsMIN = tsMIN;
	}







	public double getCsMAX() {
		return CsMAX;
	}







	public void setCsMAX(double d) {
		CsMAX = d;
	}







	public double getCsMIN() {
		return CsMIN;
	}







	public void setCsMIN(double d) {
		CsMIN = d;
	}







	public double getpMAX() {
		return pMAX;
	}







	public void setpMAX(double d) {
		this.pMAX = d;
	}







	public double getpMIN() {
		return pMIN;
	}







	public void setpMIN(double d) {
		this.pMIN = d;
	}







	@Override
	public String toString() {
		return nome;
	}







	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}







	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorkStation other = (WorkStation) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}








	
	
	
	
	
	
	
	
	
	
	

}
