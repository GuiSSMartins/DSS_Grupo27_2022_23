package business.subPartidas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;

import business.subCatálogos.Carro;
import business.subCatálogos.Hibrido;
import business.subCatálogos.Piloto;

public class Base extends Simulador {

	public int hashCode() {
		int lHashCode = 0;
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object aObject) {
		if (this == aObject) {
			return true;
		} else if (aObject instanceof Base) {
			Base lBaseObject = (Base) aObject;
			return this==lBaseObject;
		// ajuda deus
		}
		return false;
	}

	public List<Evento> calcularEventosPartida(Corrida aCorrida) {
		
		List<Evento> eventos = new ArrayList<Evento>();
		List<Progresso> primeiroVolta = new ArrayList<Progresso>();
		int voltas = aCorrida.getCircuito().getNVoltas();
		ArrayList<Progresso> aux = new ArrayList<Progresso>();
		HashMap<Progresso, Integer> temp = new HashMap<Progresso, Integer>();
		for (Progresso c : aCorrida.getProgressos()) {
			aux.add(c.clone());
		}
		for (int i = 0; i < voltas; i++) {
			// Criar os progressos desta volta

			// Ler os progressos
			for (Progresso p : aux) {
				Carro carro = p.getCarro();
				Piloto piloto = p.getPiloto();
				if (carro.getDNF() == false) // verifica se o carro esta acidentado
				{
					List<Evento> ev = carro.checkLap(aCorrida.getCircuito(), i, aCorrida.getClima(),piloto); // verifica se o carro tem evento na volta
					int eve=-1;
					if(ev!=null)
						for (Evento e : ev){
							if(e.getAcontecimento()==3)
								eve=1;
							eventos.add(e);
						}
					if (eve==1) // verifica se o carro tem acidente na volta
					{
						carro.setDNF(true);
						temp.put(p.clone(), i);
					} 
				}
			}
			List<Evento> evs= this.checkMultiEventos();
			for(Evento e:evs)
				eventos.add(e);
			primeiroVolta = this.primeiroVolta(i, aux, primeiroVolta);
		}
		return eventos;
	}

	private List<Evento> checkMultiEventos() {
		//
		return null;
	}

	/**
     * Metodo auxiliar privado para determinar o carro que vai em 1o a cada volta
     */
    private List<Progresso> primeiroVolta(int volta, List<Progresso> l, List<Progresso> primeiroVolta)
    {
       	l.sort(null); 
       	Iterator<Progresso> it = l.iterator();
       	boolean f = false;
       	Progresso c = null;
       	while(it.hasNext() && f==false)
       	{
       	    c = it.next();
       	}
       	if(c!=null)
       	    primeiroVolta.add(volta,c.clone());
		return primeiroVolta;
	}
}