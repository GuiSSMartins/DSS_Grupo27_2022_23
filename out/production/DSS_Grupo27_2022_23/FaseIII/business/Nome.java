public class Nome {
	public Campeonato _denominado;

	public int hashCode() {
		int lHashCode = 0;
		if ( this._denominado != null ) {
			lHashCode += this._denominado.hashCode();
		}
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object aObject) {
		if (this == aObject) {
			return true;
		} else if (aObject instanceof Nome) {
			Nome lNomeObject = (Nome) aObject;
			boolean lEquals = true;
			lEquals &= ((this._denominado == lNomeObject._denominado)
				|| (this._denominado != null && this._denominado.equals(lNomeObject._denominado)));
			return lEquals;
		}
		return false;
	}
}