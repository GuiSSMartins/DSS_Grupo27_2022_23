import java.util.ArrayList;

public class Motor_Combustão {
	public ArrayList<Carro> _utiliza_um = new ArrayList<Carro>();
	public Potência _caracterizado_por;

	public int hashCode() {
		int lHashCode = 0;
		if ( this._utiliza_um != null ) {
			lHashCode += this._utiliza_um.hashCode();
		}
		if ( this._caracterizado_por != null ) {
			lHashCode += this._caracterizado_por.hashCode();
		}
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object aObject) {
		if (this == aObject) {
			return true;
		} else if (aObject instanceof Motor_Combustão) {
			Motor_Combustão lMotor_CombustãoObject = (Motor_Combustão) aObject;
			boolean lEquals = true;
			lEquals &= ((this._utiliza_um == lMotor_CombustãoObject._utiliza_um)
				|| (this._utiliza_um != null && this._utiliza_um.equals(lMotor_CombustãoObject._utiliza_um)));
			lEquals &= ((this._caracterizado_por == lMotor_CombustãoObject._caracterizado_por)
				|| (this._caracterizado_por != null && this._caracterizado_por.equals(lMotor_CombustãoObject._caracterizado_por)));
			return lEquals;
		}
		return false;
	}
}