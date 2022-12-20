package business.subCatálogos;



import java.util.ArrayList;

import data.SecçãoDAO;

public class Circuito {

	private int nVoltas;
	private String nome;
	private double comprimento;
	private int nCurvas;
	private int nChicanes;
	private int nRetas;
	private ArrayList<Secção> secções;
	private SecçãoDAO secçãoDAO;

	public Circuito(){
		this.secções = new ArrayList<Secção>();
		this.secçãoDAO = SecçãoDAO.getInstance();
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
}