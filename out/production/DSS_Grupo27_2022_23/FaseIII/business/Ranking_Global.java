import java.util.ArrayList;

public class Ranking_Global {
	public ArrayList<Carreira> _associado = new ArrayList<Carreira>();
	public ArrayList<Resultado_Final> _contém = new ArrayList<Resultado_Final>();

	public int hashCode() {
		int lHashCode = 0;
		if ( this._associado != null ) {
			lHashCode += this._associado.hashCode();
		}
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
		} else if (aObject instanceof Ranking_Global) {
			Ranking_Global lRanking_GlobalObject = (Ranking_Global) aObject;
			boolean lEquals = true;
			lEquals &= ((this._associado == lRanking_GlobalObject._associado)
				|| (this._associado != null && this._associado.equals(lRanking_GlobalObject._associado)));
			lEquals &= ((this._contém == lRanking_GlobalObject._contém)
				|| (this._contém != null && this._contém.equals(lRanking_GlobalObject._contém)));
			return lEquals;
		}
		return false;
	}
}