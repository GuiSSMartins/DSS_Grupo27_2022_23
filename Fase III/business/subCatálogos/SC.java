
/**
 * Write a description of class SC here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
package business.subCatálogos;

public class SC extends Carro
{
    public SC()
    {
        super();
    }
    
    public SC(String marca, String modelo, int potencia, int fiabilidade, int id, double PAC)
    {
        super(marca,modelo,2500,potencia,fiabilidade,id,PAC);
    }
    
    public SC(SC p)
    {
        super(p);
    }
    
    public SC clone()
    {
        return new SC(this);
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
