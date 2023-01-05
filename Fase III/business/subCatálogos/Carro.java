package business.subCatálogos;

/**
 * Write a description of class Carro here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public abstract class Carro
{
    //Variaveis de instancia
    private int id;
    private String marca;
    private String modelo;
    private int cilindrada;
    private int potenciaCombustao;
    private double PAC; // perfil aerodinâmico do carro (entre 0 e 1)
    private int fiabilidade;
    private boolean dnf;
    
    
    /* Construtores */
    public Carro()
    {
        this.id = 0;
        this.marca = "";
        this.modelo = "";
        this.cilindrada = 0;
        this.potenciaCombustao = 0;
        this.fiabilidade = 0;
        this.PAC = 0;
        this.dnf = false;
    }
    
    public Carro(int id, String marca, String modelo, int cilindrada, int potencia, int fiabilidade, double PAC)
    {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.cilindrada = cilindrada;
        this.potenciaCombustao = potencia;
        this.fiabilidade = fiabilidade; 
        this.PAC = PAC;
        this.dnf = false;
    }
    
    public Carro(Carro c)
    {
        this.id = c.getID();
        this.marca = c.getMarca();
        this.modelo = c.getModelo();
        this.cilindrada = c.getCilindrada();
        this.potenciaCombustao = c.getPotencia();
        this.fiabilidade = c.getFiabilidade();
        this.PAC = c.getPAC();
        this.dnf = c.getDNF();
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
    
    public int getPotencia()
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

    public boolean getDNF()
    {
        return this.dnf;
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
    
    public void setPotencia(int potencia)
    {
        this.potenciaCombustao = potencia;
    }

    public void setID(int id) {
        this.id = id;
    }

    public void setPAC(double PAC) {
        this.PAC = PAC;
    }
    
    public void setDNF(boolean b)
    {
        this.dnf = b;
    }
    
    
    /* Metodos usuais */
    public abstract Carro clone();
    
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
        sb.append("\nDNF: ");sb.append(this.dnf);
        return sb.toString();
    }
    
    public boolean equals(Object o)
    {
        if(this==o)
        return true;
        
        if(o==null || this.getClass()!=o.getClass())
        return false;
        
        Carro c = (Carro) o;
        return( this.marca.equals(c.getMarca()) &&
                this.modelo.equals(c.getModelo()) &&
                this.cilindrada == c.getCilindrada() &&
                this.potenciaCombustao == c.getPotencia() &&
                this.fiabilidade == c.getFiabilidade() &&
                this.id == c.getID() &&
                this.PAC == c.getPAC() ) &&
                this.dnf == c.getDNF();
    }

    
    
    public Double tempoProximaVolta(Circuito c, int clima, int volta, Piloto p1)
    {
        double t_medio = c.getTempoMedio();
        double t_chuva = c.getTempoDesvio();
        double minimum = 0; 
        double maximum = 5000;
        double fator_sorte = minimum + Double.valueOf(Math.random()*(maximum-minimum)).intValue();
        double maximum_chuva = 2000;
        double fator_sorte_chuva= minimum + Double.valueOf(Math.random()*(maximum_chuva-minimum)).intValue();
        return ((t_medio + (this.getCilindrada()/this.getPotencia())*1000) - fator_sorte 
                + (clima*(t_chuva - p1.getCTS()*1000)) - fator_sorte_chuva);
    
    }
    
    
    /**
     * define se o carro desiste (true desiste, false continua em prova)
     */
    public abstract boolean checkDNF(int volta,int totalvoltas,int clima);
}
