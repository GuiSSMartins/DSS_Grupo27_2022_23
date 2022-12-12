public class Agressivo {
	public Modo_do_motor _é_um;

	public int hashCode() {
		int lHashCode = 0;
		if ( this._é_um != null ) {
			lHashCode += this._é_um.hashCode();
		}
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object aObject) {
		if (this == aObject) {
			return true;
		} else if (aObject instanceof Agressivo) {
			Agressivo lAgressivoObject = (Agressivo) aObject;
			boolean lEquals = true;
			lEquals &= ((this._é_um == lAgressivoObject._é_um)
				|| (this._é_um != null && this._é_um.equals(lAgressivoObject._é_um)));
			return lEquals;
		}
		return false;
	}
}