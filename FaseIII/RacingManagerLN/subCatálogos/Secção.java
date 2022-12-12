package DSS_Grupo27_2022_23.FaseIII.RacingManagerLN.subCatálogos;

public class Secção {

        private int _gDU;
        private String _tipo;
        private int _id;

        public int hashCode() {
            int lHashCode = 0;
            if ( this._tipo != null ) {
                lHashCode += this._tipo.hashCode();
            }
            if ( lHashCode == 0 ) {
                lHashCode = super.hashCode();
            }
            return lHashCode;
        }

        public boolean equals(Object aObject) {
            if (this == aObject) {
                return true;
            } else if (aObject instanceof Secção) {
                Secção lSecçãoObject = (Secção) aObject;
                boolean lEquals = true;
                lEquals &= this._gDU == lSecçãoObject._gDU;
                lEquals &= ((this._tipo == lSecçãoObject._tipo)
                        || (this._tipo != null && this._tipo.equals(lSecçãoObject._tipo)));
                lEquals &= this._id == lSecçãoObject._id;
                return lEquals;
            }
            return false;
        }
}
