package business.subCatálogos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import business.SubUtilizadores.Carreira;
import business.SubUtilizadores.Jogador;
import business.SubUtilizadores.Utilizador;
import data.*;

public class FacadeCatalogos {
    // São todos os Maps
    private CampeonatoDAO campeonatos;
    private CircuitoDAO circuitos;
    private PilotoDAO pilotos;
    private CarroDAO carros;
    private SeccaoDAO seccoes;
    private CarreiraDAO carreiras;
    private UtilizadorDAO utilizadores;

    public FacadeCatalogos() {
        this.circuitos = CircuitoDAO.getInstance();
        this.campeonatos = CampeonatoDAO.getInstance();
        this.utilizadores = UtilizadorDAO.getInstance();
        this.seccoes = SeccaoDAO.getInstance();
        this.pilotos = PilotoDAO.getInstance();
        this.carros = CarroDAO.getInstance();
        this.carreiras = CarreiraDAO.getInstance();
    }

    // Povoar a base de dados
    public void povoar() {
        this.circuitos.povoar();
        this.campeonatos.povoar();
        this.pilotos.povoar();
        this.carros.povoar();
        this.seccoes.povoar();
        this.utilizadores.povoar();
        this.carreiras.povoar();
    }

    public Collection<Campeonato> getCampeonatos() {
        return new ArrayList<>(this.campeonatos.values());
    }

    public Collection<Circuito> getCircuitos() {
        return new ArrayList<>(this.circuitos.values());
    }

    public Collection<Piloto> getPilotos() {
        return new ArrayList<>(this.pilotos.values());
    }

    public Collection<Carro> getCarros() {
        return new ArrayList<>(this.carros.values());
    }

    public Collection<Seccao> getSeccoes() {
        return new ArrayList<>(this.seccoes.values());
    }

    public Collection<Carreira> getCarreiras() {
        return new ArrayList<>(this.carreiras.values());
    }

    public Collection<Utilizador> getUtilizadores() {
        return new ArrayList<>(this.utilizadores.values());
    }

    public Jogador getJogador(String email) {
        return (Jogador) this.utilizadores.get(email);
    }

    public List<Carreira> getCarreiraJogador(String email) {
        return this.carreiras.getCarreiras(email);
    }

    public List<Carreira> getRankingGlobal() {
        List<Carreira> ranking = new ArrayList<>();

        Set<String> emails = this.utilizadores.keySetJogador();

        for (String email : emails) {
            List<Carreira> carreira = this.carreiras.getCarreiras(email);
            int pontuacaoTotal = 0;
            for (Carreira c : carreira) {
                pontuacaoTotal += c.getPontuacao();
            }
            ranking.add(new Carreira(null, pontuacaoTotal, email));
        }

        return ranking.stream().sorted((c1, c2) -> Integer.compare(c2.getPontuacao(), c1.getPontuacao()))
                .collect(Collectors.toList());
    }
}