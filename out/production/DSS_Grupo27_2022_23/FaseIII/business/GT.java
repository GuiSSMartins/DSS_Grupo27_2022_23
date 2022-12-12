public class GT {
	public Categoria _é_um;
	public Fator_Deterioração _contém;

	public int hashCode() {
		int lHashCode = 0;
		if ( this._é_um != null ) {
			lHashCode += this._é_um.hashCode();
		}
		if ( this._contém != null ) {
			lHashCode += this._contém.hashCode();
		}
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object aObject) {
		if (this == aObject) {
			return true;
		} else if (aObject instanceof GT) {
			GT lGTObject = (GT) aObject;
			boolean lEquals = true;
			lEquals &= ((this._é_um == lGTObject._é_um)
				|| (this._é_um != null && this._é_um.equals(lGTObject._é_um)));
			lEquals &= ((this._contém == lGTObject._contém)
				|| (this._contém != null && this._contém.equals(lGTObject._contém)));
			return lEquals;
		}
		return false;
	}
}