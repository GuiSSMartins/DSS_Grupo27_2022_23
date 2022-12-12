import java.util.ArrayList;

public class Evento {
	private int _idSecção;
	private int _acontecimento;
	private List<String> _idjogadoresEnvolvidos;
	public ArrayList<Corrida> _possui = new ArrayList<Corrida>();
	public Acidente _é_um;
	public Volta _acontece;
	public ArrayList<Carro> _está_envolvido = new ArrayList<Carro>();
	public ArrayList<Piloto> _provoca = new ArrayList<Piloto>();

	public int hashCode() {
		int lHashCode = 0;
		if ( this._possui != null ) {
			lHashCode += this._possui.hashCode();
		}
		if ( this._é_um != null ) {
			lHashCode += this._é_um.hashCode();
		}
		if ( this._acontece != null ) {
			lHashCode += this._acontece.hashCode();
		}
		if ( this._está_envolvido != null ) {
			lHashCode += this._está_envolvido.hashCode();
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
		} else if (aObject instanceof Evento) {
			Evento lEventoObject = (Evento) aObject;
			boolean lEquals = true;
			lEquals &= this._idSecção == lEventoObject._idSecção;
			lEquals &= this._acontecimento == lEventoObject._acontecimento;
			lEquals &= this._idjogadoresEnvolvidos == lEventoObject._idjogadoresEnvolvidos;
			lEquals &= ((this._possui == lEventoObject._possui)
				|| (this._possui != null && this._possui.equals(lEventoObject._possui)));
			lEquals &= ((this._é_um == lEventoObject._é_um)
				|| (this._é_um != null && this._é_um.equals(lEventoObject._é_um)));
			lEquals &= ((this._acontece == lEventoObject._acontece)
				|| (this._acontece != null && this._acontece.equals(lEventoObject._acontece)));
			lEquals &= ((this._está_envolvido == lEventoObject._está_envolvido)
				|| (this._está_envolvido != null && this._está_envolvido.equals(lEventoObject._está_envolvido)));
			lEquals &= ((this._provoca == lEventoObject._provoca)
				|| (this._provoca != null && this._provoca.equals(lEventoObject._provoca)));
			return lEquals;
		}
		return false;
	}
}