import java.util.ArrayList;

public class Nível_de_Perícia {
	public ArrayList<Piloto> _possui = new ArrayList<Piloto>();
	public SVA _é_um;

	public int hashCode() {
		int lHashCode = 0;
		if ( this._possui != null ) {
			lHashCode += this._possui.hashCode();
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
		} else if (aObject instanceof Nível_de_Perícia) {
			Nível_de_Perícia lNível_de_PeríciaObject = (Nível_de_Perícia) aObject;
			boolean lEquals = true;
			lEquals &= ((this._possui == lNível_de_PeríciaObject._possui)
				|| (this._possui != null && this._possui.equals(lNível_de_PeríciaObject._possui)));
			lEquals &= ((this._é_um == lNível_de_PeríciaObject._é_um)
				|| (this._é_um != null && this._é_um.equals(lNível_de_PeríciaObject._é_um)));
			return lEquals;
		}
		return false;
	}
}