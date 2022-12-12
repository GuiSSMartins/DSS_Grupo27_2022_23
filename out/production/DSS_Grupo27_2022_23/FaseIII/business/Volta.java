import java.util.ArrayList;

public class Volta {
	public Circuito _é_constituído_por;
	public ArrayList<Evento> _acontece = new ArrayList<Evento>();

	public int hashCode() {
		int lHashCode = 0;
		if ( this._é_constituído_por != null ) {
			lHashCode += this._é_constituído_por.hashCode();
		}
		if ( this._acontece != null ) {
			lHashCode += this._acontece.hashCode();
		}
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object aObject) {
		if (this == aObject) {
			return true;
		} else if (aObject instanceof Volta) {
			Volta lVoltaObject = (Volta) aObject;
			boolean lEquals = true;
			lEquals &= ((this._é_constituído_por == lVoltaObject._é_constituído_por)
				|| (this._é_constituído_por != null && this._é_constituído_por.equals(lVoltaObject._é_constituído_por)));
			lEquals &= ((this._acontece == lVoltaObject._acontece)
				|| (this._acontece != null && this._acontece.equals(lVoltaObject._acontece)));
			return lEquals;
		}
		return false;
	}
}