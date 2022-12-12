import java.util.ArrayList;

public class Curva {
	public ArrayList<Circuito> _possui = new ArrayList<Circuito>();
	public _GDU _definido_por;

	public int hashCode() {
		int lHashCode = 0;
		if ( this._possui != null ) {
			lHashCode += this._possui.hashCode();
		}
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
		} else if (aObject instanceof Curva) {
			Curva lCurvaObject = (Curva) aObject;
			boolean lEquals = true;
			lEquals &= ((this._possui == lCurvaObject._possui)
				|| (this._possui != null && this._possui.equals(lCurvaObject._possui)));
			lEquals &= ((this._definido_por == lCurvaObject._definido_por)
				|| (this._definido_por != null && this._definido_por.equals(lCurvaObject._definido_por)));
			return lEquals;
		}
		return false;
	}
}