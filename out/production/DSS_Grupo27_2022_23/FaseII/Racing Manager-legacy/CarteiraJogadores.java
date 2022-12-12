
/**
 * Write a description of class CarteiraJogadores here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.io.Serializable;

public class CarteiraJogadores implements Serializable
{
    private Map<String,Jogador> jogadores; //key = nome jogador
    private Set<Jogador> classificacao;
    
    public CarteiraJogadores()
    {
        this.jogadores = new HashMap<String,Jogador>();
        this.classificacao = new TreeSet<Jogador>( new JogadorComparatorNome() );
    }
    
    public CarteiraJogadores(Map<String,Jogador> j, Set<Jogador> c)
    {
        this();
        for(String n : j.keySet())
        {
            this.jogadores.put(n, j.get(n).clone());
        }
        for(Jogador jog : c)
        {
            this.classificacao.add(jog.clone());
        }
    }
    
    public CarteiraJogadores(CarteiraJogadores c)
    {
        this.jogadores = c.getJogadores();
    }
    
    public Map<String,Jogador> getJogadores()
    {
        HashMap<String,Jogador> aux = new HashMap<String,Jogador>();
        for(String n : this.jogadores.keySet())
        {
            aux.put(n, this.jogadores.get(n).clone());
        }
        return aux;
    }
    
    //Metodos
    /**
     * adicionar um jogador
     */
    public void adicionarJogador(Jogador j)
    {
        this.jogadores.put(j.getNome(), j.clone());
    }
    
    /**
     * adicionar map de jogadores
     */
    public void adicionarJogador(Map<String,Jogador> j)
    {
        for(String n : j.keySet())
        {
            this.jogadores.put(n, j.get(n).clone());
        }
    }
    
    /**
     * Jogador por nome
     */
    public Jogador getJogador(String nome)
    {
        return this.jogadores.get(nome).clone();
    }
    
    /**
     * Fazer Aposta do jogador
     */
    public void apostar(String nome, Aposta a)
    {
        this.jogadores.get(nome).fazerAposta(a);
    }
    
    /**
    * Verificar se jogador existe
    */
    public boolean existeJogador(String nome)
    {
      return this.jogadores.containsKey(nome);
    }
    
    /**
     * Atualizar Apostas apos corrida
     */
    public void atualizarHistoricoApostas(int nrprova)
    {
        for(Jogador j : this.jogadores.values())
        {
            j.atualizarHistoricoApostas(nrprova);
        }
    }
    
    /**
     * Atualizar melhores apostadores
     */
    public void atualizarMelhoresApostadores()
    {
        this.classificacao.clear();
        for(Jogador j : this.jogadores.values())
        {
            this.classificacao.add(j.clone());
        }
    }
    
    /**
     * Lista os melhores apostadores
     */
    public String printMelhoresApostadores()
    {
        StringBuilder sb = new StringBuilder();
        int i=1;
        sb.append("\nMELHORES JOGADORES");
        sb.append("\n====================");
        sb.append("\nNome \t\t\tGanho");
        for(Jogador j : this.classificacao)
        {
            sb.append("\n");sb.append(i);sb.append("º ");sb.append(j.getNome());sb.append(" \t\t");
            sb.append(j.getGanho());
            i++;
        }
        return sb.toString();
    }
    
    /**
     * Vencedores das apostas na corrida
     */
    public Map<String,Integer> vencedores(int nrprova, Set<Carro> resultados)
    {
        HashMap<String,Integer> aux = new HashMap<String,Integer>();
        for(Jogador j : this.jogadores.values())
        {
            int tot = j.valorGanhoAposta(nrprova, resultados);
            if( tot > 0)
            aux.put(j.getNome(), tot);
        }
        return aux;
    }
    
    /**
     * Lista os vencedores e o total ganho da aposta na corrida
     */
    public String printVencedores(Map<String, Integer> res)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("\n\n||||| Vencedores das apostas |||||");
        sb.append("\nNome \t\tTotal Ganho");
        for(String s : res.keySet())
        {
            sb.append("\n");
            sb.append(s);sb.append("\t\t");sb.append(res.get(s));
        }
        return sb.toString();
    }
}
