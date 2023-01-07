package business.subPartidas;

import business.subCatálogos.Carro;
import business.subCatálogos.Piloto;

public class Progresso implements Comparable<Progresso>{
	private String email;
	private Piloto piloto;
	private Carro carro;
	private long tempo;
	private int seccao;
	private int volta;

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Piloto getPiloto() {
		return this.piloto;
	}

	public void setPiloto(Piloto piloto) {
		this.piloto = piloto;
	}

	public Carro getCarro() {
		return this.carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public long getTempo() {
		return this.tempo;
	}

	public void setTempo(long tempo) {
		this.tempo = tempo;
	}

	public int getSeccao() {
		return this.seccao;
	}

	public void setSeccao(int seccao) {
		this.seccao = seccao;
	}

	public int getVolta() {
		return this.volta;
	}

	public void setVolta(int volta) {
		this.volta = volta;
	}

	public Progresso(String email, Piloto piloto, Carro carro) {
		this.email = email;
		this.piloto = piloto;
		this.carro = carro;
		this.seccao = -1;
		this.volta = -1;
		this.tempo = 0;
	}

	public Progresso(Piloto piloto, Carro carro, long tempo, int seccao) {
		this.piloto = piloto;
		this.carro = carro;
		this.tempo = tempo;
		this.seccao = seccao;
		this.volta = -1;
		this.email = null;
	}

	public Progresso(Progresso p) {
		this.email = p.getEmail();
		this.carro = p.getCarro();
		this.piloto = p.getPiloto();
		this.tempo = p.getTempo();
		this.seccao = p.getSeccao();
		this.volta = p.getVolta();
	}

	public void update(Evento aE, String aIdJogador) {
		throw new UnsupportedOperationException();
	}

	public boolean equals(Object aObject) {
		if (this == aObject) {
			return true;
		} else if (aObject instanceof Progresso) {
			Progresso lProgressoObject = (Progresso) aObject;
			boolean lEquals = true;
			lEquals &= ((this.piloto == lProgressoObject.piloto)
					|| (this.piloto != null && this.piloto.equals(lProgressoObject.piloto)));
			lEquals &= ((this.carro == lProgressoObject.carro)
					|| (this.carro != null && this.carro.equals(lProgressoObject.carro)));
			lEquals &= this.tempo == lProgressoObject.tempo;
			lEquals &= this.seccao == lProgressoObject.seccao;
			return lEquals;
		}
		return false;
	}

	public int compareTo(Progresso p) {
		if (this.tempo < p.getTempo())
			return -1;
		else if (this.tempo > p.getTempo())
			return 1;
		else
			return 1;
	}

	public Progresso clone() {
		return new Progresso(this);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getEmail());
		sb.append(this.tempo);
		return sb.toString();
	}

}