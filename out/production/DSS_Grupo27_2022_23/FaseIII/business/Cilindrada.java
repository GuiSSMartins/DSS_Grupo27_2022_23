import java.util.ArrayList;

public class Cilindrada {
	public ArrayList<Carro> _caracterizado_pela = new ArrayList<Carro>();
	public ArrayList<Categoria> _associada_a = new ArrayList<Categoria>();

	public int hashCode() {
		int lHashCode = 0;
		if ( this._caracterizado_pela != null ) {
			lHashCode += this._caracterizado_pela.hashCode();
		}
		if ( this._associada_a != null ) {
			lHashCode += this._associada_a.hashCode();
		}
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object aObject) {
		if (this == aObject) {
			return true;
		} else if (aObject instanceof Cilindrada) {
			Cilindrada lCilindradaObject = (Cilindrada) aObject;
			boolean lEquals = true;
			lEquals &= ((this._caracterizado_pela == lCilindradaObject._caracterizado_pela)
				|| (this._caracterizado_pela != null && this._caracterizado_pela.equals(lCilindradaObject._caracterizado_pela)));
			lEquals &= ((this._associada_a == lCilindradaObject._associada_a)
				|| (this._associada_a != null && this._associada_a.equals(lCilindradaObject._associada_a)));
			return lEquals;
		}
		return false;
	}
}