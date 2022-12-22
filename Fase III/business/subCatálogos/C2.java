
/**
 * Write a description of class PC2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
package business.subCatálogos;

public class C2 extends Carro
{
    
    public C2()
    {
        super();
    }
    
    public C2(int id, String marca, String modelo, int cilindrada, int potencia, int fiabilidade, double PAC)
    {
        super(id,marca,modelo,cilindrada,potencia,fiabilidade,PAC);
    }
    
    public C2(C2 p)
    {
        super(p);
    }
    
    public C2 clone()
    {
        return new C2(this);
    }

    public boolean verificaCilindrada(int cilindrada) {
        if (cilindrada >= 3000 && cilindrada <= 5000) return true;
        return false;
    }
    
    public boolean equals(Object o)
    {
        if(this==o)
        return true;
        
        if(o==null || this.getClass()!=o.getClass())
        return false;
        
        C2 c = (C2) o;
        return ( super.equals(c));
    }
}
