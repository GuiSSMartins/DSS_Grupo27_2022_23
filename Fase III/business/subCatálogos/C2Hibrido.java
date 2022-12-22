
/**
 * Write a description of class PC2H here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
package business.subCatálogos;

public class C2Hibrido extends Hibrido
{
    
    public C2Hibrido()
    {
        super();
    }
    
    public C2Hibrido(int id, String marca, String modelo, int cilindrada, int potencia, int fiabilidade, double PAC, int eletrico)
    {
        super(id,marca,modelo,cilindrada,potencia,fiabilidade,PAC,eletrico);
    }
    
    public C2Hibrido(C2Hibrido p)
    {
        super(p);
    }
    
    public C2Hibrido clone()
    {
        return new C2Hibrido(this);
    }
    
    public boolean equals(Object o)
    {
        if(this==o)
        return true;
        
        if(o==null || this.getClass()!=o.getClass())
        return false;
        
        C2Hibrido c = (C2Hibrido) o;
        return ( super.equals(c));
    }   
}
