package business.subPartidas;

import java.util.List;

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
			boolean lEquals = true;
			return lEquals;
		}
		return false;
	}

	public List<Evento> calcularEventosPartida(Corrida aCorrida) {
		throw new UnsupportedOperationException();
	}
}