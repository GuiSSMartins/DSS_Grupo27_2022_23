
/**
 * Write a description of class PC1H here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Random;

public class PC1H extends PC1 implements Hibrido
{
    private int motor_eletrico;
    
    public PC1H()
    {
       super();
       this.motor_eletrico = 0;
    }
    
    public PC1H(String marca, String modelo, int cilindrada, int potencia, Equipa e, int eletrico)
    {
        super(marca,modelo,cilindrada,potencia,e);
        this.motor_eletrico = eletrico;
    }
    
    public PC1H(PC1H p)
    {
        super(p);
        this.motor_eletrico = p.getPotenciaMotorEletrico();
    }
    
    public PC1H clone()
    {
        return new PC1H(this);
    }
    
    public int getPotenciaMotorEletrico()
    {
        return this.motor_eletrico;
    }
    
    public void setPotenciaMotorEletrico(int potencia)
    {
        this.motor_eletrico = potencia;
    }
    
    public boolean DNF(int volta,int totalvoltas,int clima)
    {
       Random rand=new Random();
       int x=rand.nextInt(85);
       int motorh = this.getPotenciaMotorEletrico()/20;     
       return (x > (super.getFiabilidade()-motorh));
       //return false;
    }
    
    public boolean equals(Object o)
    {
        if(this==o)
        return true;
        
        if(o==null || this.getClass()!=o.getClass())
        return false;
        
        PC1H c = (PC1H) o;
        return ( super.equals(c) && this.motor_eletrico == c.getPotenciaMotorEletrico());
    }    
}
