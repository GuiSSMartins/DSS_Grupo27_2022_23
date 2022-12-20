    
/**
 * Write a description of class PC1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
package business.subCatálogos;

public class C1 extends Carro
{
    public C1()
    {
        super();
    }
    
    public C1(String marca, String modelo, int potencia, int fiabilidade, int id, double PAC)
    {
        super(marca,modelo,6000,potencia,fiabilidade,id,PAC);
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
}
