public class Nodo implements Comparable<Nodo> {
	
	private int tono;
	private double prob;
	private Nodo izq;
	private Nodo der;
	
	public Nodo(int tono, double prob, Nodo izq, Nodo der) {
		this.tono = tono;
		this.prob = prob;
		this.izq = izq;
		this.der = der;
	}

	public int getTono() {
		return tono;
	}

	public void setTono(int tono) {
		this.tono = tono;
	}

	public double getProb() {
		return prob;
	}

	public void setProb(double prob) {
		this.prob = prob;
	}

	public Nodo getIzq() {
		return izq;
	}

	public void setIzq(Nodo izq) {
		this.izq = izq;
	}

	public Nodo getDer() {
		return der;
	}

	public void setDer(Nodo der) {
		this.der = der;
	}
	
	public void recorrer() {
		if(this.getIzq() != null)
			this.getIzq().recorrer();
		System.out.println(this.getTono());
		if(this.getDer() != null)
			this.getDer().recorrer();
	}
	

	
	@Override
	public int compareTo(Nodo o) {
		if(this.getProb() > o.getProb())
			return 1;
		else if(this.getProb() < o.getProb())
			return -1;
		return 0;
	}
	
	
	
	

}