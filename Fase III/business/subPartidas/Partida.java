package business.subPartidas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import business.subCatálogos.Campeonato;
import business.subCatálogos.Carro;
import business.subCatálogos.Circuito;
import business.subCatálogos.Piloto;

public class Partida {
	private List<String> resultadoFinal;
	private int totalAfinações;
	private List<Circuito> circuitos;
	private List<Corrida> corridas;
	private int idCorridaAtual;
	private Map<String, Estado> estados = new HashMap<String, Estado>();
	private Simulador simulador;

	public Partida(Campeonato c, int versaoJogo) {
		this.circuitos = new ArrayList<>();
		this.corridas = new ArrayList<>();
		for (Circuito s : c.getCircuitos()) {
			this.circuitos.add(s);
			this.corridas.add(new Corrida(versaoJogo, s));
		}
		this.totalAfinações = (this.circuitos.size() / 3) * 2;
		this.idCorridaAtual = 0;
		this.resultadoFinal = new ArrayList<>();
		if (versaoJogo == 0)
			this.simulador = new Base();
		else if (versaoJogo == 1)
			this.simulador = new Premium();
	}

	public Map<String, Integer> getClimas() {
		Map<String, Integer> climas = new HashMap<>();
		for (Corrida corrida : this.corridas) {
			climas.put(corrida.getCircuito().getNome(), corrida.getClima());
		}
		return climas;
	}

	public void iniciarCorrida() {
		Corrida corrida = this.corridas.get(this.idCorridaAtual);
		corrida.iniciarProgressos(this.estados);
		// System.out.println(corrida.totalProgressos());
		corrida.simulaCorrida(this.simulador);
	}

	public void adicionaJogador(String aIdJogador, Piloto aPiloto, Carro aCarro) {
		Estado estado = new Estado();
		estado.setCarro(aCarro);
		estado.setPiloto(aPiloto);
		this.estados.put(aIdJogador, estado);
	}

	public boolean verificaDisponibilidadeAfinacoesPartida(String aIdJogador) {
		return this.estados.get(aIdJogador).getNAfinacoes() < this.totalAfinações;
	}

	public void aplicarNovaAfinacaoPartida(String aIdJogador, double aAfinacao) {
		this.estados.get(aIdJogador).setNAfinacoes(this.estados.get(aIdJogador).getNAfinacoes() + 1);
		this.estados.get(aIdJogador).setDownforce(totalAfinações);
	}

	public void registaConfiguracaoCorridaPartida(int aTipoPneus, int aModoMotor, String aIdJogador) {
		this.estados.get(aIdJogador).setTipoPneus(aTipoPneus);
		this.estados.get(aIdJogador).setModoMotor(aModoMotor);
	}

	public List<String> calculaResultadoPartida() {
		List<String> lKeys = new ArrayList<String>(this.estados.keySet());

		Collections.sort(lKeys, new Comparator<String>() {
			@Override
			public int compare(String aKey1, String aKey2) {
				return estados.get(aKey2).getPontuacao() - estados.get(aKey1).getPontuacao();
			}
		});
		return lKeys;
	}

	public String calculaResultadoCorrida() {
		Corrida corrida = corridas.get(this.idCorridaAtual);
		String resultados = corrida.printResultados();

		// Atualizar os estados por cada corrida
		Set<Progresso> progressos = corrida.getResultados();
		int[] pontuacao = { 12, 10, 8, 7, 6, 5, 4, 3, 2, 1 };
		int i = 0;
		for (Progresso p : progressos) {
			String email = p.getEmail();
			this.estados.get(email).adicionarPontuacao(pontuacao[i]);
			i++;
		}

		this.idCorridaAtual++;
		return resultados;
	}

	public int getNCorridas() {
		return this.corridas.size();
	}

	public void setSimulador(int versaoJogo) { // 1 - Base; 2 - Premium
		if (versaoJogo == 1)
			this.simulador = new Base();
		else
			this.simulador = new Premium();
	}

	public Simulador getSimulador() {
		return this.simulador;
	}

	public boolean equals(Object aObject) {
		if (this == aObject) {
			return true;
		} else if (aObject instanceof Partida) {
			Partida lPartidaObject = (Partida) aObject;
			boolean lEquals = true;
			lEquals &= this.resultadoFinal == lPartidaObject.resultadoFinal;
			lEquals &= this.totalAfinações == lPartidaObject.totalAfinações;
			lEquals &= ((this.corridas == lPartidaObject.corridas)
					|| (this.corridas != null && this.corridas.equals(lPartidaObject.corridas)));
			lEquals &= ((this.estados == lPartidaObject.estados)
					|| (this.estados != null && this.estados.equals(lPartidaObject.estados)));
			return lEquals;
		}
		return false;
	}

	public int getPontuacaoJogador(String email) {
		return this.estados.get(email).getPontuacao();
	}
}