import java.util.ArrayList;

public class Motor_Elétrico {
	public ArrayList<C1_Híbrido> _possui = new ArrayList<C1_Híbrido>();
	public Potência _caracterizado_por;

	public int hashCode() {
		int lHashCode = 0;
		if ( this._possui != null ) {
			lHashCode += this._possui.hashCode();
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
		} else if (aObject instanceof Motor_Elétrico) {
			Motor_Elétrico lMotor_ElétricoObject = (Motor_Elétrico) aObject;
			boolean lEquals = true;
			lEquals &= ((this._possui == lMotor_ElétricoObject._possui)
				|| (this._possui != null && this._possui.equals(lMotor_ElétricoObject._possui)));
			lEquals &= ((this._caracterizado_por == lMotor_ElétricoObject._caracterizado_por)
				|| (this._caracterizado_por != null && this._caracterizado_por.equals(lMotor_ElétricoObject._caracterizado_por)));
			return lEquals;
		}
		return false;
	}
}