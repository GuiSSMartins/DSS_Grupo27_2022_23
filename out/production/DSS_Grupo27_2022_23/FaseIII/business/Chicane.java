public class Chicane {
	public Circuito _possui;
	public _GDU _definido_por;

	public int hashCode() {
		int lHashCode = 0;
		if ( this._possui != null ) {
			lHashCode += this._possui.hashCode();
		}
		if ( this._definido_por != null ) {
			lHashCode += this._definido_por.hashCode();
		}
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object aObject) {
		if (this == aObject) {
			return true;
		} else if (aObject instanceof Chicane) {
			Chicane lChicaneObject = (Chicane) aObject;
			boolean lEquals = true;
			lEquals &= ((this._possui == lChicaneObject._possui)
				|| (this._possui != null && this._possui.equals(lChicaneObject._possui)));
			lEquals &= ((this._definido_por == lChicaneObject._definido_por)
				|| (this._definido_por != null && this._definido_por.equals(lChicaneObject._definido_por)));
			return lEquals;
		}
		return false;
	}
}