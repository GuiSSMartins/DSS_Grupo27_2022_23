package business.subPartidas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import business.SubUtilizadores.Jogador;
import business.subCatálogos.Campeonato;
import business.subCatálogos.Carro;
import business.subCatálogos.Piloto;

public class SSPartidas implements ISubPartidas {
	private Partida partida; // partida mais recente
	private Campeonato campeonato;
	private Jogador jogador; // o id é sempre 1 // "Utilizador"
	private List<Jogador> bots; // id começar em 2
	private int n_corridas;
	// Os estados estão na partida

	public SSPartidas() {
		this.bots = new ArrayList<>();
	}

	public int hashCode() {
		int lHashCode = 0;
		if ( this.partida != null ) {
			lHashCode += this.partida.hashCode();
		}
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object aObject) {
		if (this == aObject) {
			return true;
		} else if (aObject instanceof SSPartidas) {
			SSPartidas lSSPartidasObject = (SSPartidas) aObject;
			boolean lEquals = true;
			lEquals &= ((this.partida == lSSPartidasObject.partida)
				|| (this.partida != null && this.partida.equals(lSSPartidasObject.partida)));
			return lEquals;
		}
		return false;
	}


	@Override
	public void iniciarPartida() {

		this.n_corridas = this.partida.getNCorridas();

		// iniciar as várias corridas
		for (int i=0; i<n_corridas; i++) {
			iniciarCorrida();
			String resultados_corrida = finalizarCorrida();
			System.out.println(resultados_corrida);
		}
	}

	@Override
	public void iniciarCorrida() {
		this.partida.iniciarCorrida();
	}

	@Override
	public void atualizaEstados(List<String> resultados) {
		// no final de cada partida, deve-se atualizar os estados dos vários jogadores
	}

	@Override
	public List<String> finalizarPartida() {
		List<String> resultados = this.partida.calculaResultadoPartida(); // resultados finais
		atualizaEstados(resultados);
		this.partida = null;
		return resultados;
	}

	@Override
	public List<String> calculaPosicao() {
		// não é preciso
		return null;
	}

	@Override
	public void calcularFiabilidade() {
		// não é preciso
	}

	@Override
	public String finalizarCorrida() {
		String resultados = this.partida.calculaResultadoCorrida(); // resultados finais
		return resultados;
	}

	@Override
	public void entrarNaPartida(String idJogador, int versaoJogo, Piloto piloto, Carro carro) {
		if (idJogador.equals("Utilizador")) {
			this.jogador.setVersaoJogo(versaoJogo);
			this.partida.adicionaJogador(idJogador, piloto, carro);
		}
		else { // é um bot
			String idBot = "BOT1";
			// Criar Piloto

			// Criar Carro
		}
	}

	@Override
	public void registarConfiguracao(Campeonato campeonato) {
		this.campeonato = campeonato;
		this.partida = new Partida(this.campeonato);
	}

	@Override
	public List<Evento> getEventos() {
		return null; // não é preciso fazer
	}

	@Override
	public boolean verificaDisponibilidadeAfinacoes(String idJogador) {
		return false; // não é preciso fazer
	}

	public Map<String, Integer> clima() { // Clima
		return this.partida.getClimas();
	}

}