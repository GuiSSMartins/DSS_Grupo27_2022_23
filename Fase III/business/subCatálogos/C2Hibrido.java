
/**
 * Write a description of class PC2H here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
package business.subCatálogos;

public class C2Hibrido extends C2 implements Hibrido
{
    private int potenciaEletrico;
    
    public C2Hibrido()
    {
        super();
        this.potenciaEletrico = 0;
    }
    
    public C2Hibrido(String marca, String modelo, int cilindrada, int potencia, int fiabilidade,int id, double PAC, int eletrico)
    {
        super(marca,modelo,cilindrada,potencia,fiabilidade,id,PAC);
        this.potenciaEletrico = eletrico;
    }
    
    public C2Hibrido(C2Hibrido p)
    {
        super(p);
        this.potenciaEletrico = p.getPotenciaEletrico();
    }
    
    public C2Hibrido clone()
    {
        return new C2Hibrido(this);
    }
    
    public int getPotenciaEletrico()
    {
        return this.potenciaEletrico;
    }
    
    public void setPotenciaEletrico(int e)
    {
        this.potenciaEletrico = e; 
    }

    
    public boolean equals(Object o)
    {
        if(this==o)
        return true;
        
        if(o==null || this.getClass()!=o.getClass())
        return false;
        
        C2Hibrido c = (C2Hibrido) o;
        return ( super.equals(c) && this.potenciaEletrico == c.getPotenciaEletrico());
    }   
}
