package Diagrama_de_Classes_com_Packages.Partidas;

public interface IGestPartidas {

	public void registarConfiguracao(Campeonato aCampeonato, String aIdJogador);

	public boolean entrarNaPartida(String aIdPartida, String aIdJogador, Piloto aPiloto, Carro aCarro);

	public void iniciarPartida();

	public void iniciarCorrida();

	public void atualizaEstados(List<String> aResultados);

	public void finalizarPartida();

	public List<Evento> getEventos(String aIdPartida);

	public List<String> calculaPosicao();

	public void calcularFiabilidade();

	public List<String> finalizarCorrida();

	public boolean verificaDisponibilidadeAfinacoes(String aIdPartida, String aIdJogador);

	public void aplicarNovaAfinacao(String aIdPartida, String aIdJogador, double aAfinacao);

	public void registaConfiguracaoCorrida(String aIdPartida, int aTipoPneus, int aModoMotor, String aIdJogador);

	public void calcularEventos(String aIdPartida, Corrida aCorrida);

	public int hashCode();

	public boolean equals(Object aObject);
}