package business.subCatálogos;



import java.util.ArrayList;
import java.util.List;

import data.SecçãoDAO;

public class Circuito {

	private int nVoltas;
	private String nome;
	private double comprimento;
	private int nCurvas;
	private int nChicanes;
	private int nRetas;
	private List<Secção> secções;
	private SecçãoDAO secçãoDAO;

	public Circuito(){
		this.secções = new ArrayList<Secção>();
		this.secçãoDAO = SecçãoDAO.getInstance();
	}

	public Circuito(String nome, int nVoltas, double comprimento, int nCurvas, int nChicanes, int nRetas) {
		this.nVoltas = nVoltas;
		this.nome = nome;
		this.comprimento = comprimento;
		this.nCurvas = nCurvas;
		this.nChicanes = nChicanes;
		this.nRetas = nRetas;
		this.secções = new ArrayList<>();
		this.secçãoDAO = SecçãoDAO.getInstance();
	}

	public Circuito(Circuito c) {
		this.nVoltas = c.getNVoltas();
		this.nome = c.getNome();
		this.comprimento = c.getComprimento();
		this.nCurvas = c.getNCurvas();
		this.nChicanes = c.getNChicanes();
		this.nRetas = c.getNCurvas();
		this.secções = c.getSecções();
		this.secçãoDAO = SecçãoDAO.getInstance();
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

	public List<Secção> getSecções() {
		return this.secções;
	}

	public void setSecções(List<Secção> secções) {
		this.secções = secções;
	}

	
	public int hashCode() {
		int lHashCode = 0;
		if ( this.nome != null ) {
			lHashCode += this.nome.hashCode();
		}
		if ( this.secções != null ) {
			lHashCode += this.secções.hashCode();
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
			lEquals &= ((this.secções == lCircuitoObject.secções)
					|| (this.secções != null && this.secções.equals(lCircuitoObject.secções)));
			return lEquals;
		}
		return false;
	}

	public Circuito clone() {
		Circuito clone = new Circuito(this);
		return clone;
	}
}