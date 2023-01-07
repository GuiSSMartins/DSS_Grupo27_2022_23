
/**
 * Write a description of class Corrida here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
package business.subPartidas;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import business.subCatálogos.Carro;
import business.subCatálogos.Circuito;
import business.subCatálogos.Hibrido;
import business.subCatálogos.Piloto;

import java.util.Random;
import java.io.Serializable;

public class Corrida implements Serializable {
    // variaveis de instancia
    private List<Progresso> listaProgressos;
    private Circuito circuito;
    private Set<Progresso> resultados;
    // private Map<Carro,double> bestLap;
    private Map<Carro, Integer> dnf;
    private List<Progresso> primeiroVolta;
    private List<Evento> eventos;
    private int clima; // 1-chove 0-sol
    private int versaoJogo;

    // Construtores
    public Corrida(int versaoJogo, Circuito circuito) {
        this.versaoJogo = versaoJogo;
        this.listaProgressos = new ArrayList<Progresso>();
        this.circuito = circuito;
        this.eventos = new ArrayList<>();
        this.dnf = new HashMap<Carro, Integer>();
        this.resultados = new TreeSet<Progresso>((o1, o2) -> o1.compareTo(o2));
        // this.bestLap = new HashMap<Carro,double>();
        this.primeiroVolta = new ArrayList<Progresso>();
        Random rand = new Random();
        int x = rand.nextInt(2);
        this.clima = x;
    }

    public Corrida(List<Progresso> p, Circuito c, Set<Progresso> r, List<Progresso> pr, int clima, int versaoJogo) {
        for (Progresso prog : p) {
            this.listaProgressos.add(prog);
        }
        // this.circuito = c.clone();
        for (Progresso car : r) {
            this.resultados.add(car.clone());
        }
        for (Progresso x : pr) {
            this.primeiroVolta.add(x.clone());
        }
        this.clima = clima;
        this.versaoJogo = versaoJogo;
        this.eventos = new ArrayList<>();
    }

    public Corrida(Corrida c) {
        this.listaProgressos = c.getProgressos();
        this.circuito = c.getCircuito();
        this.resultados = c.getResultados();
        this.primeiroVolta = c.getPrimeiroVolta();
        this.clima = c.getClima();
        this.versaoJogo = c.getVersaoJogo();
        this.dnf = c.getDNF();
    }

    // Gets e sets
    public List<Progresso> getProgressos() {
        ArrayList<Progresso> aux = new ArrayList<Progresso>();
        for (Progresso c : this.listaProgressos) {
            aux.add(c.clone());
        }
        return aux;
    }

    public void setProgressos(List<Progresso> progressos) {
        this.limpaListaProgressos();
        for (Progresso c : progressos) {
            this.listaProgressos.add(c.clone());
        }
    }

    public Circuito getCircuito() {
        return this.circuito.clone();
    }

    public Set<Progresso> getResultados() {
        Set<Progresso> aux = new TreeSet<Progresso>();
        for (Progresso c : this.resultados) {
            aux.add(c.clone());
        }
        return aux;
    }

    public int getClima() {
        return this.clima;
    }

    public int getVersaoJogo() {
        return this.versaoJogo;
    }

    public List<Progresso> getPrimeiroVolta() {
        ArrayList<Progresso> aux = new ArrayList<Progresso>();
        for (Progresso c : this.primeiroVolta) {
            aux.add(c.clone());
        }
        return aux;
    }

    public void setPrimeiroVolta(List<Progresso> ps) {
        for(Progresso p : ps) {
            this.primeiroVolta.add(p.clone());
        }

    }

    public void setCircuito(Circuito c) {
        this.circuito = c.clone();
    }

    // Metodos

    public Corrida clone() {
        return new Corrida(this);
    }

    /**
     * Adicionar um progresso á lista
     */
    public void adicionarProgresso(Progresso c) {
        this.listaProgressos.add(c.clone());
    }

    /**
     * adicionar lista de progressos
     */
    public void adicionarProgresso(List<Progresso> l) {
        for (Progresso c : l) {
            this.listaProgressos.add(c.clone());
        }
    }

    /**
     * Numero total de progressos
     */
    public int totalProgressos() {
        return this.listaProgressos.size();
    }

    /**
     * Remover um progresso
     */
    public void removerProgresso(Progresso p) {
        this.listaProgressos.remove(p);
    }

    /**
     * Limpa a lista de progressos
     */
    public void limpaListaProgressos() {
        this.listaProgressos.clear();
        this.listaProgressos = new ArrayList<>();
    }

    /**
     * Simula a corrida
     */
    public void simulaCorrida(Simulador s) {
        this.eventos = s.calcularEventosPartida(this);
        this.calculaResultadosFinais();
    }

    /**
     * Lista de Acidentados DNF
     */
    public String printDNF() {
        StringBuilder sb = new StringBuilder();
        sb.append("Espetados!!!");
        for (Carro c : this.dnf.keySet()) {
            sb.append("\n" + c.getMarca() + " \t\tVolta: " + this.dnf.get(c));
        }
        return sb.toString();
    }

    public Map<Carro, Integer> getDNF() {
        HashMap<Carro, Integer> aux = new HashMap<Carro, Integer>();
        for (Carro c : this.dnf.keySet()) {
            aux.put(c.clone(), this.dnf.get(c));
        }
        return aux;
    }

    /**
     * Lista de participantes da corrida
     */
    public String listaCarrosParticipantes() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Progresso p : this.listaProgressos) {
            Carro c = p.getCarro();
            sb.append("\n");
            sb.append(i);
            sb.append(" - ");
            sb.append(c.getMarca());
            sb.append(" ");
            sb.append(c.getModelo());
            i++;
        }
        return sb.toString();
    }

    public void iniciarProgressos(Map<String, Estado> estados) {
        Set<String> keys = estados.keySet();
        for (String email : keys) {
            Estado estado = estados.get(email);
            Carro carro = estado.getCarro();
            Piloto piloto = estado.getPiloto();
            Progresso progresso = new Progresso(email, piloto, carro);
            this.listaProgressos.add(progresso);
        }
    }

    /**
     * Lista o 1o classificado em cada volta
     */
    private String printPrimeiroVolta() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("\n||||| Primeiro carro a cada volta e desistentes |||||");
        List<Progresso> primeiroVolta = this.getPrimeiroVolta();
        for (int i = 0; i < primeiroVolta.size(); i++) {
            sb.append("\n");
            sb.append(i + 1);
            sb.append("ª Volta: ");
            sb.append(primeiroVolta.get(i).getCarro().getMarca());
            sb.append(" ");
            sb.append(primeiroVolta.get(i).getCarro().getModelo());
            sb.append(" ");
            if (primeiroVolta.get(i).getCarro().checkDNF(i + 1, primeiroVolta.size(), this.getClima()))
                sb.append("DESISTIU");
        }
        return sb.toString();
    }

    /**
     * Lista os resultados da corrida.
     */
    public String printResultados() {
        Circuito circuito = this.getCircuito();
        StringBuilder sb = new StringBuilder();
        int i = 1;
        sb.append("\n||||| ");
        sb.append(circuito.getNome());
        sb.append(" |||||");
        sb.append("\n||||| ");
        sb.append("Voltas: ");
        sb.append(circuito.getNVoltas());
        sb.append(" |||||");
        sb.append("\n||||| ");
        sb.append("Distancia: ");
        sb.append(circuito.getComprimento());
        sb.append("km | ");
        sb.append("Condição meteorológica: ");
        if (this.getClima() == 0) {
            sb.append("Sol");
        } else {
            sb.append("Chuva");
        }
        sb.append("\n\n||||| Classificacoes da corrida |||||");

        Set<Progresso> resultados = this.getResultados();
        for (Progresso progresso : resultados) {
            Carro c = progresso.getCarro();
            sb.append("\n");
            sb.append(i);
            sb.append("º: ");
            sb.append(progresso.getTempo());
            sb.append("\t Categoria: ");
            sb.append(c.getClass().getName());
            sb.append(" ");
            sb.append("\t Carro: ");
            sb.append(c.getMarca());
            sb.append(" ");
            sb.append(c.getModelo());
            i++;
        }
        sb.append("\n\n||||| Classificacoes da corrida Hibridos |||||");
        i = 1;
        for (Progresso progresso : resultados) {
            Carro c = progresso.getCarro();
            if (c instanceof Hibrido) {

                sb.append("\n");
                sb.append(i);
                sb.append("º: ");
                sb.append(progresso.getTempo());
                sb.append("\t Categoria: ");
                sb.append(c.getClass().getName());
                sb.append(" ");
                sb.append("\t Carro: ");
                sb.append(c.getMarca());
                sb.append(" ");
                sb.append(c.getModelo());
                i++;
            }
        }
        sb.append(this.printPrimeiroVolta());
        return sb.toString();
    }

    public void calculaResultadosFinais() {
        for(Progresso p : listaProgressos) {
            this.resultados.add(p.clone());
        }
    }


}
