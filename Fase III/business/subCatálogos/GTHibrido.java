package business.subCatálogos;


/**
 * Write a description of class GTH here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class GTHibrido extends Hibrido
{
    private double taxaDeterioracao;

    public GTHibrido()
    {
        super();
    }
    
    public GTHibrido(int id, String marca, String modelo, int cilindrada, int potencia, int fiabilidade, double PAC,  double taxaDeterioracao, int eletrico)
    {
        super(id,marca,modelo,cilindrada,potencia,fiabilidade,PAC,eletrico);
        this.taxaDeterioracao=taxaDeterioracao;
    }
    
    public GTHibrido(GTHibrido p)
    {
        super(p);
        this.taxaDeterioracao = p.getTaxaDeterioracao();
    }
    
    public GTHibrido clone()
    {
        return new GTHibrido(this);
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
        return ( super.equals(c));
    }

    public double getTaxaDeterioracao(){
        return this.taxaDeterioracao;
    }

    public void setTaxaDeterioracao(double taxaDeterioracao){
        this.taxaDeterioracao = taxaDeterioracao;
    }
}
