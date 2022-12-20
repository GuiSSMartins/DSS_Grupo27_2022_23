
/**
 * Write a description of class GT here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

package business.subCatálogos;


public class GT extends Carro
{
    private double taxaDeterioracao;

    public GT()
    {
        super();
    }
    
    public GT(String marca, String modelo, int cilindrada, int potencia, int fiabilidade, int id, double PAC, double taxaDeterioracao)
    {
        super(marca,modelo,cilindrada,potencia,fiabilidade,id,PAC);
        this.taxaDeterioracao = taxaDeterioracao;
    }
    
    public GT(GT p)
    {
        super(p);
        this.taxaDeterioracao = p.getTaxaDeterioracao();
    }
    
    public GT clone()
    {
        return new GT(this);
    }
    
     
    public boolean equals(Object o)
    {
        if(this==o)
        return true;
        
        if(o==null || this.getClass()!=o.getClass())
        return false;
        
        GT c = (GT) o;
        return ( super.equals(c));
    }

    public double getTaxaDeterioracao(){
        return this.taxaDeterioracao;
    }

    public void setTaxaDeterioracao(double taxaDeterioracao){
        this.taxaDeterioracao = taxaDeterioracao;
    }
}
