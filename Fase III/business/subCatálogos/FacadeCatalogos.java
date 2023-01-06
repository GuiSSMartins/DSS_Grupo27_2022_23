package business.subCatálogos;

import java.util.ArrayList;
import java.util.Collection;

import business.SubUtilizadores.Carreira;
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

        this.campeonatos = CampeonatoDAO.getInstance();
        this.utilizadores = UtilizadorDAO.getInstance();
        this.seccoes = SeccaoDAO.getInstance();
        this.circuitos = CircuitoDAO.getInstance();
        this.pilotos = PilotoDAO.getInstance();
        this.carros = CarroDAO.getInstance();
        this.carreiras = CarreiraDAO.getInstance();
    }

    // Povoar a base de dados 
    public void povoar(){
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
}
