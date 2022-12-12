public class GT_Híbrido {
	public GT _é_um;
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
		} else if (aObject instanceof GT_Híbrido) {
			GT_Híbrido lGT_HíbridoObject = (GT_Híbrido) aObject;
			boolean lEquals = true;
			lEquals &= ((this._é_um == lGT_HíbridoObject._é_um)
				|| (this._é_um != null && this._é_um.equals(lGT_HíbridoObject._é_um)));
			lEquals &= ((this._possui == lGT_HíbridoObject._possui)
				|| (this._possui != null && this._possui.equals(lGT_HíbridoObject._possui)));
			return lEquals;
		}
		return false;
	}
}