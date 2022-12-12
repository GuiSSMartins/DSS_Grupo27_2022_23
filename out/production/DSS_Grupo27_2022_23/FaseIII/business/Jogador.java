import java.util.ArrayList;

public class Jogador {
	public Carreira _detém;
	public Campeonato _escolhe;
	public ArrayList<Afinação> _altera = new ArrayList<Afinação>();
	public Utilizador _é_um;

	public int hashCode() {
		int lHashCode = 0;
		if ( this._detém != null ) {
			lHashCode += this._detém.hashCode();
		}
		if ( this._escolhe != null ) {
			lHashCode += this._escolhe.hashCode();
		}
		if ( this._altera != null ) {
			lHashCode += this._altera.hashCode();
		}
		if ( this._é_um != null ) {
			lHashCode += this._é_um.hashCode();
		}
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object aObject) {
		if (this == aObject) {
			return true;
		} else if (aObject instanceof Jogador) {
			Jogador lJogadorObject = (Jogador) aObject;
			boolean lEquals = true;
			lEquals &= ((this._detém == lJogadorObject._detém)
				|| (this._detém != null && this._detém.equals(lJogadorObject._detém)));
			lEquals &= ((this._escolhe == lJogadorObject._escolhe)
				|| (this._escolhe != null && this._escolhe.equals(lJogadorObject._escolhe)));
			lEquals &= ((this._altera == lJogadorObject._altera)
				|| (this._altera != null && this._altera.equals(lJogadorObject._altera)));
			lEquals &= ((this._é_um == lJogadorObject._é_um)
				|| (this._é_um != null && this._é_um.equals(lJogadorObject._é_um)));
			return lEquals;
		}
		return false;
	}
}