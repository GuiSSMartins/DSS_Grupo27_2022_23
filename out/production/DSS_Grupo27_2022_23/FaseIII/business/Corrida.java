import Diagrama_de_Classes_com_Packages.Partidas.Progresso;
import java.util.ArrayList;
import Diagrama_de_Classes___Partidas.SubSistemaCatálogo____circuito;

public class Corrida {
	private String _situacaoMetereologica;
	private List<String> _posicoes;
	public Resultado _de_uma;
	public Progresso _progressos;
	public Circuito _configura;
	public ArrayList<Piloto> _participa = new ArrayList<Piloto>();
	public Situação_Meteorológica _influenciada_pela;
	public ArrayList<Evento> _possui = new ArrayList<Evento>();
	private Circuito.Circuito _circuito;
	private ArrayList<Evento> _eventos = new ArrayList<Evento>();
	public SubSistemaCatálogo____circuito _circuito;

	public int hashCode() {
		int lHashCode = 0;
		if ( this._situacaoMetereologica != null ) {
			lHashCode += this._situacaoMetereologica.hashCode();
		}
		if ( this._de_uma != null ) {
			lHashCode += this._de_uma.hashCode();
		}
		if ( this._progressos != null ) {
			lHashCode += this._progressos.hashCode();
		}
		if ( this._configura != null ) {
			lHashCode += this._configura.hashCode();
		}
		if ( this._participa != null ) {
			lHashCode += this._participa.hashCode();
		}
		if ( this._influenciada_pela != null ) {
			lHashCode += this._influenciada_pela.hashCode();
		}
		if ( this._possui != null ) {
			lHashCode += this._possui.hashCode();
		}
		if ( this._circuito != null ) {
			lHashCode += this._circuito.hashCode();
		}
		if ( this._eventos != null ) {
			lHashCode += this._eventos.hashCode();
		}
		if ( this._circuito != null ) {
			lHashCode += this._circuito.hashCode();
		}
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object aObject) {
		if (this == aObject) {
			return true;
		} else if (aObject instanceof Corrida) {
			Corrida lCorridaObject = (Corrida) aObject;
			boolean lEquals = true;
			lEquals &= ((this._situacaoMetereologica == lCorridaObject._situacaoMetereologica)
				|| (this._situacaoMetereologica != null && this._situacaoMetereologica.equals(lCorridaObject._situacaoMetereologica)));
			lEquals &= this._posicoes == lCorridaObject._posicoes;
			lEquals &= ((this._de_uma == lCorridaObject._de_uma)
				|| (this._de_uma != null && this._de_uma.equals(lCorridaObject._de_uma)));
			lEquals &= ((this._progressos == lCorridaObject._progressos)
				|| (this._progressos != null && this._progressos.equals(lCorridaObject._progressos)));
			lEquals &= ((this._configura == lCorridaObject._configura)
				|| (this._configura != null && this._configura.equals(lCorridaObject._configura)));
			lEquals &= ((this._participa == lCorridaObject._participa)
				|| (this._participa != null && this._participa.equals(lCorridaObject._participa)));
			lEquals &= ((this._influenciada_pela == lCorridaObject._influenciada_pela)
				|| (this._influenciada_pela != null && this._influenciada_pela.equals(lCorridaObject._influenciada_pela)));
			lEquals &= ((this._possui == lCorridaObject._possui)
				|| (this._possui != null && this._possui.equals(lCorridaObject._possui)));
			lEquals &= ((this._circuito == lCorridaObject._circuito)
				|| (this._circuito != null && this._circuito.equals(lCorridaObject._circuito)));
			lEquals &= ((this._eventos == lCorridaObject._eventos)
				|| (this._eventos != null && this._eventos.equals(lCorridaObject._eventos)));
			lEquals &= ((this._circuito == lCorridaObject._circuito)
				|| (this._circuito != null && this._circuito.equals(lCorridaObject._circuito)));
			return lEquals;
		}
		return false;
	}
}