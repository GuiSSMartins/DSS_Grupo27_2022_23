import java.util.ArrayList;

public class Categoria {
	public ArrayList<Carro> _pertence_a = new ArrayList<Carro>();
	public Fiabilidade _depende_da;
	public ArrayList<Resultado> _influenciado_por = new ArrayList<Resultado>();
	public C2 _é_um;
	public Cilindrada _associada_a;

	public int hashCode() {
		int lHashCode = 0;
		if ( this._pertence_a != null ) {
			lHashCode += this._pertence_a.hashCode();
		}
		if ( this._depende_da != null ) {
			lHashCode += this._depende_da.hashCode();
		}
		if ( this._influenciado_por != null ) {
			lHashCode += this._influenciado_por.hashCode();
		}
		if ( this._é_um != null ) {
			lHashCode += this._é_um.hashCode();
		}
		if ( this._associada_a != null ) {
			lHashCode += this._associada_a.hashCode();
		}
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object aObject) {
		if (this == aObject) {
			return true;
		} else if (aObject instanceof Categoria) {
			Categoria lCategoriaObject = (Categoria) aObject;
			boolean lEquals = true;
			lEquals &= ((this._pertence_a == lCategoriaObject._pertence_a)
				|| (this._pertence_a != null && this._pertence_a.equals(lCategoriaObject._pertence_a)));
			lEquals &= ((this._depende_da == lCategoriaObject._depende_da)
				|| (this._depende_da != null && this._depende_da.equals(lCategoriaObject._depende_da)));
			lEquals &= ((this._influenciado_por == lCategoriaObject._influenciado_por)
				|| (this._influenciado_por != null && this._influenciado_por.equals(lCategoriaObject._influenciado_por)));
			lEquals &= ((this._é_um == lCategoriaObject._é_um)
				|| (this._é_um != null && this._é_um.equals(lCategoriaObject._é_um)));
			lEquals &= ((this._associada_a == lCategoriaObject._associada_a)
				|| (this._associada_a != null && this._associada_a.equals(lCategoriaObject._associada_a)));
			return lEquals;
		}
		return false;
	}
}