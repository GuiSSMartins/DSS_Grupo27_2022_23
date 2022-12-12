public class Username {
	public Utilizador _identificado_por;

	public int hashCode() {
		int lHashCode = 0;
		if ( this._identificado_por != null ) {
			lHashCode += this._identificado_por.hashCode();
		}
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object aObject) {
		if (this == aObject) {
			return true;
		} else if (aObject instanceof Username) {
			Username lUsernameObject = (Username) aObject;
			boolean lEquals = true;
			lEquals &= ((this._identificado_por == lUsernameObject._identificado_por)
				|| (this._identificado_por != null && this._identificado_por.equals(lUsernameObject._identificado_por)));
			return lEquals;
		}
		return false;
	}
}