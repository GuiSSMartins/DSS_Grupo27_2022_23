package business.subCatálogos;

import java.util.ArrayList;
import java.util.List;

import data.CircuitoDAO;

public class Campeonato {
    private String nome;
    private List<String> nomesCircuitos;
    private List<Circuito> circuitos;
    private CircuitoDAO circuitoDAO;


    public Campeonato() {
        this.nomesCircuitos = new ArrayList<>();
        this.circuitos = new ArrayList<>();
        this.circuitoDAO = CircuitoDAO.getInstance();
    }

    public Campeonato(String nome) {
        this.nome = nome;
        this.nomesCircuitos = new ArrayList<>();
        this.circuitos = new ArrayList<>();
        this.circuitoDAO = CircuitoDAO.getInstance();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNomesCircuitos(List<String> circuitos) {
        for (String c : circuitos) {
            this.nomesCircuitos.add(c);
        }
    }

    public List<Circuito> getCircuitos() {
        List<Circuito> copia = new ArrayList<>();
        for (Circuito c : this.circuitos) {
            copia.add(c);
        }
        return copia;
    }

    public List<String> getNomesCircuitos() {
        List<String> copia = new ArrayList<>();
        for (String c : this.nomesCircuitos) {
            copia.add(c);
        }
        return copia;
    }

    public void addCircuitos(String circuito){
        this.nomesCircuitos.add(circuito);
        this.circuitos.add(this.circuitoDAO.get(circuito));
    }

    public String toString() {
        return this.nome;
    }

}
