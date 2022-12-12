public class C1 {
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
		} else if (aObject instanceof C1) {
			C1 lC1Object = (C1) aObject;
			boolean lEquals = true;
			lEquals &= ((this._é_um == lC1Object._é_um)
				|| (this._é_um != null && this._é_um.equals(lC1Object._é_um)));
			lEquals &= ((this._altera == lC1Object._altera)
				|| (this._altera != null && this._altera.equals(lC1Object._altera)));
			return lEquals;
		}
		return false;
	}
}