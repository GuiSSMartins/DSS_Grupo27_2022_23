import java.util.ArrayList;

public class Fator_Deterioração {
	public ArrayList<GT> _contém = new ArrayList<GT>();

	public int hashCode() {
		int lHashCode = 0;
		if ( this._contém != null ) {
			lHashCode += this._contém.hashCode();
		}
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object aObject) {
		if (this == aObject) {
			return true;
		} else if (aObject instanceof Fator_Deterioração) {
			Fator_Deterioração lFator_DeterioraçãoObject = (Fator_Deterioração) aObject;
			boolean lEquals = true;
			lEquals &= ((this._contém == lFator_DeterioraçãoObject._contém)
				|| (this._contém != null && this._contém.equals(lFator_DeterioraçãoObject._contém)));
			return lEquals;
		}
		return false;
	}
}