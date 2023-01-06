package business.subCatálogos;

import data.*;

public class FacadeCatalogos {
    private CampeonatoDAO campeonatos;
    private CircuitoDAO circuitos;
    private PilotoDAO pilotos;
    private CarroDAO carros;
    private CarroHibridoDAO carrosHibridos;

    public FacadeCatalogos() {
        this.campeonatos = CampeonatoDAO.getInstance();
        this.circuitos = CircuitoDAO.getInstance();
        this.pilotos = PilotoDAO.getInstance();
        this.carros = CarroDAO.getInstance();
        this.carrosHibridos = CarroHibridoDAO.getInstance();
    }
}
