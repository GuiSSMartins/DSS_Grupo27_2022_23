import java.util.ArrayList;

public class Campeonato {
	public Administrador _adiciona;
	public ArrayList<Jogador> _escolhe = new ArrayList<Jogador>();
	public ArrayList<Resultado_Final> _possui = new ArrayList<Resultado_Final>();
	public ArrayList<Circuito> _é_formado_por = new ArrayList<Circuito>();
	public Nome _denominado;
	public ArrayList<Piloto> _participa = new ArrayList<Piloto>();

	public int hashCode() {
		int lHashCode = 0;
		if ( this._adiciona != null ) {
			lHashCode += this._adiciona.hashCode();
		}
		if ( this._escolhe != null ) {
			lHashCode += this._escolhe.hashCode();
		}
		if ( this._possui != null ) {
			lHashCode += this._possui.hashCode();
		}
		if ( this._é_formado_por != null ) {
			lHashCode += this._é_formado_por.hashCode();
		}
		if ( this._denominado != null ) {
			lHashCode += this._denominado.hashCode();
		}
		if ( this._participa != null ) {
			lHashCode += this._participa.hashCode();
		}
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object aObject) {
		if (this == aObject) {
			return true;
		} else if (aObject instanceof Campeonato) {
			Campeonato lCampeonatoObject = (Campeonato) aObject;
			boolean lEquals = true;
			lEquals &= ((this._adiciona == lCampeonatoObject._adiciona)
				|| (this._adiciona != null && this._adiciona.equals(lCampeonatoObject._adiciona)));
			lEquals &= ((this._escolhe == lCampeonatoObject._escolhe)
				|| (this._escolhe != null && this._escolhe.equals(lCampeonatoObject._escolhe)));
			lEquals &= ((this._possui == lCampeonatoObject._possui)
				|| (this._possui != null && this._possui.equals(lCampeonatoObject._possui)));
			lEquals &= ((this._é_formado_por == lCampeonatoObject._é_formado_por)
				|| (this._é_formado_por != null && this._é_formado_por.equals(lCampeonatoObject._é_formado_por)));
			lEquals &= ((this._denominado == lCampeonatoObject._denominado)
				|| (this._denominado != null && this._denominado.equals(lCampeonatoObject._denominado)));
			lEquals &= ((this._participa == lCampeonatoObject._participa)
				|| (this._participa != null && this._participa.equals(lCampeonatoObject._participa)));
			return lEquals;
		}
		return false;
	}
}