package business.subPartidas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import business.SubUtilizadores.Jogador;
import business.subCatálogos.C2Hibrido;
import business.subCatálogos.Campeonato;
import business.subCatálogos.Carro;
import business.subCatálogos.GT;
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
		if (this.partida != null) {
			lHashCode += this.partida.hashCode();
		}
		if (lHashCode == 0) {
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
		for (int i = 0; i < n_corridas; i++) {
			this.iniciarCorrida();
			String resultados_corrida = finalizarCorrida();
			System.out.println(resultados_corrida);
			System.out.println("\n");
		}
	}

	@Override
	public void iniciarCorrida() {
		this.partida.iniciarCorrida();
	}

	@Override
	public void atualizaEstados(List<String> resultados) {
		// no final de cada corrida, deve-se atualizar os estados dos vários jogadores
	}

	@Override
	public List<String> finalizarPartida() {
		List<String> resultados = this.partida.calculaResultadoPartida(); // resultados finais
		int pontuacao = this.partida.getPontuacaoJogador(jogador.getEmail());
		this.jogador.registaPontuacao(this.campeonato.getNome(), pontuacao);
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
	public void entrarNaPartida(Jogador jogador, int versaoJogo, Piloto piloto, Carro carro) {
		this.jogador = jogador;
		this.partida.adicionaJogador(jogador.getEmail(), piloto, carro);
	}

	@Override
	public void registarConfiguracao(Campeonato campeonato, int versaoJogo) {
		this.campeonato = campeonato;
		this.partida = new Partida(this.campeonato, versaoJogo);
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

	public void criarBots() {
		// Jogadores temporários

		// 1º Bot - Normal
		// Configuar piloto e carro demos
		Piloto pb1 = new Piloto("bot1", 0.5, 0.6);
		Carro cb1 = new GT(11,"marcabot1","modelobot1",3000,100,60,0.3,0.03);
		Jogador bot1 = new Jogador("bot1", "", "", 2);
		this.bots.add(bot1);
		this.partida.adicionaJogador("bot1", pb1, cb1);

		// 2º Bot - Híbrido
		// Configuarar piloto e carro demos
		Piloto pb2 = new Piloto("bot2", 0.5, 0.6);
		Carro cb2 = new C2Hibrido(10, "marcabot2", "modelobot2", 4000, 100, 50, 0.6, 100);
		Jogador bot2 = new Jogador("bot2", "", "", 2);
		this.bots.add(bot2);
		this.partida.adicionaJogador("bot2", pb2, cb2);
	}

}