
/**
 * Write a description of class PC1H here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
package business.subCatálogos;

public class C1Hibrido extends Hibrido
{
    public C1Hibrido()
    {
       super();
    }


    public C1Hibrido(int id, String marca, String modelo, int cilindrada, int potencia, int fiabilidade, double PAC, int eletrico)
    {
        super(id,marca,modelo,cilindrada,potencia,fiabilidade,PAC,eletrico);
    }
    
    public C1Hibrido(C1Hibrido p)
    {
        super(p);
    }
    
    public C1Hibrido clone()
    {
        return new C1Hibrido(this);
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
        
        C1Hibrido c = (C1Hibrido) o;
        return ( super.equals(c));
    }    
}
