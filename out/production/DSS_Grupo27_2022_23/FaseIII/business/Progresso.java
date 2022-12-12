package Diagrama_de_Classes_com_Packages.Partidas;

public class Progresso {
	private Piloto _piloto;
	private Carro _carro;
	private double _tempo;
	private int _seccao;

	public void update(Evento aE, String aIdJogador) {
		throw new UnsupportedOperationException();
	}

	public int hashCode() {
		int lHashCode = 0;
		if ( this._piloto != null ) {
			lHashCode += this._piloto.hashCode();
		}
		if ( this._carro != null ) {
			lHashCode += this._carro.hashCode();
		}
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object aObject) {
		if (this == aObject) {
			return true;
		} else if (aObject instanceof Progresso) {
			Progresso lProgressoObject = (Progresso) aObject;
			boolean lEquals = true;
			lEquals &= ((this._piloto == lProgressoObject._piloto)
				|| (this._piloto != null && this._piloto.equals(lProgressoObject._piloto)));
			lEquals &= ((this._carro == lProgressoObject._carro)
				|| (this._carro != null && this._carro.equals(lProgressoObject._carro)));
			lEquals &= this._tempo == lProgressoObject._tempo;
			lEquals &= this._seccao == lProgressoObject._seccao;
			return lEquals;
		}
		return false;
	}
}