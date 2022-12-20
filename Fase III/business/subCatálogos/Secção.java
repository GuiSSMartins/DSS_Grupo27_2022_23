package business.subCatálogos;

public class Secção {

    private int GDU;
    private String tipo;
    private int id;

    public Secção(){

    }

    public int getGDU(){
        return this.GDU;
    }

    public void setGDU(int GDU) {
        this.GDU = GDU;
    }

    public String getTipo() {
        return this.tipo;
    }

    public int getID() {
        return this.id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int hashCode() {
        int lHashCode = 0;
        if ( this.tipo != null ) {
            lHashCode += this.tipo.hashCode();
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
            lEquals &= this.GDU == lSecçãoObject.GDU;
            lEquals &= ((this.tipo == lSecçãoObject.tipo)
                    || (this.tipo != null && this.tipo.equals(lSecçãoObject.tipo)));
            lEquals &= this.id == lSecçãoObject.id;
            return lEquals;
        }
        return false;
    }
}
