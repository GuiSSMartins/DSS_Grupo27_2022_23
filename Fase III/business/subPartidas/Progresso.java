package business.subPartidas;

import business.subCatálogos.Carro;
import business.subCatálogos.Piloto;

public class Progresso {
	private Piloto piloto;
	private Carro carro;
	private double tempo;
	private int seccao;

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

	public double getTempo() {
		return this.tempo;
	}

	public void setTempo(double tempo) {
		this.tempo = tempo;
	}

	public int getSeccao() {
		return this.seccao;
	}

	public void setSeccao(int seccao) {
		this.seccao = seccao;
	}

	public Progresso(Piloto piloto, Carro carro, double tempo, int seccao)
	{
		this.piloto = piloto.clone();
		this.carro = carro.clone();
		this.tempo = tempo;
		this.seccao = seccao;
	}

	public Progresso(Progresso p)
	{
		this.carro=p.getCarro();
		this.piloto=p.getPiloto();
		this.tempo=p.getTempo();
		this.seccao=p.getSeccao();
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
	
	public Integer compareTo(Progresso p)
	{
		if(this.tempo<p.getTempo())
			return -1;
		else if(this.tempo>p.getTempo())
			return 1;
		else
			return 0; 
	}

	public Progresso clone()
    {
        return new Progresso(this);
    }

}