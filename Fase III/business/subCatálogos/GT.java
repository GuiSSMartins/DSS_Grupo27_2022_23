
/**
 * Write a description of class GT here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

package business.subCatálogos;

import java.util.Random;

public class GT extends Carro {
    private double taxaDeterioracao;

    public GT() {
        super();
    }

    public GT(int id, String marca, String modelo, int cilindrada, int potencia, int fiabilidade, double PAC,
            double taxaDeterioracao) {
        super(id, marca, modelo, cilindrada, potencia, fiabilidade, PAC);
        this.taxaDeterioracao = taxaDeterioracao;
    }

    public GT(GT p) {
        super(p);
        this.taxaDeterioracao = p.getTaxaDeterioracao();
    }

    public GT clone() {
        return new GT(this);
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || this.getClass() != o.getClass())
            return false;

        GT c = (GT) o;
        return (super.equals(c));
    }

    public double getTaxaDeterioracao() {
        return this.taxaDeterioracao;
    }

    public void setTaxaDeterioracao(double taxaDeterioracao) {
        this.taxaDeterioracao = taxaDeterioracao;
    }

    public boolean checkDNF(int volta, int totalvoltas, int clima) {
        Random rand = new Random();
        int x = rand.nextInt(70);
        // no maximo fiabilidade de 85%
        // 3000 cilindrada = 85% / 4500 cilindrada = 57%
        int fiabilidade = (int) ((100000 / super.getCilindrada()) * 2.55);
        int desgaste = (int) ((volta + 1) * 0.5); // 0.5% a cada volta
        return (x > (fiabilidade - desgaste));
    }

}
