package DSS_Grupo27_2022_23.FaseIII.RacingManagerLN.subCatálogos;

import DSS_Grupo27_2022_23.FaseIII.RacingManagerLN.SubUtilizadores.Administrador;
import DSS_Grupo27_2022_23.FaseIII.RacingManagerLN.subPartidas.Corrida;
import DSS_Grupo27_2022_23.FaseIII.RacingManagerLN.subPartidas.Evento;

import java.util.ArrayList;

public class Circuito {

	private int _nVoltas;
	private String _nome;
	private double _comprimento;
	private int _nCurvas;
	private int _nChicanes;
	private int _nRetas;
	private ArrayList<Secção> _secções = new ArrayList<Secção>();

	public int hashCode() {
		int lHashCode = 0;
		if ( this._nome != null ) {
			lHashCode += this._nome.hashCode();
		}
		if ( this._secções != null ) {
			lHashCode += this._secções.hashCode();
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
			lEquals &= this._nVoltas == lCircuitoObject._nVoltas;
			lEquals &= ((this._nome == lCircuitoObject._nome)
					|| (this._nome != null && this._nome.equals(lCircuitoObject._nome)));
			lEquals &= this._comprimento == lCircuitoObject._comprimento;
			lEquals &= this._nCurvas == lCircuitoObject._nCurvas;
			lEquals &= this._nChicanes == lCircuitoObject._nChicanes;
			lEquals &= this._nRetas == lCircuitoObject._nRetas;
			lEquals &= ((this._secções == lCircuitoObject._secções)
					|| (this._secções != null && this._secções.equals(lCircuitoObject._secções)));
			return lEquals;
		}
		return false;
	}
}