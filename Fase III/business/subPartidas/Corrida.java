
/**
 * Write a description of class Corrida here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
package business.subPartidas;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import business.subCatálogos.Carro;
import business.subCatálogos.Circuito;
import business.subCatálogos.Hibrido;

import java.util.Random;
import java.util.Iterator;
import java.io.Serializable;

public class Corrida implements Serializable
{
    //variaveis de instancia
    private List<Progresso> listaProgressos;
    private Circuito circuito;
    private Set<Progresso> resultados;
    //private Map<Carro,double> bestLap;
    private List<Progresso> primeiroVolta;
    private int clima; //1-chove 0-sol
    private int versaoJogo;
    
    //Construtores
    public Corrida()
    {
        this.versaoJogo = 0;
        this.listaProgressos = new ArrayList<Progresso>();
        this.circuito = new Circuito();
        this.resultados = new TreeSet<Progresso>();
        //this.bestLap = new HashMap<Carro,double>();
        this.primeiroVolta = new ArrayList<Progresso>();
        Random rand=new Random();
        int x=rand.nextInt(2);
        this.clima = x;
    }
    
    
    public Corrida(List<Progresso> p, Circuito c, Set<Progresso> r, List<Progresso> pr, int clima, int versaoJogo)
    {
        this();
        for(Progresso prog: p)
        {
            this.listaProgressos.add(prog);
        }
        // this.circuito = c.clone();
        for(Progresso car: r)
        {
            this.resultados.add(car.clone());
        }
        for(Progresso x : pr)
        {
            this.primeiroVolta.add(x.clone());
        }
        this.clima = clima;
        this.versaoJogo = versaoJogo;
    }
    
   
    
    public Corrida(Corrida c)
    {
        this.listaProgressos = c.getProgressos();
        this.circuito = c.getCircuito();
        this.resultados = c.getResultados();
        this.primeiroVolta = c.getPrimeiroVolta();
        this.clima = c.getClima();
        this.versaoJogo = c.getVersaoJogo();
    }
    
    
    //Gets e sets
    public List<Progresso> getProgressos()
    {
        ArrayList<Progresso> aux = new ArrayList<Progresso>();
        for(Progresso c: this.listaProgressos)
        {
            aux.add(c.clone());
        }
        return aux;
    }
    
    public Circuito getCircuito()
    {
        return this.circuito.clone();
    }
    
    
    public Set<Progresso> getResultados()
    {
        TreeSet<Progresso> aux = new TreeSet<Progresso>();
        for(Progresso c : this.resultados)
        {
            aux.add(c.clone());
        }
        return aux;
    }
    
    public int getClima()
    {
        return this.clima;
    }

    public int getVersaoJogo()
    {
        return this.versaoJogo;
    }
    
    
    public List<Progresso> getPrimeiroVolta()
    {
        ArrayList<Progresso> aux = new ArrayList<Progresso>();
        for(Progresso c : this.primeiroVolta)
        {
            aux.add(c.clone());
        }
        return aux;
    }
 
 
    public void setCircuito(Circuito c)
    {
        this.circuito = c.clone();
    }
 
    //Metodos
    
    public Corrida clone()
    {
       return new Corrida(this);
    }
   
   /**
     * Adicionar um progresso á lista
     */
    public void adicionarProgresso(Progresso c)
    {
        this.listaProgressos.add(c.clone());
    }
    
    /**
     * adicionar lista de progressos
     */
    public void adicionarProgresso(List<Progresso> l)
    {
        for(Progresso c : l)
        {
            this.listaProgressos.add(c.clone());
        }
    }
    
    /**
     * Numero total de progressos
     */
    public int totalProgressos()
    {
        return this.listaProgressos.size();
    }
    
    /**
     * Remover um progresso
     */
    public void removerProgresso(Progresso p)
    {
        this.listaProgressos.remove(p);
    }
    
    /**
     * Limpa a lista de progressos
     */
    public void limpaListaProgressos()
    {
      this.listaProgressos.clear();
    }
    
    /**
     * Simula a corrida
     */   
    public void simulaCorrida()
    {
        int voltas = this.circuito.getNVoltas();
        double t_aux, t_volta;
        ArrayList<Progresso> aux = new ArrayList<Progresso>();
        HashMap<Progresso,Integer> temp = new HashMap<Progresso,Integer>();
        for(Progresso c : this.listaProgressos)
        {
            aux.add(c.clone());
        }
        for(int i=0; i<voltas; i++)
        {
            for(Progresso c : aux)
            { 
                if(c.getCarro().getDNF()==false) //verifica se o carro esta acidentado
                {
                    if(c.getCarro().checkDNF(i,voltas,this.clima)==true) //verifica se o carro tem acidente na volta
                    {
                        c.getCarro().setDNF(true);
                        temp.put(c.clone(),i);
                    }
                    else
                    {
                        t_aux = c.getTempo(); //tempo feito ate ao momento
                        Hibrido h = (Hibrido)c;
                        int motor = h.getPotenciaEletrico();
                        t_volta = c.getCarro().tempoProximaVolta(this.circuito, 0, i, c.getPiloto()) - motor*10;
                        c.setTempo(t_aux +t_volta); 
                    }
                    
                }
            }
            this.primeiroVolta(i,aux); //metodo auxiliar para determinar o 1º a cada volta
        }
    }
    
    /**
     * Metodo auxiliar privado para determinar o carro que vai em 1o a cada volta
     */
    private void primeiroVolta(int volta, List<Progresso> l)
    {
        l.sort(null); 
       Iterator<Progresso> it = l.iterator();
       boolean f = false;
       Progresso c = null;
       while(it.hasNext() && f==false)
       {
           c = it.next();
       }
       if(c!=null)
            this.primeiroVolta.add(volta,c.clone());
    }
    
   
   /**
    * Lista de participantes da corrida
    */
   public String listaCarrosParticipantes()
   {
       StringBuilder sb = new StringBuilder();
       int i = 1;
       for(Progresso p : this.listaProgressos)
       {
            Carro c = p.getCarro();
            sb.append("\n");
            sb.append(i);sb.append(" - ");sb.append(c.getMarca());sb.append(" ");sb.append(c.getModelo());
            i++;
       }
       return sb.toString();
   }
}
