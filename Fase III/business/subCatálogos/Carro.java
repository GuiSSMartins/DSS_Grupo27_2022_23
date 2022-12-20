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
    private String marca;
    private String modelo;
    private int cilindrada;
    private int potenciaCombustao;
    private double PAC;
    private int fiabilidade;
    private int id;
    
    /* Construtores */
    public Carro()
    {
        this.marca = "";
        this.modelo = "";
        this.cilindrada = 0;
        this.potenciaCombustao = 0;
        this.fiabilidade = 0;
        this.PAC = 0;
        this.id = 0;
    }
    
    public Carro(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, int id, double PAC)
    {
        this.marca = marca;
        this.modelo = modelo;
        this.cilindrada = cilindrada;
        this.potenciaCombustao = potencia;
        this.fiabilidade = fiabilidade; 
        this.id = id;
        this.PAC = PAC;
    }
    
    public Carro(Carro c)
    {
       this.marca = c.getMarca();
       this.modelo = c.getModelo();
       this.cilindrada = c.getCilindrada();
       this.potenciaCombustao = c.getPotencia();
       this.fiabilidade = c.getFiabilidade();
       this.id = c.getID();
       this.PAC = c.getPAC();
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
    
    
    /* Metodos usuais */
    public abstract Carro clone();
    
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("\nMarca: ");sb.append(this.marca);
        sb.append("\nModelo: ");sb.append(this.modelo);
        sb.append("\nCilindrada: ");sb.append(this.cilindrada);
        sb.append("\nPotencia: ");sb.append(this.potenciaCombustao);
        sb.append("\nFiabiliade: ");sb.append(this.fiabilidade);
        sb.append("\nID: ");sb.append(this.id);
        sb.append("\nPAC: ");sb.append(this.PAC);
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
                this.PAC == c.getPAC() );
    }

    
    //Outros metodos
    /**
     * Tempo em milisegundos de uma volta

    public long tempoProximaVolta(Circuito c, int clima, int volta)
    {
        Piloto p1 = this.getEquipa().getPiloto1();
        Piloto p2 = this.getEquipa().getPiloto2();
        Map<String,Long> aux = c.getTemposMedios();
        long t_medio = aux.get(this.getClass().getName());
        long t_chuva = c.getTempoDesvio();
        long minimum = 0;
        long maximum = 5000;
        long fator_sorte = minimum + Double.valueOf(Math.random()*(maximum-minimum)).intValue();
        long maximum_chuva = 2000;
        long fator_sorte_chuva= minimum + Double.valueOf(Math.random()*(maximum_chuva-minimum)).intValue();
        
        if(volta<(c.getVoltas()/2))
        {
            // usa piloto 1
            return (t_medio + ((this.getCilindrada()/this.getPotencia())-p1.getQualidade())*1000) - fator_sorte 
                    + (clima*(t_chuva - p1.getQualidadeChuva()*1000)) - fator_sorte_chuva;
        }
        else
        {   
            //usa piloto 2
            if(volta == (c.getVoltas()/2))
            {
                return (t_medio + ((this.getCilindrada()/this.getPotencia())-p2.getQualidade())*1000) - fator_sorte 
                    + (clima*(t_chuva - p2.getQualidadeChuva()*1000)) - fator_sorte_chuva + c.getTempoBox();
            }
            else
            return (t_medio + ((this.getCilindrada()/this.getPotencia())-p2.getQualidade())*1000) - fator_sorte 
                    + (clima*(t_chuva - p2.getQualidadeChuva()*1000)) - fator_sorte_chuva;
        }
    }
    **/
    
}
