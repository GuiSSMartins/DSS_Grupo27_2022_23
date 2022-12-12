public class Reta {
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
		} else if (aObject instanceof Reta) {
			Reta lRetaObject = (Reta) aObject;
			boolean lEquals = true;
			lEquals &= ((this._possui == lRetaObject._possui)
				|| (this._possui != null && this._possui.equals(lRetaObject._possui)));
			lEquals &= ((this._definido_por == lRetaObject._definido_por)
				|| (this._definido_por != null && this._definido_por.equals(lRetaObject._definido_por)));
			return lEquals;
		}
		return false;
	}
}