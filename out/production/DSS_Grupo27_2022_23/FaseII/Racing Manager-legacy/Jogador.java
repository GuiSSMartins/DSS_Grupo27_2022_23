
/**
 * Write a description of class Jogador here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Set;
import java.io.Serializable;

public class Jogador implements Comparable<Jogador>,Serializable
{
  private String nome;
  private String morada;
  private int conta;
  private List<Aposta> historico;
  private List<Aposta> apostas;
  private int ganho;
  
  public Jogador()
  {
      this.nome = "";
      this.morada = "";
      this.conta = 0;
      this.historico = new ArrayList<Aposta>();
      this.apostas = new ArrayList<Aposta>();
      this.ganho = 0;
  }
  
  public Jogador(String n, String m, int c, List<Aposta> hist, List<Aposta> apostas, int ganho)
  {
      this();
      this.nome = n;
      this.morada = m;
      this.conta = c;
      for(Aposta a : hist)
      {
          this.historico.add(a.clone());
      };
      for(Aposta a : apostas)
      {
          this.apostas.add(a.clone());
      }
      this.ganho = 0;
  }
  
  public Jogador(Jogador j)
  {
      this.nome = j.getNome();
      this.morada = j.getMorada();
      this.conta = j.getConta();
      this.historico = j.getHistorico();
      this.apostas = j.getApostas();
      this.ganho = j.getGanho();
  }
  
  public String getNome()
  {
      return this.nome;
  }
  
  public String getMorada()
  {
      return this.morada;
  }
  
  public int getConta()
  {
    return this.conta;
  }
  
  public List<Aposta> getHistorico()
  {
      ArrayList<Aposta> aux = new ArrayList<Aposta>();
      for(Aposta a: this.historico)
      {
          aux.add(a.clone());
      }
      return aux;
  }
  
  public List<Aposta> getApostas()
  {
      ArrayList<Aposta> aux = new ArrayList<Aposta>();
      for(Aposta a: this.apostas)
      {
          aux.add(a.clone());
      }
      return aux;
  }
  
  public int getGanho()
  {
      return this.ganho;
    }
  
  public Jogador clone()
  {
      return new Jogador(this);
  }
  
  /**
   * Fazer aposta
   */
  public void fazerAposta(Aposta a)
  {
      this.apostas.add(a.clone());
      this.conta -= a.getValor();
  }
  
  /**
   * Verificar se ja fez aposta na prova
   */
  public boolean jaApostou(int nrprova)
  {
      boolean cenas = false;
      for(Aposta a : this.apostas)
      { 
          if(a.getCorrida() == (nrprova-1))
          cenas = true;
      }
      return cenas;
  }
  
  /**
   * Verificar se tem dinheiro para apostar
   */
  public boolean temDinheiro(int x)
  {
      return (x<=this.conta);
  }
  
  /**
   * atualizador de apostas
   */
  public void atualizarHistoricoApostas(int nrprova)
  { 
      ArrayList<Aposta> remover = new ArrayList<Aposta>();
      for(Aposta a : this.apostas)
      {
          if(a.getCorrida()<nrprova)
            {
                this.historico.add(a);
                remover.add(a);
            }
      }
      for(Aposta a : remover)
      {
          this.apostas.remove(a);
      }
  }
  
  /**
   * 
   */
  public int valorGanhoAposta(int nrprova, Set<Carro> resultados)
  {
      int carroscertos = 0;
      int total = 0;
      int i = 0;
      Iterator<Carro> it = resultados.iterator();
      
      for(Aposta a: this.apostas)
      {
          if((nrprova-1) == a.getCorrida())
          {
              while(it.hasNext() && i<3)
              {
                  Carro res = it.next();
                  for(Carro c :a.getCarros())
                  {
                    if(
                        //res.equals(c) nao pode ser porque os carros dos resultados
                        //vao ter tempo logo dará sempre falso
                        res.getMarca().equals(c.getMarca()) &&
                        res.getModelo().equals(c.getModelo()) &&
                        res.getEquipa().getNome().equals(c.getEquipa().getNome()) &&
                        res.getPotencia() == c.getPotencia() &&
                        res.getCilindrada() == c.getCilindrada()                    
                    )
                        carroscertos++;
                  }
                  i++;
              }
              total = (int) calculoAposta(a,carroscertos);
              a.setGanho(total);
          }
      }
      
      this.conta += total;
      this.ganho += total;
      return total;
  }
  
  private double calculoAposta(Aposta a, int nrcarros)
  {
      if(nrcarros == 3)
      {
          return a.getValor()*2;
      }
      if(nrcarros == 2)
      {
          return a.getValor()*1.5;
      }
      if(nrcarros == 1)
      {
          return a.getValor()*1.25;
      }
      
      return 0;
  }
  
  /**
   * Informacao do jogador
   */
  public String printInfo()
  {
      StringBuilder sb = new StringBuilder();
      sb.append("\nNome: ");sb.append(this.nome);sb.append("\tMorada: ");sb.append(this.morada);
      sb.append("\tConta: "); sb.append(this.conta);
      sb.append("\tGanho: "); sb.append(this.ganho);
      sb.append("\n\nApostas em Vigor");
      sb.append("\n=====================");
      for(Aposta a : this.apostas)
      {
          sb.append(a.toString());
      }
      sb.append("\n\nHistorico de Apostas");
      sb.append("\n=====================");
      for(Aposta a : this.historico)
      {
          sb.append(a.toString());
      }
      return sb.toString();
  }
  
  public int compareTo(Jogador j)
    {
        if(this.ganho > j.getGanho())
            return -1;
        if(this.ganho < j.getGanho())
            return 1;
        else 
            return 0;
    }
}
