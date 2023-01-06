    
/**
 * Write a description of class PC1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
package business.subCatálogos;

import java.util.Random;

public class C1 extends Carro
{
    public C1()
    {
        super();
    }
    
    public C1(int id, String marca, String modelo, int potencia, int fiabilidade, double PAC)
    {
        super(id,marca,modelo,6000,potencia,fiabilidade,PAC);
    }
    
    public C1(C1 p)
    {
        super(p);
    }
    
    public C1 clone()
    {
        return new C1(this);
    }

    public boolean verificaFiabilidade(int fiabilidade) {
        if (fiabilidade >= 90 && fiabilidade <= 100) return true;
        return false;
    }

    
    public boolean equals(Object o)
    {
        if(this==o)
        return true;
        
        if(o==null || this.getClass()!=o.getClass())
        return false;
        
        C1 c = (C1) o;
        return ( super.equals(c));
    }

    public boolean checkDNF(int volta,int totalvoltas,int clima)
    {
       Random rand=new Random();
       int x=rand.nextInt(87);
       return (x > super.getFiabilidade());
       //return false;
    }
}
