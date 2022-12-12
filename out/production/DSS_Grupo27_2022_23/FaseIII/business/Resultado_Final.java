import java.util.ArrayList;

public class Resultado_Final {
	public Ranking_Global _contém;
	public ArrayList<Resultado> _é_uma_coleção_de = new ArrayList<Resultado>();
	public ArrayList<Piloto> _referente = new ArrayList<Piloto>();
	public Campeonato _possui;
	public ArrayList<Carreira> _apresenta = new ArrayList<Carreira>();

	public int hashCode() {
		int lHashCode = 0;
		if ( this._contém != null ) {
			lHashCode += this._contém.hashCode();
		}
		if ( this._é_uma_coleção_de != null ) {
			lHashCode += this._é_uma_coleção_de.hashCode();
		}
		if ( this._referente != null ) {
			lHashCode += this._referente.hashCode();
		}
		if ( this._possui != null ) {
			lHashCode += this._possui.hashCode();
		}
		if ( this._apresenta != null ) {
			lHashCode += this._apresenta.hashCode();
		}
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object aObject) {
		if (this == aObject) {
			return true;
		} else if (aObject instanceof Resultado_Final) {
			Resultado_Final lResultado_FinalObject = (Resultado_Final) aObject;
			boolean lEquals = true;
			lEquals &= ((this._contém == lResultado_FinalObject._contém)
				|| (this._contém != null && this._contém.equals(lResultado_FinalObject._contém)));
			lEquals &= ((this._é_uma_coleção_de == lResultado_FinalObject._é_uma_coleção_de)
				|| (this._é_uma_coleção_de != null && this._é_uma_coleção_de.equals(lResultado_FinalObject._é_uma_coleção_de)));
			lEquals &= ((this._referente == lResultado_FinalObject._referente)
				|| (this._referente != null && this._referente.equals(lResultado_FinalObject._referente)));
			lEquals &= ((this._possui == lResultado_FinalObject._possui)
				|| (this._possui != null && this._possui.equals(lResultado_FinalObject._possui)));
			lEquals &= ((this._apresenta == lResultado_FinalObject._apresenta)
				|| (this._apresenta != null && this._apresenta.equals(lResultado_FinalObject._apresenta)));
			return lEquals;
		}
		return false;
	}
}