package business.subCatálogos;

import java.util.ArrayList;
import java.util.List;

import data.CircuitoDAO;

public class Campeonato {
    private String nome;
    private List<String> circuitos;
    private CircuitoDAO circuitoDAO;


    public Campeonato() {
        this.circuitos = new ArrayList<>();
        this.circuitoDAO = CircuitoDAO.getInstance();
    }

    public Campeonato(String nome) {
        this.nome = nome;
        this.circuitos = new ArrayList<>();
        this.circuitoDAO = CircuitoDAO.getInstance();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void setCircuitos(List<String> circuitos) {
        for (String c : circuitos) {
            this.circuitos.add(c);
        }
    }

    public List<Circuito> getCircuitos() {
        List<Circuito> copia = new ArrayList<>();
        for (String c : this.circuitos) {
            copia.add(this.circuitoDAO.get(c));
        }
        return copia;
    }

    public List<String> getNomesCircuitos() {
        List<String> copia = new ArrayList<>();
        for (String c : this.circuitos) {
            copia.add(c);
        }
        return copia;
    }

    public void addCircuitos(String circuito){
        this.circuitos.add(circuito);
    }

    public String toString() {
        return this.nome;
    }

}
