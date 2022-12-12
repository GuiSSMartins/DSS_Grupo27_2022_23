import java.util.ArrayList;

public class Circuito {
	public ArrayList<Corrida> _configura = new ArrayList<Corrida>();
	public ArrayList<Campeonato> _é_formado_por = new ArrayList<Campeonato>();
	public Administrador _adiciona;
	public ArrayList<Evento> _acontece = new ArrayList<Evento>();
	public ArrayList<Curva> _possui = new ArrayList<Curva>();
	public ArrayList<Volta> _é_constituído_por = new ArrayList<Volta>();
	public Nome _denominado;

	public int hashCode() {
		int lHashCode = 0;
		if ( this._configura != null ) {
			lHashCode += this._configura.hashCode();
		}
		if ( this._é_formado_por != null ) {
			lHashCode += this._é_formado_por.hashCode();
		}
		if ( this._adiciona != null ) {
			lHashCode += this._adiciona.hashCode();
		}
		if ( this._acontece != null ) {
			lHashCode += this._acontece.hashCode();
		}
		if ( this._possui != null ) {
			lHashCode += this._possui.hashCode();
		}
		if ( this._é_constituído_por != null ) {
			lHashCode += this._é_constituído_por.hashCode();
		}
		if ( this._denominado != null ) {
			lHashCode += this._denominado.hashCode();
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
			lEquals &= ((this._configura == lCircuitoObject._configura)
				|| (this._configura != null && this._configura.equals(lCircuitoObject._configura)));
			lEquals &= ((this._é_formado_por == lCircuitoObject._é_formado_por)
				|| (this._é_formado_por != null && this._é_formado_por.equals(lCircuitoObject._é_formado_por)));
			lEquals &= ((this._adiciona == lCircuitoObject._adiciona)
				|| (this._adiciona != null && this._adiciona.equals(lCircuitoObject._adiciona)));
			lEquals &= ((this._acontece == lCircuitoObject._acontece)
				|| (this._acontece != null && this._acontece.equals(lCircuitoObject._acontece)));
			lEquals &= ((this._possui == lCircuitoObject._possui)
				|| (this._possui != null && this._possui.equals(lCircuitoObject._possui)));
			lEquals &= ((this._é_constituído_por == lCircuitoObject._é_constituído_por)
				|| (this._é_constituído_por != null && this._é_constituído_por.equals(lCircuitoObject._é_constituído_por)));
			lEquals &= ((this._denominado == lCircuitoObject._denominado)
				|| (this._denominado != null && this._denominado.equals(lCircuitoObject._denominado)));
			return lEquals;
		}
		return false;
	}
	public class Circuito {
		private int _nVoltas;
		private String _nome;
		private double _comprimento;
		private int _nCurvas;
		private int _nChicanes;
		private int _nRetas;
		private ArrayList<Circuito.Secção> _secções = new ArrayList<Secção>();

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
	public class Secção {
		private int _gDU;
		private String _tipo;
		private int _id;

		public int hashCode() {
			int lHashCode = 0;
			if ( this._tipo != null ) {
				lHashCode += this._tipo.hashCode();
			}
			if ( lHashCode == 0 ) {
				lHashCode = super.hashCode();
			}
			return lHashCode;
		}

		public boolean equals(Object aObject) {
			if (this == aObject) {
				return true;
			} else if (aObject instanceof Secção) {
				Secção lSecçãoObject = (Secção) aObject;
				boolean lEquals = true;
				lEquals &= this._gDU == lSecçãoObject._gDU;
				lEquals &= ((this._tipo == lSecçãoObject._tipo)
					|| (this._tipo != null && this._tipo.equals(lSecçãoObject._tipo)));
				lEquals &= this._id == lSecçãoObject._id;
				return lEquals;
			}
			return false;
		}
	}
}