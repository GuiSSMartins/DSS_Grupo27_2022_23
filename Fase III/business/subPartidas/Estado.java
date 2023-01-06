package business.subPartidas;

import business.subCatálogos.Carro;
import business.subCatálogos.Piloto;

public class Estado {
	private int nAfinacoes;
	private int posicao;
	private int tipoPneus; // 1 - macio, 2 - duro e 3 - chuva
	private int modoMotor;
	private int downforce;
	private int pontuacao;
	private Piloto piloto;
	private Carro carro;

	public Estado() {
		this.pontuacao = 0; // inicialmente, o jogador não possui pontuação
	}

	public Estado(int nAfinacoes, int posicao, int tipoPneus, int modoMotor, int downforce, Piloto piloto, Carro carro) {
		this.nAfinacoes = nAfinacoes;
		this.posicao = posicao;
		this.tipoPneus = tipoPneus;
		this.modoMotor = modoMotor;
		this.downforce = downforce;
		this.pontuacao = 0; // inicialmente, o jogador não possui pontuação
		this.piloto = piloto;
		this.carro = carro;
	}

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
		if (this.nAfinacoes > 0) return true;
		return false;
	}

	public void decnAfinacoes() {
		this.nAfinacoes--;
	}

	public void configuraEstadoJogador(int aTipoPneus, int aModoMotor) {
		this.tipoPneus = aTipoPneus;
		this.modoMotor = aModoMotor;
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