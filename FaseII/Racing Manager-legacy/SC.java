
/**
 * Write a description of class SC here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Map;
import java.util.Random;

public class SC extends Carro
{
    public SC()
    {
        super();
    }
    
    public SC(String marca, String modelo, int cilindrada, int potencia, Equipa e)
    {
        super(marca,modelo,cilindrada,potencia,e,0);
    }
    
    public SC(SC p)
    {
        super(p);
    }
    
    public SC clone()
    {
        return new SC(this);
    }
    
    public boolean DNF(int volta,int totalvoltas, int chuva)
    {
       Random rand=new Random();
       int x=rand.nextInt(73);
       Piloto p = null;
       if(volta<totalvoltas/2)
           p = super.getEquipa().getPiloto1();
       else
           p = super.getEquipa().getPiloto2();
       int qualidade;
       if(chuva == 1)
            qualidade = p.getQualidadeChuva();
       else
            qualidade = p.getQualidade();
       //no maximo fiabilidade de 70%
       int fiabilidade = (int)(qualidade*0.75) + (int)((super.getCilindrada()/10)*0.25);
       //System.out.println("Fiabilidade: "+fiabilidade);
       //System.out.println("Random: "+x);
       return (x > fiabilidade);
    }    
     
    public boolean equals(Object o)
    {
        if(this==o)
        return true;
        
        if(o==null || this.getClass()!=o.getClass())
        return false;
        
        SC c = (SC) o;
        return ( super.equals(c));
    }
}
