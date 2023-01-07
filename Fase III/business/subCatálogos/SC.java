
/**
 * Write a description of class SC here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
package business.subCatálogos;

import java.util.Random;

public class SC extends Carro {
    public SC() {
        super();
    }

    public SC(int id, String marca, String modelo, int potencia, int fiabilidade, double PAC) {
        super(id, marca, modelo, 2500, potencia, fiabilidade, PAC);
    }

    public SC(SC p) {
        super(p);
    }

    public SC clone() {
        return new SC(this);
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || this.getClass() != o.getClass())
            return false;

        SC c = (SC) o;
        return (super.equals(c));
    }

    public boolean checkDNF(int volta, int totalvoltas, int chuva) {
        Random rand = new Random();
        int x = rand.nextInt(70);
        // no maximo fiabilidade de 70%
        int fiabilidade = (int) ((100000 / super.getCilindrada()) * 2.55);
        return (x > fiabilidade);
    }

}
