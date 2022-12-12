import java.util.ArrayList;

public class Fiabilidade {
	public ArrayList<Carro> _caracterizada_pela = new ArrayList<Carro>();
	public Categoria _depende_da;

	public int hashCode() {
		int lHashCode = 0;
		if ( this._caracterizada_pela != null ) {
			lHashCode += this._caracterizada_pela.hashCode();
		}
		if ( this._depende_da != null ) {
			lHashCode += this._depende_da.hashCode();
		}
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object aObject) {
		if (this == aObject) {
			return true;
		} else if (aObject instanceof Fiabilidade) {
			Fiabilidade lFiabilidadeObject = (Fiabilidade) aObject;
			boolean lEquals = true;
			lEquals &= ((this._caracterizada_pela == lFiabilidadeObject._caracterizada_pela)
				|| (this._caracterizada_pela != null && this._caracterizada_pela.equals(lFiabilidadeObject._caracterizada_pela)));
			lEquals &= ((this._depende_da == lFiabilidadeObject._depende_da)
				|| (this._depende_da != null && this._depende_da.equals(lFiabilidadeObject._depende_da)));
			return lEquals;
		}
		return false;
	}
}