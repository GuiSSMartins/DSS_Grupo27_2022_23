public class Class2 {

	public int hashCode() {
		int lHashCode = 0;
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object aObject) {
		if (this == aObject) {
			return true;
		} else if (aObject instanceof Class2) {
			Class2 lClass2Object = (Class2) aObject;
			boolean lEquals = true;
			return lEquals;
		}
		return false;
	}
}