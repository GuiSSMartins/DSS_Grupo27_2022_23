package business.subPartidas;

import java.util.List;
import java.util.Map;

import business.SubUtilizadores.Jogador;
import business.subCatálogos.Campeonato;
import business.subCatálogos.Carro;
import business.subCatálogos.Piloto;

public class SSPartidas implements ISubPartidas {
	private Partida partida; // partida mais recente
	private Campeonato campeonato;
	private Jogador jogador; // o id é sempre 1
	private int n_bots;
	private List<Jogador> bots; // id começar em 2
	private int idPartida;
	private int n_corridas;
	private Simulador simulador;
	private Map<Integer, Estado> estados; // cada indice do jogador/bot, corresponde ao seu estado

	public SSPartidas() {
		this.idPartida = 0;
		this.n_bots = 0;

		// Criação de Jogado dummy
		// this.jogador = new Jogador();
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
		this.idPartida++;
		this.partida = new Partida(this.campeonato);
		// iniciar as várias corridas
		for (int i=0; i<n_corridas; i++) {
			iniciarCorrida();

			finalizarCorrida();
		}
	}

	@Override
	public void iniciarCorrida() {
		this.partida.iniciarCorrida();
	}

	@Override
	public void atualizaEstados(List<String> resultados) {
		// no final de cada corrida, dev-se atualizar os estados dos vários jogadores
		
	}

	@Override
	public List<String> finalizarPartida() {
		List<String> resultados = this.partida.calculaResultadoPartida(); // resultados finais
		this.partida = null;

		return resultados;
	}

	@Override
	public List<String> calculaPosicao() {
		// Devem-se calcular as posições no final de cada corrida
		return null;
	}

	@Override
	public void calcularFiabilidade() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> finalizarCorrida() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void entrarNaPartida(String idJogador, Piloto piloto, Carro carro) {
		
	}

	@Override
	public void registarConfiguracao(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	@Override
	public List<Evento> getEventos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean verificaDisponibilidadeAfinacoes(String idPartida, String idJogador) {
		// TODO Auto-generated method stub
		return false;
	}

	public Map<String, String> clima() { // Clima
		return this.partida.getClimas();
	}
}