
/**
 * Write a description of class Circuito here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;

public class Circuito implements Serializable
{
    /* Variaveis instancia */
    private String nome;
    private int distancia;
    private int voltas;
    //private long tempoMedio;
    private Map<String,Long> temposMedios;
    private long tempoDesvio;
    private long tempoBox;
    private Record record;
    
    /* Construtores */
    public Circuito()
    {
        this.nome = "";
        this.distancia = 0;
        this.voltas = 0;
        this.temposMedios = new HashMap<String, Long>();
        this.tempoDesvio = 0;
        this.tempoBox = 0;
        this.record = null;
    }
    
    public Circuito(String n,int d, int v, Map<String, Long> m, long ds, long b, Record r)
    {
        this.nome = n;
        this.distancia = d;
        this.voltas = v;
        HashMap<String,Long> aux = new HashMap<String, Long>();
        if(m == null)
        {
            this.temposMedios = new HashMap<String, Long>();
        }
        else
        {
            for(String g : m.keySet())
            {
                aux.put(g, m.get(g));
            }
        }
        this.temposMedios = aux;
        this.tempoDesvio = ds;
        this.tempoBox = b;
        this.record = r.clone();

    }
    
    public Circuito(Circuito c)
    {
        this.nome = c.getNome();
        this.distancia = c.getDistancia();
        this.voltas = c.getVoltas();
        this.temposMedios = c.getTemposMedios();
        this.tempoDesvio = c.getTempoDesvio();
        this.tempoBox = c.getTempoBox();
        this.record = c.getRecord();
    }
    
    /* Gets e Sets */
    public String getNome()
    {
        return this.nome;
    }
    
    public int getDistancia()
    {
        return this.distancia;
    }
    
    public int getVoltas()
    {
        return this.voltas;
    }
    
    public Map<String,Long> getTemposMedios()
    {
        HashMap<String,Long> aux = new HashMap<String, Long>();
        for(String g : this.temposMedios.keySet())
        {
            aux.put(g, this.temposMedios.get(g));
        }
        return aux;
    }
    
    public long getTempoDesvio()
    {
        return this.tempoDesvio;
    }
    
    public long getTempoBox()
    {
        return this.tempoBox;
    }
    
    public Record getRecord()
    {
        return this.record.clone();
    }
    
    public void setNome(String n)
    {
        this.nome = n;
    }
    
    public void setDistancia(int d)
    {
        this.distancia = d;
    }
    
    public void setVoltas(int v)
    {
        this.voltas = v;
    }
    
    public void setTempoDesvio(long ds)
    {
        this.tempoDesvio = ds;
    }
    
    public void setTempoBox(long b)
    {
        this.tempoBox = b;
    }
    
    public void setRecord(Record r)
    {
        this.record = r.clone();
    }
    
    public void setTempoMedio(String categoria, long tempo)
    {
        this.temposMedios.put(categoria, tempo);
    }
    
    /* Metodos usuais */
    public Circuito clone()
    {
        return new Circuito(this);
    }
    
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("\nNome: ");sb.append(this.nome);
        sb.append("\nDistancia: ");sb.append(this.distancia);
        sb.append("\nNumero de voltas: ");sb.append(this.voltas);
        //sb.append("\nTempo Medio: ");sb.append(TimeConverter.toTimeFormat(this.tempoMedio));
        sb.append("\nDesvio Tempo: ");sb.append(TimeConverter.toTimeFormat(this.tempoDesvio));
        sb.append("\nTempo Box: ");sb.append(TimeConverter.toTimeFormat(this.tempoBox));
        sb.append("\nRecord: ");sb.append(this.record.toString());
        return sb.toString();
    }
    
    public boolean equals(Object o)
    {
       if(this == o)
       return true;
       
       if(o == null || this.getClass() != o.getClass())
       return false;
       
       Circuito c = (Circuito) o;
       return ( this.nome.equals(c.getNome()) &&
                this.distancia == c.getDistancia() &&
                this.voltas == c.getVoltas() &&
                //this.tempoMedio == c.getTempoMedio() &&
                this.tempoDesvio == c.getTempoDesvio() &&
                this.tempoBox == c.getTempoBox() &&
                this.record.equals(c.getRecord()));
    }
    
}
