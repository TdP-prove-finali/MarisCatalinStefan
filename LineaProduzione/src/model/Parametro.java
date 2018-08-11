package model;

public class Parametro implements Comparable<Parametro> {

	private double max;
	private double current;
	private double min;
	private WorkStation ws;
	
	private String nome;

	public Parametro( String nome, double max, double min, WorkStation ws) {
		
		this.max = max;
		this.min = min;
		this.ws=ws;
		this.nome = nome;
	}
	
	public Parametro(Parametro p) {
		this.nome=p.getNome();
		this.max=p.getMax();
		this.min=p.getMin();
		this.current=p.getCurrent();
		this.ws=p.getWs();
	}

	public WorkStation getWs() {
		return ws;
	}

	public double getCurrent() {
		return current;
	}

	public void setCurrent(double current) {
		this.current = current;
	}

	public double getMax() {
		return max;
	}

	public double getMin() {
		return min;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((ws == null) ? 0 : ws.hashCode());
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
		Parametro other = (Parametro) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (ws == null) {
			if (other.ws != null)
				return false;
		} else if (!ws.equals(other.ws))
			return false;
		return true;
	}

	@Override
	public int compareTo(Parametro p0) {
		return this.getWs().getNome().compareTo(p0.getWs().getNome());
	}


	
	
	
	
	
	
	
}
