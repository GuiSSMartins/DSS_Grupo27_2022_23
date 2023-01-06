package business.subCatálogos;



/**
 * Write a description of interface Hibrido here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Hibrido
{
    //Variaveis de instancia
    private int id;
    private String marca;
    private String modelo;
    private int cilindrada;
    private int potenciaCombustao;
    private double PAC;
    private int fiabilidade;
    private int potenciaEletrico;

    /* Construtores */
    public Hibrido()
    {
        this.id = 0;
        this.marca = "";
        this.modelo = "";
        this.cilindrada = 0;
        this.potenciaCombustao = 0;
        this.fiabilidade = 0;
        this.PAC = 0;
        this.potenciaEletrico = 0;
    }
    
    public Hibrido(int id, String marca, String modelo, int cilindrada, int potencia, int fiabilidade, double PAC, int potenciaEletrico)
    {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.cilindrada = cilindrada;
        this.potenciaCombustao = potencia;
        this.fiabilidade = fiabilidade; 
        this.PAC = PAC;
        this.potenciaEletrico = potenciaEletrico;
    }
    
    public Hibrido(Hibrido c)
    {
        this.id = c.getID();
        this.marca = c.getMarca();
        this.modelo = c.getModelo();
        this.cilindrada = c.getCilindrada();
        this.potenciaCombustao = c.getPotenciaCombustao();
        this.fiabilidade = c.getFiabilidade();
        this.PAC = c.getPAC();
        this.potenciaEletrico = c.getPotenciaEletrico();
    }

    /* Gets e sets */
    
    
    public String getMarca()
    {
        return this.marca;
    }
    
    public String getModelo()
    {
        return this.modelo;
    }
    
    public int getCilindrada()
    {
        return this.cilindrada;
    }
    
    public int getPotenciaCombustao()
    {
        return this.potenciaCombustao;
    }
    
    public int getFiabilidade()
    {
        return this.fiabilidade;
    }

    public int getID() {
        return this.id;
    }

    public double getPAC() {
        return this.PAC;
    }

    public int getPotenciaEletrico()
    {
        return this.potenciaEletrico;
    }
    
    public void setMarca(String marca)
    {
        this.marca = marca;
    }
    
    public void setModelo(String modelo)
    {
        this.modelo = modelo;
    }
    
    public void setCilindrada(int cilindrada)
    {
        this.cilindrada = cilindrada;
    }
    
    public void setPotenciaCombustao(int potencia)
    {
        this.potenciaCombustao = potencia;
    }

    public void setID(int id) {
        this.id = id;
    }

    public void setPAC(double PAC) {
        this.PAC = PAC;
    }

    public void setPotenciaEletrico(int potencia)
    {
        this.potenciaEletrico = potencia;
    }

    /* Metodos usuais */
    public abstract Hibrido clone();
    
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("\nID: ");sb.append(this.id);
        sb.append("\nMarca: ");sb.append(this.marca);
        sb.append("\nModelo: ");sb.append(this.modelo);
        sb.append("\nCilindrada: ");sb.append(this.cilindrada);
        sb.append("\nPotencia: ");sb.append(this.potenciaCombustao);
        sb.append("\nFiabiliade: ");sb.append(this.fiabilidade);
        sb.append("\nPAC: ");sb.append(this.PAC);
        return sb.toString();
    }
    
    public boolean equals(Object o)
    {
        if(this==o)
        return true;
        
        if(o==null || this.getClass()!=o.getClass())
        return false;
        
        Hibrido c = (Hibrido) o;
        return( this.marca.equals(c.getMarca()) &&
                this.modelo.equals(c.getModelo()) &&
                this.cilindrada == c.getCilindrada() &&
                this.potenciaCombustao == c.getPotenciaCombustao() &&
                this.fiabilidade == c.getFiabilidade() &&
                this.id == c.getID() &&
                this.PAC == c.getPAC() );
    }
}
