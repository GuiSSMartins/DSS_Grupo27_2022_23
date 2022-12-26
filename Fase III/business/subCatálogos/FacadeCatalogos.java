package business.subCatálogos;

import java.util.ArrayList;
import java.util.Collection;

import data.*;

public class FacadeCatalogos {
    // São todos os Maps
    private CampeonatoDAO campeonatos;
    private CircuitoDAO circuitos;
    private PilotoDAO pilotos;
    private CarroDAO carros;

    public FacadeCatalogos() {
        this.campeonatos = CampeonatoDAO.getInstance();
        this.circuitos = CircuitoDAO.getInstance();
        this.pilotos = PilotoDAO.getInstance();
        this.carros = CarroDAO.getInstance();
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
}
