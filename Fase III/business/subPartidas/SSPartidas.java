package business.subPartidas;
public class SSPartidas {
	private Partida _partidas;

	public int hashCode() {
		int lHashCode = 0;
		if ( this._partidas != null ) {
			lHashCode += this._partidas.hashCode();
		}
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object aObject) {
		if (this == aObject) {
			return true;
		} else if (aObject instanceof SSPartidas) {
			SSPartidas lSSPartidasObject = (SSPartidas) aObject;
			boolean lEquals = true;
			lEquals &= ((this._partidas == lSSPartidasObject._partidas)
				|| (this._partidas != null && this._partidas.equals(lSSPartidasObject._partidas)));
			return lEquals;
		}
		return false;
	}
}