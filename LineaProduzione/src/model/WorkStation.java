package model;

public class WorkStation {
	private String nome;
	
	private int m;
	private int te;
	private int ce;
	
	private boolean guasti;
	private int MfMAX;
	private int MfMIN;
	private int MrMAX;
	private int MrMIN;
	private int CfMAX;
	private int CfMIN;
	private int CrMAX;
	private int CrMIN;
	
	private boolean setup;
	private int NsMAX;
	private int NsMIN;
	private int TsMAX;
	private int TsMIN;
	private int CsMAX;
	private int CsMIN;
	
	
	private boolean rilavorazioni;
	private float pMAX;
	private float pMIN;
	
	
	
	
	
	
	
	public WorkStation(String nome, int m, int t0, int c0) {
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







	public int getTe() {
		return te;
	}







	public int getCe() {
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







	public int getMfMAX() {
		return MfMAX;
	}







	public void setMfMAX(int mfMAX) {
		MfMAX = mfMAX;
	}







	public int getMfMIN() {
		return MfMIN;
	}







	public void setMfMIN(int mfMIN) {
		MfMIN = mfMIN;
	}







	public int getMrMAX() {
		return MrMAX;
	}







	public void setMrMAX(int mrMAX) {
		MrMAX = mrMAX;
	}







	public int getMrMIN() {
		return MrMIN;
	}







	public void setMrMIN(int mrMIN) {
		MrMIN = mrMIN;
	}







	public int getCfMAX() {
		return CfMAX;
	}







	public void setCfMAX(int cfMAX) {
		CfMAX = cfMAX;
	}







	public int getCfMIN() {
		return CfMIN;
	}







	public void setCfMIN(int cfMIN) {
		CfMIN = cfMIN;
	}







	public int getCrMAX() {
		return CrMAX;
	}







	public void setCrMAX(int crMAX) {
		CrMAX = crMAX;
	}







	public int getCrMIN() {
		return CrMIN;
	}







	public void setCrMIN(int crMIN) {
		CrMIN = crMIN;
	}







	public int getNsMAX() {
		return NsMAX;
	}







	public void setNsMAX(int nsMAX) {
		NsMAX = nsMAX;
	}







	public int getNsMIN() {
		return NsMIN;
	}







	public void setNsMIN(int nsMIN) {
		NsMIN = nsMIN;
	}







	public int getTsMAX() {
		return TsMAX;
	}







	public void setTsMAX(int tsMAX) {
		TsMAX = tsMAX;
	}







	public int getTsMIN() {
		return TsMIN;
	}







	public void setTsMIN(int tsMIN) {
		TsMIN = tsMIN;
	}







	public int getCsMAX() {
		return CsMAX;
	}







	public void setCsMAX(int csMAX) {
		CsMAX = csMAX;
	}







	public int getCsMIN() {
		return CsMIN;
	}







	public void setCsMIN(int csMIN) {
		CsMIN = csMIN;
	}







	public float getpMAX() {
		return pMAX;
	}







	public void setpMAX(float pMAX) {
		this.pMAX = pMAX;
	}







	public float getpMIN() {
		return pMIN;
	}







	public void setpMIN(float pMIN) {
		this.pMIN = pMIN;
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
