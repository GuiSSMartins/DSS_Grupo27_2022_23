package ui;

import business.subCatálogos.Campeonato;
import business.subCatálogos.Carro;
import business.subCatálogos.Circuito;
import business.subCatálogos.FacadeCatalogos;
import business.subCatálogos.Piloto;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


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
            Carro carro = carros.get(opcao2 - 1);

            // Escolher piloto

            boolean bool_opcao3 = false;
            String op_piloto = null;

            List<Piloto> pilotos = this.model.getPilotos().stream()
                                            .collect(Collectors.toList());

            while(!bool_opcao3) {
                this.printPilotos(pilotos);
                int nPilotos = pilotos.size();
                System.out.println("\nNúmero do carro: ");
                op_piloto = scin.nextLine();

                bool_opcao3 = this.verificaOpcaoPiloto(nPilotos, op_piloto);
            }

            int opcao3 = Integer.parseInt(op_piloto);
            Piloto piloto = pilotos.get(opcao3 - 1);

            // Apresentar os outros jogadores "bot"

            // Apresentar as situacoes meteorologicas de cada circuito

            // Perguntar se o jogador pretende alterar a afinação do carro
            // Se sim, alterar downforce

            boolean pode_avancar = false;
            boolean bool_opcao4 = false;
            String op_afinacao = null;

            while (!pode_avancar) { // Enquanto o jogador não fornecer uma opção correta, deve insistir
                System.out.println("\nDeseja alterar a afinação do seu carro? (Escreva o número da opção desejada)\n");
                System.out.println("1 - Sim");
                System.out.println("2 - Não");
                System.out.println("\nOpção: ");
                op_afinacao = scin.nextLine();
                Integer opcao =  Integer.parseInt(op_afinacao);
                if (opcao == 1) {
                    pode_avancar = true;
                    bool_opcao4 = true;
                }
                else if (opcao == 2) pode_avancar = true;
                else System.out.println("\n\nOpção inválida!\n");
            }

            if (bool_opcao4) { // Pode alterar a downforce do carro


            }

            // Escolher modo dos pneus & modo do motor

            // (Gerar definições dos pilotos e dos carros aleatórios)

            // Correr a simulação

            // Apresentar os resulatdos
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void printCampeonatos(List<Campeonato> campeonatos) {
        int i=1;
        System.out.println("\n--- CAMPEONATOS DISPONÍVEIS ---\n");
        for (Campeonato c : campeonatos) {
            System.out.println("Campeonato "+i+": "+c);
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
            System.out.println(i+": "+nome);
            i+=1;
        }
        System.out.println();
    }

    private void printCarros(List<Carro> carros) {
        System.out.println("\n--- Lista de CARROS disponíveis  ---\n");
        int i=1;
        for(Carro carro : carros) {
            System.out.println(i+": "+carro);
            i+=1;
        }
        System.out.println();
    }

    private boolean verificaOpcaoCarro(int nCarros, String op_carro) {
        int opcao = Integer.parseInt(op_carro);
        if (opcao > 0 && opcao <= nCarros) return true;
        else {
            System.out.println("\nATENÇÃO: Opção inválida!\n");
            return false;
        }
    }

    private void printPilotos(List<Piloto> pilotos) {
        System.out.println("\n--- Lista de PILOTOS disponíveis  ---\n");
        int i=1;
        for(Piloto piloto : pilotos) {
            System.out.println("Piloto "+i+": "+piloto);
            i+=1;
        }
        System.out.println();
    }

    private boolean verificaOpcaoPiloto(int nPilotos, String op_piloto) {
        int opcao = Integer.parseInt(op_piloto);
        if (opcao > 0 && opcao <= nPilotos) return true;
        else {
            System.out.println("\nATENÇÃO: Opção inválida!\n");
            return false;
        }
    }
}
