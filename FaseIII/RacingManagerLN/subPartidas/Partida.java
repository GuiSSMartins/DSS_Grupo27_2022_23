package DSS_Grupo27_2022_23.FaseIII.RacingManagerLN.subPartidas;

import java.util.ArrayList;
import java.util.List;


public class Partida {
	private List<String> _resultadoFinal;
	private int _totalAfinações;
	// private Campeonato _campeonato;
	private ArrayList<Corrida> _corridas = new ArrayList<Corrida>();
	private Estado _estados;
	private Simulador _simulador;

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


	public boolean equals(Object aObject) {
		if (this == aObject) {
			return true;
		} else if (aObject instanceof Partida) {
			Partida lPartidaObject = (Partida) aObject;
			boolean lEquals = true;
			lEquals &= this._resultadoFinal == lPartidaObject._resultadoFinal;
			lEquals &= this._totalAfinações == lPartidaObject._totalAfinações;
			lEquals &= ((this._corridas == lPartidaObject._corridas)
				|| (this._corridas != null && this._corridas.equals(lPartidaObject._corridas)));
			lEquals &= ((this._estados == lPartidaObject._estados)
				|| (this._estados != null && this._estados.equals(lPartidaObject._estados)));
			lEquals &= ((this._simulador == lPartidaObject._simulador)
				|| (this._simulador != null && this._simulador.equals(lPartidaObject._simulador)));
			return lEquals;
		}
		return false;
	}
}