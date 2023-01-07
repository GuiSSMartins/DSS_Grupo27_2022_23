package business.subPartidas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Iterator;

import business.subCatálogos.Carro;
import business.subCatálogos.Circuito;
import business.subCatálogos.GT;
import business.subCatálogos.GTHibrido;
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
		int voltas = aCorrida.getCircuito().getNVoltas();
		List<Seccao> seccoes = aCorrida.getCircuito().getseccoes();
		seccoes.sort((s1, s2) -> s1.getPosicao() - s2.getPosicao());
		List<Progresso> primeiroVolta = new ArrayList<>();
		int nSeccoes = seccoes.size();
		Circuito circuito = aCorrida.getCircuito();
		ArrayList<Progresso> aux = new ArrayList<Progresso>();
		for (Progresso c : aCorrida.getProgressos()) {
			c.setSeccao(0);
			aux.add(c);
		}

		List<Progresso> desistentes = new ArrayList<>();
		for (int i = 0; i < voltas; i++) {

			for(int j=0; j<nSeccoes; j++){

				List<Evento> ev = this.checkEventosSeccao(aCorrida.getCircuito(), aux, i, aCorrida.getClima(), seccoes.get(j));
				System.out.println("EVENTOS" + ev);
				if(ev!=null){
				// percorre a lista de eventos para determinar se houve algum desistente por avaria ou acidente
				for(Evento e:ev)
				{
					if(e.getAcontecimento()==0 || e.getAcontecimento()==3)
					{
						// vai a todos os jogadores envolvidos
						for(String piloto: e.getIdJogadoresEnvolvidos())
						{
							// procura pelo respetivo progresso
							for(Progresso p: aux)
							{
								if(piloto.equals(p.getPiloto().getNome()))
								{
									if(p.getCarro().checkDNF(i, voltas, aCorrida.getClima()))
									{
										// desistiu

										// adiciona à lista de desistentes
										desistentes.add(p.clone());

										// remove da lista de jogadores para a próxima volta
										aux.remove(p);
									}
								}
							}
						}
					}

					// adiciona o evento à lista geral de eventos
					eventos.add(e.clone());
				}
				}

				System.out.println("FINAL :");
				for (Progresso p: aux) {
					System.out.println(p);
				}

				for (Progresso p: aux) {
					if (!desistentes.contains(p)) {
						Double novo_tempo = p.getCarro().tempoProximaVolta(circuito, aCorrida.getClima(), p.getPiloto());
						Long tempo_progresso = p.getTempo() + Double.valueOf(novo_tempo).longValue();
						p.setTempo(tempo_progresso);
						if(nSeccoes==j) p.setSeccao(0);
						else p.setSeccao(j+1);
					}
				}
			}

			// aumenta o numero de voltas dadas no progresso
			for (Progresso p: aux)
				if (!desistentes.contains(p))
						p.setVolta(i+1);
			primeiroVolta = this.primeiroVolta(voltas-1, aux);
		}

		aCorrida.setPrimeiroVolta(primeiroVolta);
		return eventos;
	}

	public List<Evento> algoGeraAcidente(List<Progresso> aux, Progresso p, List<String> idsJogadoresEnvolvidos, List<Integer> sofreuEventoNaSeccao, int i) {
		List<Evento> res = new ArrayList<>();

		// percorre todos os progressos na seccao
		for (Progresso prog : aux)
			if (!prog.equals(p))
				if (prog.getPiloto().getSVA() > 0.5) 
				{
					// piloto agressivo

					// cria random entre 0 e 100
					int probAcidente = rand.nextInt(101);

					if (probAcidente > 80) 
					{
						List<String> idsJogadoresEnvolvidosTemp = new ArrayList<>();
						for (String s : idsJogadoresEnvolvidos)
							idsJogadoresEnvolvidosTemp.add(String.valueOf(s));

						// adiciona o jogador à lista de jogadores envolvidos no evento acidente
						idsJogadoresEnvolvidosTemp.add(prog.getPiloto().getNome());

						prog.getCarro().setDNF(true);

						// cria evento do tipo acidente
						res.add(new Evento(i, p.getSeccao(), 0, idsJogadoresEnvolvidosTemp));

						// adiciona o carro à lista de carros que sofreram evento nesta secção
						sofreuEventoNaSeccao.add(p.getCarro().getID());

						p.getCarro().setDNF(true);
					}
				}

		return res;
	}

	public List<Evento> ocorreuUltrapassagem(List<Progresso> aux, Progresso p, List<Integer> sofreuEventoNaSeccao, int i) {

		Piloto piloto = p.getPiloto();
		Carro c = p.getCarro();


		List<Evento> res = new ArrayList<>();

		for (Progresso prog : aux)
			if (!prog.equals(p))
				
				if (piloto.getSVA() > prog.getPiloto().getSVA() && c.compararaCarros(prog.getCarro())){
						// && (seccoes.get(seccao).probabilidadeCarroConsegueUltrapassar() * c.getPAC() > 0.8)) {
					// consegue ultrapassar

					// adiciona o jogador à lista de jogadores envolvidos no evento ultrapassar
					List<String> idsJogadoresEnvolvidos = new ArrayList<>();
					idsJogadoresEnvolvidos.add(p.getPiloto().getNome());

					// adiciona novo evento do tipo ultrapassagem
					res.add(new Evento(i, p.getSeccao(), 1, idsJogadoresEnvolvidos));

					// adiciona o carro à lista de carros que sofreram evento nesta secção
					sofreuEventoNaSeccao.add(c.getID());

					// adiciona o jogador à lista de jogadores envolvidos no evento ultrapassar
					List<String> idsJogadoresEnvolvidosTemp = new ArrayList<>();
					idsJogadoresEnvolvidosTemp.add(p.getPiloto().getNome());

					// adiciona o jogador à lista de jogadores envolvidos no evento ultrapassado
					idsJogadoresEnvolvidos.add(p.getPiloto().getNome());

					// adiciona novo evento do tipo ultrapassado
					res.add(new Evento(i, p.getSeccao(), 2, idsJogadoresEnvolvidosTemp));
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

		// pego nos progressos que estão nesta seccao
		for (Progresso p : aux){
			if (p.getSeccao() == seccaoATestar.getPosicao())
				progressosNestaSeccao.add(p);
		}
		int seccao = seccaoATestar.getPosicao();

		System.out.println("\nSECCAO: " + seccao + " PROGRESSOS: " + progressosNestaSeccao);
				
		// para todos os progressos nesta seccao
		for (Progresso p : progressosNestaSeccao) {
			
			Carro c = p.getCarro();

			if (!sofreuEventoNaSeccao.contains(c.getID())) {

				Piloto piloto = p.getPiloto();
				boolean evento = false;
				List<String> idsJogadoresEnvolvidos = new ArrayList<>();

				if (c instanceof GT)
					if (i * ((GT) c).getTaxaDeterioracao() >= 1) {
						System.out.println("AVARIA GT");
						// adiciona o jogador à lista de jogadores envolvidos no evento avaria
						idsJogadoresEnvolvidos.add(piloto.getNome());

						// adiciona novo evento do tipo avaria
						res.add(new Evento(i, seccao, 3, idsJogadoresEnvolvidos));

						// adiciona acidentes provocados por avaria
						List<Evento> acidentes = algoGeraAcidente(progressosNestaSeccao, p, idsJogadoresEnvolvidos,sofreuEventoNaSeccao,i);
						for (Evento e : acidentes)
							res.add(e.clone());

						// adiciona o carro à lista de carros que sofreram evento nesta secção
						sofreuEventoNaSeccao.add(c.getID());

						evento = true;

					} else if (c instanceof GTHibrido)
						if (i * ((GTHibrido) c).getTaxaDeterioracao() >= 1) {
							System.out.println("AVARIA HIBRIDOGT");
							// adiciona o jogador à lista de jogadores envolvidos no evento avaria
							idsJogadoresEnvolvidos.add(p.getPiloto().getNome());

							// adiciona novo evento do tipo avaria
							res.add(new Evento(i, p.getSeccao(), 3));

							// adiciona acidentes provocados por avaria
							List<Evento> acidentes = algoGeraAcidente(progressosNestaSeccao, p, idsJogadoresEnvolvidos,sofreuEventoNaSeccao,i);
							for (Evento e : acidentes)
								res.add(e.clone());

							// adiciona o carro à lista de carros que sofreram evento nesta secção
							sofreuEventoNaSeccao.add(c.getID());

							evento = true;

						}

				if (!evento && ((clima == 0 && 1 - piloto.getCTS() > 0.8) || (clima == 1 && piloto.getCTS() > 0.8))) {
					System.out.println("ACIDENTE");
					// está sol e o piloto não sabe conduzir com sol

					// adiciona o jogador à lista de jogadores envolvidos no evento acidente
					idsJogadoresEnvolvidos.add(p.getPiloto().getNome());

					// adiciona novo evento do tipo acidente
					res.add(new Evento(i, p.getSeccao(), 0, idsJogadoresEnvolvidos));

					// adiciona acidentes provocados por acidente
					List<Evento> acidentes = algoGeraAcidente(progressosNestaSeccao, p, idsJogadoresEnvolvidos,sofreuEventoNaSeccao,i);
					for (Evento e : acidentes)
						res.add(e.clone());

					// adiciona o carro à lista de carros que sofreram evento nesta secção
					sofreuEventoNaSeccao.add(c.getID());

					evento = true;
				}

				if (!evento) {

					// pode ocorrer ultrapassagens
					List<Evento> acidentes = ocorreuUltrapassagem(progressosNestaSeccao, p, sofreuEventoNaSeccao,i);
					for (Evento e : acidentes)
					{
						System.out.println("ULTRAPASSAGEM");
						res.add(e.clone());
					}
						
				}

			}
		}

		return null;
	}

	/**
	 * Metodo auxiliar privado para determinar o carro que vai em 1o a cada volta
	 */
    private List<Progresso> primeiroVolta(int volta, List<Progresso> l)
    {
		List<Progresso> primeiroVolta = new ArrayList<>();
		Collections.sort(l);
       	Iterator<Progresso> it = l.iterator();
       	Progresso c = null;
       	while(it.hasNext())
       	{
       	    c = it.next();
       	}
       	if(c!=null) primeiroVolta.add(c.clone());
		return primeiroVolta;
	}
}