package business.subPartidas;

import java.util.List;

import business.subCatálogos.Campeonato;
import business.subCatálogos.Carro;
import business.subCatálogos.Piloto;

public interface ISubPartidas {
	/**
	 * 
	 * @param campeonato
	 * @param idJogador
	 */
	void registarConfiguracao(Campeonato campeonato);

	/**
	 * 
	 * @param idPartida
	 * @param idJogador
	 * @param piloto
	 * @param carro
	 */
	void entrarNaPartida(String idJogador, int versaoJogo, Piloto piloto, Carro carro);

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