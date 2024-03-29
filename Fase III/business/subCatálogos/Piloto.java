
/**
 * Write a description of class Piloto here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
package business.subCatálogos;

import java.io.Serializable;

public class Piloto implements Serializable {
    // Variaveis de instancia
    private String nome;
    private double CTS; // Chuva vs. Tempo Seco // (entre 0 e 1)
    private double SVA; // Segurança vs. Agressividade // (entre 0 e 1)

    // Construtores
    public Piloto() {
        this.nome = "";
        this.CTS = 0;
        this.SVA = 0;
    }

    public Piloto(String nome, double CTS, double SVA) {
        this.nome = nome;
        this.CTS = CTS;
        this.SVA = SVA;
    }

    public Piloto(Piloto p) {
        this.nome = p.getNome();
        this.CTS = p.getCTS();
        this.SVA = p.getSVA();
    }

    // Gets e Sets
    public String getNome() {
        return this.nome;
    }

    public double getCTS() {
        return this.CTS;
    }

    public double getSVA() {
        return this.SVA;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCTS(double q) {
        this.CTS = q;
    }

    public void setSVA(double q) {
        this.SVA = q;
    }

    // Metodos usuais
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" Nome: ");
        sb.append(this.nome);
        sb.append("\tCTS: ");
        sb.append(this.CTS);
        sb.append("\tSVA: ");
        sb.append(this.SVA);
        return sb.toString();
    }

    public Piloto clone() {
        return new Piloto(this);
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;

        if ((o == null) || (this.getClass() != o.getClass()))
            return false;

        Piloto p = (Piloto) o;
        return (this.nome.equals(p.getNome()) &&
                this.CTS == p.getCTS() &&
                this.SVA == p.getSVA());
    }

    public double probabilidadeCarroAvariar(int clima) {
        if (clima == 0) // sol
            return 1 - this.CTS;
        return CTS;
    }
}
