import java.util.ArrayList;

public class Potência {
	public ArrayList<Motor_Combustão> _caracterizado_por = new ArrayList<Motor_Combustão>();

	public int hashCode() {
		int lHashCode = 0;
		if ( this._caracterizado_por != null ) {
			lHashCode += this._caracterizado_por.hashCode();
		}
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object aObject) {
		if (this == aObject) {
			return true;
		} else if (aObject instanceof Potência) {
			Potência lPotênciaObject = (Potência) aObject;
			boolean lEquals = true;
			lEquals &= ((this._caracterizado_por == lPotênciaObject._caracterizado_por)
				|| (this._caracterizado_por != null && this._caracterizado_por.equals(lPotênciaObject._caracterizado_por)));
			return lEquals;
		}
		return false;
	}
}