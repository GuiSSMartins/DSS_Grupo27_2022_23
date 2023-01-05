package business.subPartidas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


	public Partida(Campeonato c) {
		for (Circuito s : c.getCircuitos()){
			this.circuitos.add(s);
		}
		this.totalAfinações = (this.circuitos.size()/3)*2; 
		this.corridas = new ArrayList<Corrida>();
		this.estados = new HashMap<String,Estado>();
		this.idCorridaAtual = 0;
		this.resultadoFinal = new ArrayList<String>();
	}

	public Map<String, String> getClimas() {
		Map<String,String> climas = new HashMap<>();
		for (Corrida corrida : this.corridas) {

		}
		return climas;
	}

	public void iniciarCorrida() {
		Corrida corrida = this.corridas.get(this.idCorridaAtual);
		corrida.simulaCorrida();
	}
	
	public void adicionaJogador(String aIdJogador, Piloto aPiloto, Carro aCarro) {
		this.estados.put(aIdJogador, new Estado());
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
}