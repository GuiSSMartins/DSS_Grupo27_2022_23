package DSS_Grupo27_2022_23.FaseIII.RacingManagerLN.SubUtilizadores;


public class SSUtilizadores {
    public Utilizador.Utilizador _utilizador;

    public int hashCode() {
        int lHashCode = 0;
        if ( this._utilizador != null ) {
            lHashCode += this._utilizador.hashCode();
        }
        if ( lHashCode == 0 ) {
            lHashCode = super.hashCode();
        }
        return lHashCode;
    }

    public boolean equals(Object aObject) {
        if (this == aObject) {
            return true;
        } else if (aObject instanceof SSUtilizadores) {
            SSUtilizadores lSSUtilizadoresObject = (SSUtilizadores) aObject;
            boolean lEquals = true;
            lEquals &= ((this._utilizador == lSSUtilizadoresObject._utilizador)
                || (this._utilizador != null && this._utilizador.equals(lSSUtilizadoresObject._utilizador)));
            return lEquals;
        }
        return false;
    }
}