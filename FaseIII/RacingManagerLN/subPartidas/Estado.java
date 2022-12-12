package DSS_Grupo27_2022_23.FaseIII.RacingManagerLN.subPartidas;

import DSS_Grupo27_2022_23.FaseIII.RacingManagerLN.subCatálogos.Piloto;

public class Estado {
	private int nAfinações;
	private int posição;
	private int tipoPneus;
	private int modoMotor;
	private int downforce;
	private int pontuacao;
	// public SubSistemaCatálogo___carro carro;
	private Piloto piloto;

	public boolean haAfinacoesDisponiveis() {
		throw new UnsupportedOperationException();
	}

	public void decnAfinacoes() {
		throw new UnsupportedOperationException();
	}

	public void configuraEstadoJogador(int aTipoPneus, int aModoMotor) {
		throw new UnsupportedOperationException();
	}

	public boolean equals(Object aObject) {
		if (this == aObject) {
			return true;
		} else if (aObject instanceof Estado) {
			Estado lEstadoObject = (Estado) aObject;
			boolean lEquals = true;
			lEquals &= this.nAfinações == lEstadoObject.nAfinações;
			lEquals &= this.posição == lEstadoObject.posição;
			lEquals &= this.tipoPneus == lEstadoObject.tipoPneus;
			lEquals &= this.modoMotor == lEstadoObject.modoMotor;
			lEquals &= this.downforce == lEstadoObject.downforce;
			lEquals &= this.pontuacao == lEstadoObject.pontuacao;
			return lEquals;
		}
		return false;
	}
}