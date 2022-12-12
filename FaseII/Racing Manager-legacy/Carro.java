
/**
 * Write a description of class Carro here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Map;
import java.io.Serializable;

public abstract class Carro implements Comparable<Carro>,Serializable
{
    //Variaveis de instancia
    private String marca;
    private String modelo;
    private int cilindrada;
    private int potencia;
    private Equipa equipa;
    private int fiabilidade;
    private long tempo;
    private boolean dnf;
    
    /* Construtores */
    public Carro()
    {
        this.marca = "";
        this.modelo = "";
        this.cilindrada = 0;
        this.potencia = 0;
        this.equipa = new Equipa();
        this.fiabilidade = 0;
        this.tempo = 0;
        this.dnf = false;
    }
    
    public Carro(String marca, String modelo, int cilindrada, int potencia, Equipa e, int fiabilidade)
    {
        this.marca = marca;
        this.modelo = modelo;
        this.cilindrada = cilindrada;
        this.potencia = potencia;
        this.equipa = e.clone();
        this.fiabilidade = fiabilidade; 
        this.tempo = 0;
        this.dnf = false;
    }
    
    public Carro(Carro c)
    {
       this.marca = c.getMarca();
       this.modelo = c.getModelo();
       this.cilindrada = c.getCilindrada();
       this.potencia = c.getPotencia();
       this.equipa = c.getEquipa();
       this.fiabilidade = c.getFiabilidade();
       this.tempo = c.getTempo();
       this.dnf = c.getDNF();
    }
    
    /* Gets e sets */
    public long getTempo()
    {
        return this.tempo;
    }
    
    
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
        return this.potencia;
    }
    
    public Equipa getEquipa()
    {
        return this.equipa.clone();
    }
    
    public int getFiabilidade()
    {
        return this.fiabilidade;
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
        this.potencia = potencia;
    }
    
    public void setEquipa(Equipa e)
    {
        this.equipa = e.clone();
    }
    
    public void setTempo(long t)
    {
        this.tempo = t;
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
        sb.append("\nMarca: ");sb.append(this.marca);
        sb.append("\nModelo: ");sb.append(this.modelo);
        sb.append("\nCilindrada: ");sb.append(this.cilindrada);
        sb.append("\nPotencia: ");sb.append(this.potencia);
        sb.append("\nFiabiliade: ");sb.append(this.fiabilidade);
        sb.append("\nTempo: ");sb.append(this.tempo);
        sb.append("\nDNF: ");sb.append(this.dnf);
        sb.append(this.equipa.toString());
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
                this.potencia == c.getPotencia() &&
                this.equipa.equals(c.getEquipa()) &&
                this.fiabilidade == c.getFiabilidade() &&
                this.tempo == c.getTempo() &&
                this.dnf == c.getDNF());
    }
    
    public int compareTo(Carro c)
    {
        if(this.tempo < c.getTempo())
            return -1;
        if(this.tempo > c.getTempo())
            return 1;
        else 
            return 0;
    }
    
    //Outros metodos
    /**
     * Tempo em milisegundos de uma volta
     */
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
            /* usa piloto 1*/
            return (t_medio + ((this.getCilindrada()/this.getPotencia())-p1.getQualidade())*1000) - fator_sorte 
                    + (clima*(t_chuva - p1.getQualidadeChuva()*1000)) - fator_sorte_chuva;
        }
        else
        {   
            /*usa piloto 2*/
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
    
    /**
     * define se o carro desiste (true desiste, false continua em prova)
     */
    public abstract boolean DNF(int volta,int totalvoltas,int clima);
    
}
