package business.subPartidas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import business.subCatálogos.Carro;
import business.subCatálogos.Circuito;
import business.subCatálogos.GT;
import business.subCatálogos.GTHibrido;
import business.subCatálogos.Piloto;
import business.subCatálogos.Seccao;

public class Base extends Simulador {

	// create instance of Random class
	Random rand = new Random();

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
		} else if (aObject instanceof Base) {
			Base lBaseObject = (Base) aObject;
			return this == lBaseObject;
			// ajuda deus
		}
		return false;
	}

	public List<Evento> calcularEventosPartida(Corrida aCorrida) {

		List<Evento> eventos = new ArrayList<Evento>();
		int voltas = aCorrida.getCircuito().getNVoltas();
		List<Seccao> seccoes = aCorrida.getCircuito().getseccoes();
		seccoes.sort((s1, s2) -> s1.getPosicao() - s2.getPosicao());
		List<Progresso> primeiroVolta = new ArrayList<>();
		int nSeccoes = seccoes.size();
		ArrayList<Progresso> aux = new ArrayList<Progresso>();
		for (Progresso c : aCorrida.getProgressos()) {
			c.setSeccao(0);
			aux.add(c);
		}

		List<Progresso> desistentes = new ArrayList<>();
		for (int i = 0; i < voltas; i++) {

			List<Evento> etv = new ArrayList<>();
			for (int j = 0; j < nSeccoes; j++) {

				List<Evento> ev = this.checkEventosSeccao(aCorrida.getCircuito(), aux, i, aCorrida.getClima(),
						seccoes.get(j));

				if (ev.size() > 0) {

					// percorre a lista de eventos para determinar se houve algum desistente por
					// avaria ou acidente
					for (Evento e : ev) {
						etv.add(e.clone());
						if (e.getAcontecimento() == 0 || e.getAcontecimento() == 3) {
							// vai a todos os jogadores envolvidos
							for (String piloto : e.getIdJogadoresEnvolvidos()) {
								// procura pelo respetivo progresso
								List<Progresso> morreu = new ArrayList<>();
								if (aux.size() > 0) {
									for (Progresso p : aux) {
										if (piloto.equals(p.getPiloto().getNome())) {
											// desistiu
											// adiciona à lista de desistentes
											desistentes.add(p.clone());
											// remove da lista de jogadores para a próxima volta
											morreu.add(p);
											p.getCarro().setDNF(true);

										}
									}
								}
								aux.removeAll(morreu);
							}
						}

						// adiciona o evento à lista geral de eventos
						eventos.add(e.clone());
					}
				}

				for (Progresso p : aux) {
					if (!desistentes.contains(p)) {
						Double novo_tempo = p.getCarro().tempoProximaSeccao(seccoes.get(j), aCorrida.getClima(),
								p.getPiloto());
						Double tempo_progresso = p.getTempo() + novo_tempo;
						p.setTempo(tempo_progresso);
						if (nSeccoes == j)
							p.setSeccao(0);
						else
							p.setSeccao(j + 1);
					}
				}

			}

			System.out.println("\n ---- EVENTOS VOLTA " + i + "  ------");
			for (Evento e : etv) {
				System.out.println(e);
			}
			System.out.println("------EVENTOS-------\n");
			System.out.println("Volta " + i + " :");
			for (Progresso p : aux) {
				System.out.println("Piloto: " + p.getPiloto().getNome() + "; Tempo: "
						+ TimeConverter.toTimeFormat(Double.valueOf(p.getTempo()).longValue()));
			}
			// aumenta o numero de voltas dadas no progresso
			for (Progresso p : aux)
				if (!desistentes.contains(p)) {
					p.setVolta(i + 1);
					p.setSeccao(0);
				}
			primeiroVolta = this.primeiroVolta(aux, primeiroVolta);
		}

		aCorrida.setPrimeiroVolta(primeiroVolta);
		return eventos;
	}

	public List<Evento> algoGeraAcidente(List<Progresso> aux, Progresso p, List<String> idsJogadoresEnvolvidos,
			List<Integer> sofreuEventoNaSeccao, int i) {
		List<Evento> res = new ArrayList<>();

		// percorre todos os progressos na seccao
		for (Progresso prog : aux)
			if (!prog.equals(p))
				if (prog.getPiloto().getSVA() > 0.5) {
					// piloto agressivo

					// cria random entre 0 e 100
					int probAcidente = rand.nextInt(101);

					if (probAcidente > 80 && !sofreuEventoNaSeccao.contains(prog.getCarro().getID())) {

						// adiciona o jogador à lista de jogadores envolvidos no evento acidente
						idsJogadoresEnvolvidos.add(prog.getPiloto().getNome());

						prog.getCarro().setDNF(true);

						// cria evento do tipo acidente
						res.add(new Evento(i, p.getSeccao(), 0, idsJogadoresEnvolvidos));

						// adiciona o carro à lista de carros que sofreram evento nesta secção
						sofreuEventoNaSeccao.add(p.getCarro().getID());

						p.getCarro().setDNF(true);
					}
				}

		return res;
	}

	public List<Evento> ocorreuUltrapassagem(List<Progresso> aux, Progresso p, List<Integer> sofreuEventoNaSeccao,
			int i, Seccao seccao) {

		Piloto piloto = p.getPiloto();
		Carro c = p.getCarro();

		List<Evento> res = new ArrayList<>();

		for (Progresso prog : aux)
			if (!prog.equals(p)) {

				if (p.getTempo() - prog.getTempo() < 0 && p.getTempo() - prog.getTempo() > -5000 &&
						(seccao.probabilidadeCarroConsegueUltrapassar() / (1 - c.getPAC()) > 0.5) &&
						!(sofreuEventoNaSeccao.contains(prog.getCarro().getID()))) {
					// consegue ultrapassar

					sofreuEventoNaSeccao.add(prog.getCarro().getID());

					// adiciona o jogador à lista de jogadores envolvidos no evento ultrapassar
					List<String> idsJogadoresEnvolvidos = new ArrayList<>();
					idsJogadoresEnvolvidos.add(prog.getPiloto().getNome());

					// adiciona novo evento do tipo ultrapassagem

					// adiciona o carro à lista de carros que sofreram evento nesta secção
					sofreuEventoNaSeccao.add(c.getID());

					// adiciona o jogador à lista de jogadores envolvidos no evento ultrapassar
					List<String> idsJogadoresEnvolvidosTemp = new ArrayList<>();
					idsJogadoresEnvolvidosTemp.add(p.getPiloto().getNome());
					idsJogadoresEnvolvidosTemp.add(prog.getPiloto().getNome());

					// adiciona o jogador à lista de jogadores envolvidos no evento ultrapassado
					idsJogadoresEnvolvidos.add(p.getPiloto().getNome());

					// adiciona novo evento do tipo ultrapassado
					res.add(new Evento(i, p.getSeccao(), 1, idsJogadoresEnvolvidos));
					res.add(new Evento(i, p.getSeccao(), 2, idsJogadoresEnvolvidosTemp));
					p.setTempo(prog.getTempo() + 5000);
				}
			}

		return res;
	}

	// devolve todos os eventos que ocorreram naquela volta
	private List<Evento> checkEventosSeccao(Circuito circuito, ArrayList<Progresso> aux, int i, int clima,
			Seccao seccaoATestar) {

		List<Evento> res = new ArrayList<>();

		List<Progresso> progressosNestaSeccao = new ArrayList<>();

		// lista de ids dos carros que já sofreram algum evento nesta seccao
		List<Integer> sofreuEventoNaSeccao = new ArrayList<>();

		int seccao = seccaoATestar.getPosicao();
		// pego nos progressos que estão nesta seccao
		for (Progresso p : aux) {
			if (p.getSeccao() == seccao)
				progressosNestaSeccao.add(p);
		}

		// para todos os progressos nesta seccao
		for (Progresso p : progressosNestaSeccao) {

			Carro c = p.getCarro();

			if (!sofreuEventoNaSeccao.contains(c.getID())) {

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
						List<Evento> acidentes = algoGeraAcidente(progressosNestaSeccao, p, idsJogadoresEnvolvidos,
								sofreuEventoNaSeccao, i);
						if (acidentes != null)
							for (Evento e : acidentes)
								res.add(e.clone());

						// adiciona o carro à lista de carros que sofreram evento nesta secção
						sofreuEventoNaSeccao.add(c.getID());

						evento = true;

					} else if (c instanceof GTHibrido)
						if (i * ((GTHibrido) c).getTaxaDeterioracao() >= 1) {
							// adiciona o jogador à lista de jogadores envolvidos no evento avaria
							idsJogadoresEnvolvidos.add(p.getPiloto().getNome());

							// adiciona novo evento do tipo avaria
							res.add(new Evento(i, p.getSeccao(), 3));

							// adiciona acidentes provocados por avaria
							List<Evento> acidentes = algoGeraAcidente(progressosNestaSeccao, p, idsJogadoresEnvolvidos,
									sofreuEventoNaSeccao, i);
							if (acidentes != null)
								for (Evento e : acidentes)
									res.add(e.clone());

							// adiciona o carro à lista de carros que sofreram evento nesta secção
							sofreuEventoNaSeccao.add(c.getID());

							evento = true;

						}

				Random rand = new Random();
				int x = rand.nextInt(101);
		
				if (!evento && ((clima == 0 && 1 - piloto.getCTS() > 0.6) || (clima == 1 && piloto.getCTS() > 0.6))
								&& x < 10) {
					// adiciona o jogador à lista de jogadores envolvidos no evento acidente
					idsJogadoresEnvolvidos.add(p.getPiloto().getNome());

					// adiciona novo evento do tipo acidente
					res.add(new Evento(i, p.getSeccao(), 0, idsJogadoresEnvolvidos));

					// adiciona acidentes provocados por acidente
					List<Evento> acidentes = algoGeraAcidente(progressosNestaSeccao, p, idsJogadoresEnvolvidos,
							sofreuEventoNaSeccao, i);
					if (acidentes != null)
						for (Evento e : acidentes)
							res.add(e.clone());

					// adiciona o carro à lista de carros que sofreram evento nesta secção
					sofreuEventoNaSeccao.add(c.getID());

					evento = true;
				}

				if (!evento) {

					// pode ocorrer ultrapassagens
					List<Evento> acidentes = ocorreuUltrapassagem(progressosNestaSeccao, p, sofreuEventoNaSeccao, i,
							seccaoATestar);
					if (acidentes != null)
						for (Evento e : acidentes) {
							res.add(e.clone());
						}

				}

			}
		}

		return res;
	}

	/**
	 * Metodo auxiliar privado para determinar o carro que vai em 1o a cada volta
	 */
	private List<Progresso> primeiroVolta(List<Progresso> l, List<Progresso> primeiroVolta) {
		List<Progresso> pl = new ArrayList<>();
		for (Progresso p : l)
			pl.add(p.clone());
		pl.sort((p1, p2) -> p1.compareTo(p2));
		for (int i = 0; i < pl.size(); i++)
			primeiroVolta.add(pl.get(i));
		return primeiroVolta;
	}

	// public List<Evento> calcularEventosPartida(Corrida aCorrida) {
	//
	// List<Evento> eventos = new ArrayList<Evento>();
	// List<Progresso> primeiroVolta = new ArrayList<Progresso>();
	// int voltas = aCorrida.getCircuito().getNVoltas();
	// Circuito circuito = aCorrida.getCircuito();
	// ArrayList<Progresso> aux = new ArrayList<Progresso>();
	// HashMap<Progresso, Integer> temp = new HashMap<Progresso, Integer>();
	// for (Progresso c : aCorrida.getProgressos()) {
	// aux.add(c.clone());
	// }
	// for (int i = 0; i < voltas; i++) {
	//
	// for (Progresso p : aux) {
	// Carro carro = p.getCarro();
	// Piloto piloto = p.getPiloto();
	// if (carro.getDNF() == false) // verifica se o carro esta acidentado
	// {
	// List<Evento> ev = null; //carro.checkLap(aCorrida.getCircuito(), i,
	// aCorrida.getClima(),piloto); // verifica se o carro tem evento na volta
	// int eve=-1;
	// if(ev!=null)
	// for (Evento e : ev){
	// if(e.getAcontecimento()==3)
	// eve=1;
	// eventos.add(e);
	// }
	// if (eve==1) // verifica se o carro tem acidente na volta
	// {
	// carro.setDNF(true);
	// temp.put(p.clone(), i);
	// }
	//
	// Double novo_tempo = carro.tempoProximaVolta(circuito, aCorrida.getClima(),
	// piloto);
	// Double tempo_progresso = p.getTempo() + novo_tempo;
	// p.setTempo(tempo_progresso);
	// p.setVolta(i);
	// }
	// }
	//
	// List<Evento> evs= this.checkMultiEventos();
	// for(Evento e:evs)
	// eventos.add(e);
	//
	// primeiroVolta = this.primeiroVolta(voltas-1, aux);
	// aCorrida.setPrimeiroVolta(primeiroVolta);
	//
	// }
	// aCorrida.setProgressos(aux);
	// return eventos;
	// }
	//
	// private List<Evento> checkMultiEventos() {
	// //
	// return new ArrayList<>();
	// }
	//
	/// **
	// * Metodo auxiliar privado para determinar o carro que vai em 1o a cada volta
	// */
	// private List<Progresso> primeiroVolta(int volta, List<Progresso> l)
	// {
	// List<Progresso> primeiroVolta = new ArrayList<>();
	// Collections.sort(l);
	// Iterator<Progresso> it = l.iterator();
	// Progresso c = null;
	// while(it.hasNext())
	// {
	// c = it.next();
	// }
	// if(c!=null) primeiroVolta.add(c.clone());
	// return primeiroVolta;
	// }
}