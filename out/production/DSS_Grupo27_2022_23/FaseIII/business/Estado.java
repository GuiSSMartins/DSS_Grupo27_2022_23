package Diagrama_de_Classes_com_Packages.Partidas;

import Diagrama_de_Classes___Partidas.SubSistemaCatálogo___carro;

public class Estado {
	private int _nAfinações;
	private int _posição;
	private int _tipoPneus;
	private int _modoMotor;
	private int _downforce;
	private int _pontuacao;
	public SubSistemaCatálogo___carro _carro;
	private Piloto _piloto;

	public boolean haAfinacoesDisponiveis() {
		throw new UnsupportedOperationException();
	}

	public void decnAfinacoes() {
		throw new UnsupportedOperationException();
	}

	public void configuraEstadoJogador(int aTipoPneus, int aModoMotor) {
		throw new UnsupportedOperationException();
	}

	public int hashCode() {
		int lHashCode = 0;
		if ( this._carro != null ) {
			lHashCode += this._carro.hashCode();
		}
		if ( this._piloto != null ) {
			lHashCode += this._piloto.hashCode();
		}
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object aObject) {
		if (this == aObject) {
			return true;
		} else if (aObject instanceof Estado) {
			Estado lEstadoObject = (Estado) aObject;
			boolean lEquals = true;
			lEquals &= this._nAfinações == lEstadoObject._nAfinações;
			lEquals &= this._posição == lEstadoObject._posição;
			lEquals &= this._tipoPneus == lEstadoObject._tipoPneus;
			lEquals &= this._modoMotor == lEstadoObject._modoMotor;
			lEquals &= this._downforce == lEstadoObject._downforce;
			lEquals &= this._pontuacao == lEstadoObject._pontuacao;
			lEquals &= ((this._carro == lEstadoObject._carro)
				|| (this._carro != null && this._carro.equals(lEstadoObject._carro)));
			lEquals &= ((this._piloto == lEstadoObject._piloto)
				|| (this._piloto != null && this._piloto.equals(lEstadoObject._piloto)));
			return lEquals;
		}
		return false;
	}
}