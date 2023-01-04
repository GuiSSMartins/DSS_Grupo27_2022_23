package business.subCatálogos;



import java.util.ArrayList;
import java.util.List;

import data.SeccaoDAO;

public class Circuito {

	private int nVoltas;
	private String nome;
	private double comprimento;
	private int nCurvas;
	private int nChicanes;
	private int nRetas;
	private List<Seccao> seccoes;
	private SeccaoDAO seccaoDAO;

	public Circuito(){
		this.seccoes = new ArrayList<Seccao>();
		this.seccaoDAO = SeccaoDAO.getInstance();
	}

	public Circuito(String nome, int nVoltas, double comprimento, int nCurvas, int nChicanes, int nRetas) {
		this.nVoltas = nVoltas;
		this.nome = nome;
		this.comprimento = comprimento;
		this.nCurvas = nCurvas;
		this.nChicanes = nChicanes;
		this.nRetas = nRetas;
		this.seccaoDAO = SeccaoDAO.getInstance();
		this.seccoes = new ArrayList<>();

		List<Seccao> list = seccaoDAO.getSeccoes(nome);
		setseccoes(list);

	}

	public Circuito(Circuito c) {
		this.nVoltas = c.getNVoltas();
		this.nome = c.getNome();
		this.comprimento = c.getComprimento();
		this.nCurvas = c.getNCurvas();
		this.nChicanes = c.getNChicanes();
		this.nRetas = c.getNCurvas();
		this.seccoes = c.getseccoes();
		this.seccaoDAO = SeccaoDAO.getInstance();
	}

	public int getNVoltas() {
		return this.nVoltas;
	}

	public void setNVoltas(int nVoltas) {
		this.nVoltas = nVoltas;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getComprimento() {
		return this.comprimento;
	}

	public void setComprimento(double comprimento) {
		this.comprimento = comprimento;
	}

	public int getNCurvas() {
		return this.nCurvas;
	}

	public void setNCurvas(int nCurvas) {
		this.nCurvas = nCurvas;
	}

	public int getNChicanes() {
		return this.nChicanes;
	}

	public void setNChicanes(int nChicanes) {
		this.nChicanes = nChicanes;
	}

	public int getNRetas() {
		return this.nRetas;
	}

	public void setNRetas(int nRetas) {
		this.nRetas = nRetas;
	}

	public List<Seccao> getseccoes() {
		List<Seccao> list = new ArrayList<>();

		for(Seccao s : this.seccoes) list.add(s.clone());

		return list;
	}

	public void setseccoes(List<Seccao> seccoes) {
		for(Seccao s : seccoes) {
			this.seccoes.add(s.clone());
		}
	}

	
	public int hashCode() {
		int lHashCode = 0;
		if ( this.nome != null ) {
			lHashCode += this.nome.hashCode();
		}
		if ( this.seccoes != null ) {
			lHashCode += this.seccoes.hashCode();
		}
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object aObject) {
		if (this == aObject) {
			return true;
		} else if (aObject instanceof Circuito) {
			Circuito lCircuitoObject = (Circuito) aObject;
			boolean lEquals = true;
			lEquals &= this.nVoltas == lCircuitoObject.nVoltas;
			lEquals &= ((this.nome == lCircuitoObject.nome)
					|| (this.nome != null && this.nome.equals(lCircuitoObject.nome)));
			lEquals &= this.comprimento == lCircuitoObject.comprimento;
			lEquals &= this.nCurvas == lCircuitoObject.nCurvas;
			lEquals &= this.nChicanes == lCircuitoObject.nChicanes;
			lEquals &= this.nRetas == lCircuitoObject.nRetas;
			lEquals &= ((this.seccoes == lCircuitoObject.seccoes)
					|| (this.seccoes != null && this.seccoes.equals(lCircuitoObject.seccoes)));
			return lEquals;
		}
		return false;
	}

	public double getTempoMedio() {
		double tempo = 0;
		for (Seccao s : this.seccoes) {
			tempo += s.getTempoMedio();
		}
		return tempo;
	}

	public double getTempoDesvio() {
		double tempo = 0;
		for (Seccao s : this.seccoes) {
			tempo += s.getTempoDesvio();
		}
		return tempo;
	}

	public Circuito clone() {
		Circuito clone = new Circuito(this);
		return clone;
	}
}