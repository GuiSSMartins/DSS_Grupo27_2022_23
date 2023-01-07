package business.subPartidas;

import java.util.List;
import business.SubUtilizadores.Jogador;
import business.subCatálogos.Campeonato;
import business.subCatálogos.Carro;
import business.subCatálogos.Piloto;

public interface ISubPartidas {
	/**
	 * 
	 * @param campeonato
	 * @param versaoJogo
	 */
	void registarConfiguracao(Campeonato campeonato, int versaoJogo);

	/**
	 * 
	 * @param idPartida
	 * @param idJogador
	 * @param piloto
	 * @param carro
	 */
	void entrarNaPartida(Jogador jogador, int versaoJogo, Piloto piloto, Carro carro);

	void iniciarPartida();

	void iniciarCorrida();

	/**
	 * 
	 * @param resultados
	 */
	void atualizaEstados(List<String> resultados);

	List<String> finalizarPartida();

	List<Evento> getEventos();

	List<String> calculaPosicao();

	void calcularFiabilidade();

	String finalizarCorrida();

	/**
	 * 
	 * @param idPartida
	 * @param idJogador
	 */
	boolean verificaDisponibilidadeAfinacoes(String idJogador);
}