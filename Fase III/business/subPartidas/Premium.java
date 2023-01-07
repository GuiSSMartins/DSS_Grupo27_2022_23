package business.subPartidas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Iterator;

import business.subCatálogos.Carro;
import business.subCatálogos.Circuito;
import business.subCatálogos.GT;
import business.subCatálogos.GTHibrido;
import business.subCatálogos.Hibrido;
import business.subCatálogos.Piloto;
import business.subCatálogos.Seccao;

public class Premium extends Simulador {

	// create instance of Random class
	Random rand = new Random();

	public void calculaTempo() {
		throw new UnsupportedOperationException();
	}

	public int hashCode() {
		int lHashCode = 0;
		if (lHashCode == 0) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object aObject) {
		if (this == aObject) {
			return true;
		} else if (aObject instanceof Premium) {
			Premium lPremiumObject = (Premium) aObject;
			return this == lPremiumObject;
		}
		return false;
	}

	public List<Evento> calcularEventosPartida(Corrida aCorrida) {

		List<Evento> eventos = new ArrayList<Evento>();
		List<Progresso> primeiroVolta = new ArrayList<Progresso>();
		int voltas = aCorrida.getCircuito().getNVoltas();
		List<Seccao> seccoes = aCorrida.getCircuito().getseccoes();
		ArrayList<Progresso> aux = new ArrayList<Progresso>();
		for (Progresso c : aCorrida.getProgressos()) {
			aux.add(c.clone());
		}
		for (int i = 0; i < voltas; i++) {
			List<Evento> ev = this.checkEventosSeccao(aCorrida.getCircuito(), aux, i, s, aCorrida.getClima(), seccoes);
			primeiroVolta = this.primeiroVolta(i, aux, primeiroVolta);
		}
		return eventos;
	}

	public List<Evento> algoGeraAcidente(ArrayList<Progresso> aux, Progresso p, List<String> idsJogadoresEnvolvidos,
			int i, Map<Integer, List<Integer>> sofreuEventoNaSeccao) {
		List<Evento> res = new ArrayList<>();

		// percorre todos os progressos para ver quais são os carros que estão na mesma
		// secção
		for (Progresso prog : aux)
			if (!prog.equals(p))
				if (prog.getSeccao() == p.getSeccao()) {
					// carro está na mesma secção
					if (prog.getPiloto().getSVA() > 0.5) {
						// piloto agressivo

						// cria random entre 0 e 100
						int probAcidente = rand.nextInt(101);

						if (probAcidente > 80) {
							List<String> idsJogadoresEnvolvidosTemp = new ArrayList<>();
							for (String s : idsJogadoresEnvolvidos)
								idsJogadoresEnvolvidosTemp.add(String.valueOf(s));

							// adiciona o jogador à lista de jogadores envolvidos no evento acidente
							idsJogadoresEnvolvidosTemp.add(prog.getPiloto().getNome());

							// cria evento do tipo acidente
							res.add(new Evento(i, p.getSeccao(), 0, idsJogadoresEnvolvidosTemp));

							// adiciona o carro à lista de carros que sofreram evento nesta secção
							sofreuEventoNaSeccao.get(p.getSeccao()).add(p.getCarro().getID());
						}
					}

				}

		return res;
	}

	public List<Evento> ocorreuUltrapassagem(ArrayList<Progresso> aux, Progresso p,
			int i, Map<Integer, List<Integer>> sofreuEventoNaSeccao, List<Seccao> seccoes) {
		Piloto piloto = p.getPiloto();
		Carro c = p.getCarro();
		int seccao = p.getSeccao();

		List<Evento> res = new ArrayList<>();

		for (Progresso prog : aux)
			if (!prog.equals(p))
				if (prog.getSeccao() == p.getSeccao()) {
					if (piloto.getSVA() > prog.getPiloto().getSVA() && c.compararaCarros(prog.getCarro())
							&& (seccoes.get(seccao).probabilidadeCarroConsegueUltrapassar() * c.getPAC() > 0.8)) {
						// consegue ultrapassar

						// adiciona o jogador à lista de jogadores envolvidos no evento ultrapassar
						List<String> idsJogadoresEnvolvidos = new ArrayList<>();
						idsJogadoresEnvolvidos.add(p.getPiloto().getNome());

						// adiciona novo evento do tipo ultrapassagem
						res.add(new Evento(i, p.getSeccao(), 1, idsJogadoresEnvolvidos));

						// adiciona o carro à lista de carros que sofreram evento nesta secção
						sofreuEventoNaSeccao.get(seccao).add(c.getID());

						// adiciona o jogador à lista de jogadores envolvidos no evento ultrapassar
						List<String> idsJogadoresEnvolvidosTemp = new ArrayList<>();
						idsJogadoresEnvolvidosTemp.add(p.getPiloto().getNome());

						// adiciona o jogador à lista de jogadores envolvidos no evento ultrapassado
						idsJogadoresEnvolvidos.add(p.getPiloto().getNome());

						// adiciona novo evento do tipo ultrapassado
						res.add(new Evento(i, p.getSeccao(), 2, idsJogadoresEnvolvidosTemp));
					}
				}

		return res;
	}

