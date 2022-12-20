
/**
 * Write a description of class PC1H here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
package business.subCatálogos;

public class C1Hibrido extends C1 implements Hibrido
{
    private int potenciaEletrico;
    
    public C1Hibrido()
    {
       super();
       this.potenciaEletrico = 0;
    }
    
    public C1Hibrido(String marca, String modelo, int potencia, int fiabilidade,int id, double PAC, int eletrico)
    {
        super(marca,modelo,potencia,fiabilidade,id,PAC);
        this.potenciaEletrico = eletrico;
    }
    
    public C1Hibrido(C1Hibrido p)
    {
        super(p);
        this.potenciaEletrico = p.getPotenciaEletrico();
    }
    
    public C1Hibrido clone()
    {
        return new C1Hibrido(this);
    }
    
    public int getPotenciaEletrico()
    {
        return this.potenciaEletrico;
    }
    
    public void setPotenciaEletrico(int potencia)
    {
        this.potenciaEletrico = potencia;
    }
    
    public boolean equals(Object o)
    {
        if(this==o)
        return true;
        
        if(o==null || this.getClass()!=o.getClass())
        return false;
        
        C1Hibrido c = (C1Hibrido) o;
        return ( super.equals(c) && this.potenciaEletrico == c.getPotenciaEletrico());
    }    
}
