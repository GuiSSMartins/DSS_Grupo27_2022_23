package ui;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import business.subCatálogos.*;

/**
 * Exemplo de interface em modo texto.
 */
public class TextUI {
    // O model tem a 'lógica de negócio'.
    private FacadeCatalogos model;

    // Menus da aplicação
    private Menu menu;

    // Scanner para leitura
    private Scanner scin;

        /**
     * Construtor.
     *
     * Cria os menus e a camada de negócio.
     */
    public TextUI() {
        // Criar o menu
        this.menu = new Menu("Racing Manager", new String[]{
            "Entrar em Campeonato",
            "Consultar Carreira do Jogador"
        });

        this.menu.setHandler(1, this::trataEntrarCampeonato);

        this.model = new FacadeCatalogos();
        this.scin = new Scanner(System.in);
    }

    /**
     * Executa o menu principal e invoca o método correspondente à opção seleccionada.
     */
    public void run() {
        this.menu.run();
        System.out.println("\nAté breve!...");
    }

    private void trataEntrarCampeonato(){
        try {

            boolean bool_opcao1 = false;
            String op_camp = null;

            List<Campeonato> campeonatos = this.model.getCampeonatos().stream()
                                            .collect(Collectors.toList());

            while(!bool_opcao1) {
                // Primeiro tem de escolher o Campeonato
                this.printCampeonatos(campeonatos);
                int nCampeonatos = campeonatos.size();
                System.out.println("\nNúmero do campeonato: ");
                op_camp = scin.nextLine();

                bool_opcao1 = this.verificaOpcaoCampeonato(nCampeonatos, op_camp);

            }
            int opcao1 = Integer.parseInt(op_camp);
            Campeonato campeonato = campeonatos.get(opcao1 - 1);

            // Visualizar circuitos do campeonato

            List<Circuito> circuitos = campeonato.getCircuitos();
            this.printCircuitos(circuitos);

            // Escolher carro

            boolean bool_opcao2 = false;
            String op_carro = null;


            List<Carro> carros = this.model.getCarros().stream()
                                            .collect(Collectors.toList());

            while(!bool_opcao2) {
                this.printCarros(carros);
                int nCarros = carros.size();
                System.out.println("\nNúmero do carro: ");
                op_carro = scin.nextLine();

                bool_opcao2 = this.verificaOpcaoCarro(nCarros, op_carro);
            }

            int opcao2 = Integer.parseInt(op_carro);
            Carro carro = carros.get(opcao1 - 1);

            // Escolher piloto

            boolean bool_opcao3 = false;
            String op_piloto = null;

            List<Piloto> pilotos = this.model.getPilotos().stream()
                                            .collect(Collectors.toList());

            while(!bool_opcao3) {
                this.printPilotos(pilotos);
                int nPilotos = pilotos.size();
                System.out.println("\nNúmero do carro: ");
                op_carro = scin.nextLine();

                bool_opcao2 = this.verificaOpcaoCarro(nPilotos, op_carro);
            }

            // Apresentar os outros jogadores "bot"

            // Apresentar as situacoes meteorologicas de cada circuito

            // Perguntar se o jogador pretende alterar a afinação do carro
            // Se sim, alterar downforce

            // Escolher modo dos pneus & modo do motor

            // (Gerar definições dos pilotos e dos carros aleatórios)
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void printCampeonatos(List<Campeonato> campeonatos) {
        int i=1;
        System.out.println("\n--- CAMPEONATOS DISPONÍVEIS ---\n");
        for (Campeonato c : campeonatos) {
            System.out.println(i+": "+c);
            i+=1;
        }
        System.out.println();
    }

    private boolean verificaOpcaoCampeonato(int nCampeonatos, String op_camp){
        int opcao = Integer.parseInt(op_camp);
        if (opcao > 0 && opcao <= nCampeonatos) return true;
        else {
            System.out.println("\nATENÇÃO: Opção inválida!\n");
            return false;
        }
    }

    private void printCircuitos(List<Circuito> circuitos){
        System.out.println("\n--- Lista de CIRCUITOS do Campeonato ---\n");
        int i=1;

        for(Circuito circuito : circuitos) {
            String nome = circuito.getNome();
            System.out.println((i+": "+nome));
            i+=1;
        }
        System.out.println();
    }

    private void printCarros(List<Carro> carros) {
        System.out.println("\n--- Lista de CIRCUITOS do Campeonato ---\n");
        int i=1;

        for(Circuito circuito : circuitos) {
            String nome = circuito.getNome();
            System.out.println((i+": "+circuito));
            i+=1;
        }
        System.out.println();
    }

// socorro deussssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss sos

    private boolean verificaOpcaoCarro(int nCarros, String op_carro) {
        int opcao = Integer.parseInt(op_carro);
        if (opcao > 0 && opcao <= nCarros) return true;
        else {
            System.out.println("\nATENÇÃO: Opção inválida!\n");
            return false;
        }
    }
}
