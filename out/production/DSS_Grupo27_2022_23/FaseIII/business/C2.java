public class C2 {
	public Categoria _é_um;
	public Afinação _altera;

	public int hashCode() {
		int lHashCode = 0;
		if ( this._é_um != null ) {
			lHashCode += this._é_um.hashCode();
		}
		if ( this._altera != null ) {
			lHashCode += this._altera.hashCode();
		}
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object aObject) {
		if (this == aObject) {
			return true;
		} else if (aObject instanceof C2) {
			C2 lC2Object = (C2) aObject;
			boolean lEquals = true;
			lEquals &= ((this._é_um == lC2Object._é_um)
				|| (this._é_um != null && this._é_um.equals(lC2Object._é_um)));
			lEquals &= ((this._altera == lC2Object._altera)
				|| (this._altera != null && this._altera.equals(lC2Object._altera)));
			return lEquals;
		}
		return false;
	}
}