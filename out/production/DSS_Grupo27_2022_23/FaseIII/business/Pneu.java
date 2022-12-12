public class Pneu {
	public Carro _possui;

	public int hashCode() {
		int lHashCode = 0;
		if ( this._possui != null ) {
			lHashCode += this._possui.hashCode();
		}
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object aObject) {
		if (this == aObject) {
			return true;
		} else if (aObject instanceof Pneu) {
			Pneu lPneuObject = (Pneu) aObject;
			boolean lEquals = true;
			lEquals &= ((this._possui == lPneuObject._possui)
				|| (this._possui != null && this._possui.equals(lPneuObject._possui)));
			return lEquals;
		}
		return false;
	}
}