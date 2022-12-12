public class Avaria {
	public Evento _é_um;

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
		} else if (aObject instanceof Avaria) {
			Avaria lAvariaObject = (Avaria) aObject;
			boolean lEquals = true;
			lEquals &= ((this._é_um == lAvariaObject._é_um)
				|| (this._é_um != null && this._é_um.equals(lAvariaObject._é_um)));
			return lEquals;
		}
		return false;
	}
}