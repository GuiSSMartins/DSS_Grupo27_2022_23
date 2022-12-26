package business.subPartidas;

import business.subCatálogos.Carro;
import business.subCatálogos.Piloto;

public class Estado {
	private int nAfinacoes;
	private int posicao;
	private int tipoPneus;
	private int modoMotor;
	private int downforce;
	private int pontuacao;
	private Piloto piloto;
	private Carro carro;

	public Carro getCarro() {
		return this.carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public int getNAfinacoes() {
		return this.nAfinacoes;
	}

	public void setNAfinacoes(int nAfinações) {
		this.nAfinacoes = nAfinações;
	}

	public int getPosicao() {
		return this.posicao;
	}

	public void setPosiçãO(int posição) {
		this.posicao = posição;
	}

	public int getTipoPneus() {
		return this.tipoPneus;
	}

	public void setTipoPneus(int tipoPneus) {
		this.tipoPneus = tipoPneus;
	}

	public int getModoMotor() {
		return this.modoMotor;
	}

	public void setModoMotor(int modoMotor) {
		this.modoMotor = modoMotor;
	}

	public int getDownforce() {
		return this.downforce;
	}

	public void setDownforce(int downforce) {
		this.downforce = downforce;
	}

	public int getPontuacao() {
		return this.pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}

	public Piloto getPiloto() {
		return this.piloto;
	}

	public void setPiloto(Piloto piloto) {
		this.piloto = piloto;
	}

	// public SubSistemaCatálogo___carro carro;

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
			lEquals &= this.nAfinacoes == lEstadoObject.nAfinacoes;
			lEquals &= this.posicao == lEstadoObject.posicao;
			lEquals &= this.tipoPneus == lEstadoObject.tipoPneus;
			lEquals &= this.modoMotor == lEstadoObject.modoMotor;
			lEquals &= this.downforce == lEstadoObject.downforce;
			lEquals &= this.pontuacao == lEstadoObject.pontuacao;
			return lEquals;
		}
		return false;
	}

}