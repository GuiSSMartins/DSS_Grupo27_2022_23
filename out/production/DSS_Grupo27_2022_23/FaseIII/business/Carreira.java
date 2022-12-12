import java.util.ArrayList;

public class Carreira {
	public ArrayList<Jogador> _detém = new ArrayList<Jogador>();
	public ArrayList<Resultado_Final> _apresenta = new ArrayList<Resultado_Final>();
	public Ranking_Global _associado;

	public int hashCode() {
		int lHashCode = 0;
		if ( this._detém != null ) {
			lHashCode += this._detém.hashCode();
		}
		if ( this._apresenta != null ) {
			lHashCode += this._apresenta.hashCode();
		}
		if ( this._associado != null ) {
			lHashCode += this._associado.hashCode();
		}
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object aObject) {
		if (this == aObject) {
			return true;
		} else if (aObject instanceof Carreira) {
			Carreira lCarreiraObject = (Carreira) aObject;
			boolean lEquals = true;
			lEquals &= ((this._detém == lCarreiraObject._detém)
				|| (this._detém != null && this._detém.equals(lCarreiraObject._detém)));
			lEquals &= ((this._apresenta == lCarreiraObject._apresenta)
				|| (this._apresenta != null && this._apresenta.equals(lCarreiraObject._apresenta)));
			lEquals &= ((this._associado == lCarreiraObject._associado)
				|| (this._associado != null && this._associado.equals(lCarreiraObject._associado)));
			return lEquals;
		}
		return false;
	}
}