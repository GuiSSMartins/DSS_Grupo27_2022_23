import java.util.ArrayList;

public class Modo_do_motor {
	public Normal _é_um;
	public ArrayList<Jogador> _escolhe = new ArrayList<Jogador>();
	public ArrayList<Carro> _possui = new ArrayList<Carro>();

	public int hashCode() {
		int lHashCode = 0;
		if ( this._é_um != null ) {
			lHashCode += this._é_um.hashCode();
		}
		if ( this._escolhe != null ) {
			lHashCode += this._escolhe.hashCode();
		}
		if ( this._possui != null ) {
			lHashCode += this._possui.hashCode();
		}
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object aObject) {
		if (this == aObject) {
			return true;
		} else if (aObject instanceof Modo_do_motor) {
			Modo_do_motor lModo_do_motorObject = (Modo_do_motor) aObject;
			boolean lEquals = true;
			lEquals &= ((this._é_um == lModo_do_motorObject._é_um)
				|| (this._é_um != null && this._é_um.equals(lModo_do_motorObject._é_um)));
			lEquals &= ((this._escolhe == lModo_do_motorObject._escolhe)
				|| (this._escolhe != null && this._escolhe.equals(lModo_do_motorObject._escolhe)));
			lEquals &= ((this._possui == lModo_do_motorObject._possui)
				|| (this._possui != null && this._possui.equals(lModo_do_motorObject._possui)));
			return lEquals;
		}
		return false;
	}
}