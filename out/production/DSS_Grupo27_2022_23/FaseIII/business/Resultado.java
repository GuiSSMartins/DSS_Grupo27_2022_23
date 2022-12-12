import java.util.ArrayList;

public class Resultado {
	public Resultado_Final _é_uma_coleção_de;
	public ArrayList<Categoria> _influenciado_por = new ArrayList<Categoria>();
	public ArrayList<Corrida> _de_uma = new ArrayList<Corrida>();
	public Carro _referente;

	public int hashCode() {
		int lHashCode = 0;
		if ( this._é_uma_coleção_de != null ) {
			lHashCode += this._é_uma_coleção_de.hashCode();
		}
		if ( this._influenciado_por != null ) {
			lHashCode += this._influenciado_por.hashCode();
		}
		if ( this._de_uma != null ) {
			lHashCode += this._de_uma.hashCode();
		}
		if ( this._referente != null ) {
			lHashCode += this._referente.hashCode();
		}
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object aObject) {
		if (this == aObject) {
			return true;
		} else if (aObject instanceof Resultado) {
			Resultado lResultadoObject = (Resultado) aObject;
			boolean lEquals = true;
			lEquals &= ((this._é_uma_coleção_de == lResultadoObject._é_uma_coleção_de)
				|| (this._é_uma_coleção_de != null && this._é_uma_coleção_de.equals(lResultadoObject._é_uma_coleção_de)));
			lEquals &= ((this._influenciado_por == lResultadoObject._influenciado_por)
				|| (this._influenciado_por != null && this._influenciado_por.equals(lResultadoObject._influenciado_por)));
			lEquals &= ((this._de_uma == lResultadoObject._de_uma)
				|| (this._de_uma != null && this._de_uma.equals(lResultadoObject._de_uma)));
			lEquals &= ((this._referente == lResultadoObject._referente)
				|| (this._referente != null && this._referente.equals(lResultadoObject._referente)));
			return lEquals;
		}
		return false;
	}
}