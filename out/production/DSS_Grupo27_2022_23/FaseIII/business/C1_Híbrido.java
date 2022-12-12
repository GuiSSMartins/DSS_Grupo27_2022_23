public class C1_Híbrido {
	public C1 _é_um;
	public Motor_Elétrico _possui;

	public int hashCode() {
		int lHashCode = 0;
		if ( this._é_um != null ) {
			lHashCode += this._é_um.hashCode();
		}
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
		} else if (aObject instanceof C1_Híbrido) {
			C1_Híbrido lC1_HíbridoObject = (C1_Híbrido) aObject;
			boolean lEquals = true;
			lEquals &= ((this._é_um == lC1_HíbridoObject._é_um)
				|| (this._é_um != null && this._é_um.equals(lC1_HíbridoObject._é_um)));
			lEquals &= ((this._possui == lC1_HíbridoObject._possui)
				|| (this._possui != null && this._possui.equals(lC1_HíbridoObject._possui)));
			return lEquals;
		}
		return false;
	}
}