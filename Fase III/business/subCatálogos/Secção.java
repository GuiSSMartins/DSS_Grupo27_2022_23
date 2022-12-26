package business.subCatálogos;

public class Secção {

    private int GDU;
    private String tipo;
    private int id;
    private int posicao;
    private String nomeCircuito;
    private double comprimento;


    public Secção(){

    }

    public Secção(int id, String tipo, int GDU, int posicao, String nomeCircuito, double comprimento){
        this.id = id;
        this.tipo = tipo;
        this.GDU = GDU;
        this.posicao = posicao;
        this.nomeCircuito = nomeCircuito;
        this.comprimento = comprimento;
    }

    public Secção(Secção s){
        this.id = s.getID();
        this.tipo = s.getTipo();
        this.GDU = s.getGDU();
        this.posicao = s.getPosicao();
        this.nomeCircuito = s.getNomeCircuito();
        this.comprimento = s.getComprimento();
    }

    public double getComprimento()
    {
        return this.comprimento;
    }

    public void setComprimento(double c)
    {
        this.comprimento=c;
    }

    public String getNomeCircuito() {
        return this.nomeCircuito;
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

    public int getPosicao() {
        return this.posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
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
            lEquals &= this.comprimento == lSecçãoObject.comprimento;
            return lEquals;
        }
        return false;
    }

    public double getTempoMedio(){
        return this.comprimento/this.getVelocidadeMedia();
    }

    public double getTempoDesvio(){
        return this.comprimento/this.getVelocidadeDesvio();
    }

    public double getVelocidadeMedia()
    {
        switch(tipo){
            case "Reta":
                return 180;
            case "Curva":
                return 120;
            case "Chicane":
                return 80;
            default:
                return 0;
        }
    }

    public double getVelocidadeDesvio()
    {
        switch(tipo){
            case "Reta":
                return 150;
            case "Curva":
                return 80;
            case "Chicane":
                return 50;
            default:
                return 0;
        }
    }

    public Secção clone(){
        return new Secção(this);
    }

}
