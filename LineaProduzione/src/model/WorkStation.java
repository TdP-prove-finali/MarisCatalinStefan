package model;

public class WorkStation {
	private String nome;
	
	private int m;
	private double te;
	private double ce;
	
	
	private boolean guasti;
	
	private Parametro Mf;
	private Parametro Mr;
    private Parametro Cf;
	private Parametro Cr;
	
	
	private boolean setup;
	
	private Parametro Ns;
	private Parametro Ts;
	private Parametro Cs;
	
	
	private boolean rilavorazioni;
	
	private Parametro p;
	
	
	
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

	public Parametro getMf() {
		return Mf;
	}

	public void setMf(Parametro mf) {
		Mf = mf;
	}

	public Parametro getMr() {
		return Mr;
	}


	public void setMr(Parametro mr) {
		Mr = mr;
	}


	public Parametro getCf() {
		return Cf;
	}

	public void setCf(Parametro cf) {
		Cf = cf;
	}

	public Parametro getCr() {
		return Cr;
	}

	public void setCr(Parametro cr) {
		Cr = cr;
	}

	public Parametro getNs() {
		return Ns;
	}

	public void setNs(Parametro ns) {
		Ns = ns;
	}

	public Parametro getTs() {
		return Ts;
	}

	public void setTs(Parametro ts) {
		Ts = ts;
	}

	public Parametro getCs() {
		return Cs;
	}

	public void setCs(Parametro cs) {
		Cs = cs;
	}

	public Parametro getP() {
		return p;
	}


	public void setP(Parametro p) {
		this.p = p;
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
