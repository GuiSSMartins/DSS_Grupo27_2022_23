public class Afinação {
	public Jogador _altera;

	public int hashCode() {
		int lHashCode = 0;
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
		} else if (aObject instanceof Afinação) {
			Afinação lAfinaçãoObject = (Afinação) aObject;
			boolean lEquals = true;
			lEquals &= ((this._altera == lAfinaçãoObject._altera)
				|| (this._altera != null && this._altera.equals(lAfinaçãoObject._altera)));
			return lEquals;
		}
		return false;
	}
}