	// devolve todos os eventos que ocorreram naquela volta
	private List<Evento> checkEventosSeccao(Circuito circuito, ArrayList<Progresso> aux, int i, int clima,
			List<Seccao> seccoes) {

		List<Evento> res = new ArrayList<>();

		// lista de ids dos carros que já sofreram algum evento na seccao
		Map<Integer, List<Integer>> sofreuEventoNaSeccao = new HashMap<>();

		// para todos os progressos
		for (Progresso p : aux) {
			int seccao = p.getSeccao();
			if (!sofreuEventoNaSeccao.containsKey(seccao))
				sofreuEventoNaSeccao.put(seccao, new ArrayList<>());
			Carro c = p.getCarro();

			if (!sofreuEventoNaSeccao.get(seccao).contains(c.getID())) {

				Piloto piloto = p.getPiloto();
				boolean evento = false;
				List<String> idsJogadoresEnvolvidos = new ArrayList<>();

				if (c instanceof GT)
					if (i * ((GT) c).getTaxaDeterioracao() >= 1) {
						// adiciona o jogador à lista de jogadores envolvidos no evento avaria
						idsJogadoresEnvolvidos.add(piloto.getNome());

						// adiciona novo evento do tipo avaria
						res.add(new Evento(i, seccao, 3, idsJogadoresEnvolvidos));

						// adiciona acidentes provocados por avaria
						List<Evento> acidentes = algoGeraAcidente(aux, p, idsJogadoresEnvolvidos, i,
								sofreuEventoNaSeccao);
						for (Evento e : acidentes)
							res.add(e.clone());

						// adiciona o carro à lista de carros que sofreram evento nesta secção
						sofreuEventoNaSeccao.get(seccao).add(c.getID());

						evento = true;

					} else if (c instanceof GTHibrido)
						if (i * ((GTHibrido) c).getTaxaDeterioracao() >= 1) {
							// adiciona o jogador à lista de jogadores envolvidos no evento avaria
							idsJogadoresEnvolvidos.add(p.getPiloto().getNome());

							// adiciona novo evento do tipo avaria
							res.add(new Evento(i, p.getSeccao(), 3));

							// adiciona acidentes provocados por avaria
							List<Evento> acidentes = algoGeraAcidente(aux, p, idsJogadoresEnvolvidos, i,
									sofreuEventoNaSeccao);
							for (Evento e : acidentes)
								res.add(e.clone());

							// adiciona o carro à lista de carros que sofreram evento nesta secção
							sofreuEventoNaSeccao.get(seccao).add(c.getID());

							evento = true;

						}

				if (!evento && ((clima == 0 && 1 - piloto.getCTS() > 0.8) || (clima == 1 && piloto.getCTS() > 0.8))) {
					// está sol e o piloto não sabe conduzir com sol

					// adiciona o jogador à lista de jogadores envolvidos no evento acidente
					idsJogadoresEnvolvidos.add(p.getPiloto().getNome());

					// adiciona novo evento do tipo acidente
					res.add(new Evento(i, p.getSeccao(), 0, idsJogadoresEnvolvidos));

					// adiciona acidentes provocados por acidente
					List<Evento> acidentes = algoGeraAcidente(aux, p, idsJogadoresEnvolvidos, i, sofreuEventoNaSeccao);
					for (Evento e : acidentes)
						res.add(e.clone());

					// adiciona o carro à lista de carros que sofreram evento nesta secção
					sofreuEventoNaSeccao.get(seccao).add(c.getID());

					evento = true;
				}

				if (!evento) {
					// pode ocorrer ultrapassagens
					List<Evento> acidentes = ocorreuUltrapassagem(aux, p, i, sofreuEventoNaSeccao, seccoes);
					for (Evento e : acidentes)
						res.add(e.clone());
				}

			}
		}

		return null;
	}

	/**
	 * Metodo auxiliar privado para determinar o carro que vai em 1o a cada volta
	 */
	private List<Progresso> primeiroVolta(int volta, List<Progresso> l, List<Progresso> primeiroVolta) {
		l.sort(null);
		Iterator<Progresso> it = l.iterator();
		boolean f = false;
		Progresso c = null;
		while (it.hasNext() && f == false) {
			c = it.next();
		}
		if (c != null)
			primeiroVolta.add(volta, c.clone());
		return primeiroVolta;
	}
}