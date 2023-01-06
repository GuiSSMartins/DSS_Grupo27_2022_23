package business.subPartidas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Simulador {
	private ArrayList<Evento> _eventos = new ArrayList<Evento>();

	public abstract List<Evento> calcularEventosPartida(Corrida aCorrida);

	public Evento verificaEvento(Map<String, Progresso> aProgressos) {
		throw new UnsupportedOperationException();
	}

	public int hashCode() {
		int lHashCode = 0;
		if ( this._eventos != null ) {
			lHashCode += this._eventos.hashCode();
		}
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object aObject) {
		if (this == aObject) {
			return true;
		} else if (aObject instanceof Simulador) {
			Simulador lSimuladorObject = (Simulador) aObject;
			boolean lEquals = true;
			lEquals &= ((this._eventos == lSimuladorObject._eventos)
				|| (this._eventos != null && this._eventos.equals(lSimuladorObject._eventos)));
			return lEquals;
		}
		return false;
	}
}