import java.util.ArrayList;

public class PAC {
	public ArrayList<Carro> _caracterizado_pela = new ArrayList<Carro>();

	public int hashCode() {
		int lHashCode = 0;
		if ( this._caracterizado_pela != null ) {
			lHashCode += this._caracterizado_pela.hashCode();
		}
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object aObject) {
		if (this == aObject) {
			return true;
		} else if (aObject instanceof PAC) {
			PAC lPACObject = (PAC) aObject;
			boolean lEquals = true;
			lEquals &= ((this._caracterizado_pela == lPACObject._caracterizado_pela)
				|| (this._caracterizado_pela != null && this._caracterizado_pela.equals(lPACObject._caracterizado_pela)));
			return lEquals;
		}
		return false;
	}
}