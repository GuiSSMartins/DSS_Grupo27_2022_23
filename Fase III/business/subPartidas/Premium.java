package business.subPartidas;

import java.util.List;

public class Premium extends Simulador {

	public void calculaTempo() {
		throw new UnsupportedOperationException();
	}

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
		} else if (aObject instanceof Premium) {
			Premium lPremiumObject = (Premium) aObject;
			boolean lEquals = true;
			return lEquals;
		}
		return false;
	}

	public List<Evento> calcularEventosPartida(Corrida aCorrida) {
		throw new UnsupportedOperationException();
	}
}