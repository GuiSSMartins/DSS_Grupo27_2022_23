import java.util.ArrayList;

public class Piloto {
	private String _nome;
	private double _cTS;
	private double _sVA;
	public ArrayList<Corrida> _participa = new ArrayList<Corrida>();
	public Administrador _adiciona;
	public ArrayList<Resultado> _referente = new ArrayList<Resultado>();
	public ArrayList<Carro> _conduz = new ArrayList<Carro>();
	public Nível_de_Perícia _possui;
	public Nome _denominado;
	public ArrayList<Evento> _provoca = new ArrayList<Evento>();

	public int hashCode() {
		int lHashCode = 0;
		if ( this._nome != null ) {
			lHashCode += this._nome.hashCode();
		}
		if ( this._participa != null ) {
			lHashCode += this._participa.hashCode();
		}
		if ( this._adiciona != null ) {
			lHashCode += this._adiciona.hashCode();
		}
		if ( this._referente != null ) {
			lHashCode += this._referente.hashCode();
		}
		if ( this._conduz != null ) {
			lHashCode += this._conduz.hashCode();
		}
		if ( this._possui != null ) {
			lHashCode += this._possui.hashCode();
		}
		if ( this._denominado != null ) {
			lHashCode += this._denominado.hashCode();
		}
		if ( this._provoca != null ) {
			lHashCode += this._provoca.hashCode();
		}
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object aObject) {
		if (this == aObject) {
			return true;
		} else if (aObject instanceof Piloto) {
			Piloto lPilotoObject = (Piloto) aObject;
			boolean lEquals = true;
			lEquals &= ((this._nome == lPilotoObject._nome)
				|| (this._nome != null && this._nome.equals(lPilotoObject._nome)));
			lEquals &= this._cTS == lPilotoObject._cTS;
			lEquals &= this._sVA == lPilotoObject._sVA;
			lEquals &= ((this._participa == lPilotoObject._participa)
				|| (this._participa != null && this._participa.equals(lPilotoObject._participa)));
			lEquals &= ((this._adiciona == lPilotoObject._adiciona)
				|| (this._adiciona != null && this._adiciona.equals(lPilotoObject._adiciona)));
			lEquals &= ((this._referente == lPilotoObject._referente)
				|| (this._referente != null && this._referente.equals(lPilotoObject._referente)));
			lEquals &= ((this._conduz == lPilotoObject._conduz)
				|| (this._conduz != null && this._conduz.equals(lPilotoObject._conduz)));
			lEquals &= ((this._possui == lPilotoObject._possui)
				|| (this._possui != null && this._possui.equals(lPilotoObject._possui)));
			lEquals &= ((this._denominado == lPilotoObject._denominado)
				|| (this._denominado != null && this._denominado.equals(lPilotoObject._denominado)));
			lEquals &= ((this._provoca == lPilotoObject._provoca)
				|| (this._provoca != null && this._provoca.equals(lPilotoObject._provoca)));
			return lEquals;
		}
		return false;
	}
}