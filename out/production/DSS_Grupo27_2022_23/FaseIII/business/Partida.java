package Diagrama_de_Classes_com_Packages.Partidas;

import Diagrama_de_Classes_com_Packages.Catálogo.Campeonato;
import java.util.ArrayList;
import Diagrama_de_Classes___Partidas.SubSistemaCatálogo____campeonato;

public class Partida {
	private List<String> _resultadoFinal;
	private int _totalAfinações;
	private Campeonato _campeonato;
	private ArrayList<Corrida> _corridas = new ArrayList<Corrida>();
	private Estado _estados;
	private Simulador _simulador;
	public SubSistemaCatálogo____campeonato _campeonato;

	public boolean verificaDisponibilidadeAfinacoesPartida(String aIdJogador) {
		throw new UnsupportedOperationException();
	}

	public void aplicarNovaAfinacaoPartida(String aIdJogador, double aAfinacao) {
		throw new UnsupportedOperationException();
	}

	public void registaConfiguracaoCorridaPartida(int aTipoPneus, int aModoMotor, String aIdJogador) {
		throw new UnsupportedOperationException();
	}

	public List<String> calculaResultadoPartida() {
		throw new UnsupportedOperationException();
	}

	public int hashCode() {
		int lHashCode = 0;
		if ( this._campeonato != null ) {
			lHashCode += this._campeonato.hashCode();
		}
		if ( this._corridas != null ) {
			lHashCode += this._corridas.hashCode();
		}
		if ( this._estados != null ) {
			lHashCode += this._estados.hashCode();
		}
		if ( this._simulador != null ) {
			lHashCode += this._simulador.hashCode();
		}
		if ( this._campeonato != null ) {
			lHashCode += this._campeonato.hashCode();
		}
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object aObject) {
		if (this == aObject) {
			return true;
		} else if (aObject instanceof Partida) {
			Partida lPartidaObject = (Partida) aObject;
			boolean lEquals = true;
			lEquals &= this._resultadoFinal == lPartidaObject._resultadoFinal;
			lEquals &= this._totalAfinações == lPartidaObject._totalAfinações;
			lEquals &= ((this._campeonato == lPartidaObject._campeonato)
				|| (this._campeonato != null && this._campeonato.equals(lPartidaObject._campeonato)));
			lEquals &= ((this._corridas == lPartidaObject._corridas)
				|| (this._corridas != null && this._corridas.equals(lPartidaObject._corridas)));
			lEquals &= ((this._estados == lPartidaObject._estados)
				|| (this._estados != null && this._estados.equals(lPartidaObject._estados)));
			lEquals &= ((this._simulador == lPartidaObject._simulador)
				|| (this._simulador != null && this._simulador.equals(lPartidaObject._simulador)));
			lEquals &= ((this._campeonato == lPartidaObject._campeonato)
				|| (this._campeonato != null && this._campeonato.equals(lPartidaObject._campeonato)));
			return lEquals;
		}
		return false;
	}
}