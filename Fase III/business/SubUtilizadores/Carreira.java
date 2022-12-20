package business.SubUtilizadores;

import java.util.HashMap;
import java.util.Map;

public class Carreira {
    private Map<String, Integer> resultados;

    public Carreira(){
        this.resultados = new HashMap<>();
    }

    public void adicionaResultado(String nome, int resultado) {
        this.resultados.put(nome,resultado);
    }

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
        } else if (aObject instanceof Carreira) {
            Carreira lCarreiraObject = (Carreira) aObject;
            boolean lEquals = true;
            lEquals &= this.resultados == lCarreiraObject.resultados;
            return lEquals;
        }
        return false;
    }
}