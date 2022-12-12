import java.util.ArrayList;

public class _GDU {
	public ArrayList<Reta> _definido_por = new ArrayList<Reta>();

	public int hashCode() {
		int lHashCode = 0;
		if ( this._definido_por != null ) {
			lHashCode += this._definido_por.hashCode();
		}
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object aObject) {
		if (this == aObject) {
			return true;
		} else if (aObject instanceof _GDU) {
			_GDU l_GDUObject = (_GDU) aObject;
			boolean lEquals = true;
			lEquals &= ((this._definido_por == l_GDUObject._definido_por)
				|| (this._definido_por != null && this._definido_por.equals(l_GDUObject._definido_por)));
			return lEquals;
		}
		return false;
	}
}