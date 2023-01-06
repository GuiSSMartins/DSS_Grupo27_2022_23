package business.subCatálogos;


/**
 * Write a description of class GTH here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class GTHibrido extends GT implements Hibrido
{
    private int potenciaEletrico;
    
    public GTHibrido()
    {
        super();
        this.potenciaEletrico = 0;
    }
    
    public GTHibrido(int id, String marca, String modelo, int cilindrada, int potencia, int fiabilidade, double PAC, double taxaDeterioracao, int eletrico)
    {
        super(id,marca,modelo,cilindrada,potencia+eletrico,fiabilidade,PAC,taxaDeterioracao);
        this.potenciaEletrico = eletrico;
    }
    
    public GTHibrido(GTHibrido p)
    {
        super(p);
        this.potenciaEletrico = p.getPotenciaEletrico();
    }
    
    public GTHibrido clone()
    {
        return new GTHibrido(this);
    }
    
    public int getPotenciaEletrico()
    {
        return this.potenciaEletrico;
    }

    public void setPotenciaEletrico(int potencia)
    {
        this.potenciaEletrico = potencia;
    }

    public boolean verificaCilindrada(int cilindrada) {
        if (cilindrada >= 2000 && cilindrada <= 4000) return true;
        return false;
    }
    
    public boolean equals(Object o)
    {
        if(this==o)
        return true;
        
        if(o==null || this.getClass()!=o.getClass())
        return false;
        
        GTHibrido c = (GTHibrido) o;
        return ( super.equals(c) && this.potenciaEletrico == c.getPotenciaEletrico());
    }   
}