import java.util.ArrayList;

public class Situação_Meteorológica {
	public ArrayList<Corrida> _influenciada_pela = new ArrayList<Corrida>();

	public int hashCode() {
		int lHashCode = 0;
		if ( this._influenciada_pela != null ) {
			lHashCode += this._influenciada_pela.hashCode();
		}
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object aObject) {
		if (this == aObject) {
			return true;
		} else if (aObject instanceof Situação_Meteorológica) {
			Situação_Meteorológica lSituação_MeteorológicaObject = (Situação_Meteorológica) aObject;
			boolean lEquals = true;
			lEquals &= ((this._influenciada_pela == lSituação_MeteorológicaObject._influenciada_pela)
				|| (this._influenciada_pela != null && this._influenciada_pela.equals(lSituação_MeteorológicaObject._influenciada_pela)));
			return lEquals;
		}
		return false;
	}
}