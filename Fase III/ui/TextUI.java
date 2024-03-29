package ui;

import business.SubUtilizadores.Carreira;
import business.SubUtilizadores.Jogador;
import business.SubUtilizadores.SSUtilizadores;
import business.subCatálogos.Campeonato;
import business.subCatálogos.Carro;
import business.subCatálogos.Circuito;
import business.subCatálogos.FacadeCatalogos;
import business.subCatálogos.Piloto;

import business.subPartidas.SSPartidas;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Exemplo de interface em modo texto.
 */
public class TextUI {
    // O model tem a 'lógica de negócio'.
    private FacadeCatalogos model;

    private SSPartidas sspartidas;
    private SSUtilizadores ssutilizadores;

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
        this.sspartidas = new SSPartidas();
        this.ssutilizadores = new SSUtilizadores();

        // Criar o menu
        this.menu = new Menu("Racing Manager", new String[] {
                "Entrar em Campeonato",
                "Consultar Carreira do Jogador",
                "Ranking Global"
        });
        this.menu.setHandler(1, this::trataEntrarCampeonato);
        this.menu.setHandler(2, this::trataConsultarCarreira);
        this.menu.setHandler(3, this::trataRankingGlobal);

        this.model = new FacadeCatalogos();
        this.model.povoar();
        this.scin = new Scanner(System.in);
    }

    /**
     * Executa o menu principal e invoca o método correspondente à opção
     * seleccionada.
     */
    public void run() {
        this.menu.run();
        System.out.println("\nAté breve!...");
    }

    private void trataEntrarCampeonato() {
        try {
            // LOGIN

            boolean b_utilizador = false;
            String username = null;
            String pass = null;

            System.out.println("\n||| LOGIN |||\n");

            while (!b_utilizador) {
                System.out.print("Nome de utilizador: ");
                username = scin.nextLine();
                System.out.print("\nPassword: ");
                pass = scin.nextLine();
                b_utilizador = this.ssutilizadores.validaAcesso(username, pass);
                if (!b_utilizador) {
                    System.out.println("\nUsername/Password errada(s). Por favor, tente novamente.\n");
                }
            }

            Jogador jogador = this.model.getJogador(username);

            int versaoJogo = jogador.getVersaoJogo();

            this.printVersaoJogo(versaoJogo);

            // Apresentar e escolher o CAMPEONATO

            boolean bool_opcao1 = false;
            String op_camp = null;

            List<Campeonato> campeonatos = this.model.getCampeonatos().stream()
                    .collect(Collectors.toList());

            while (!bool_opcao1) {
                // Primeiro tem de escolher o Campeonato
                this.printCampeonatos(campeonatos);
                int nCampeonatos = campeonatos.size();
                System.out.print("\nNúmero do campeonato: ");
                op_camp = scin.nextLine();

                bool_opcao1 = this.verificaOpcaoCampeonato(nCampeonatos, op_camp);

            }
            int opcao1 = Integer.parseInt(op_camp);
            Campeonato campeonato = campeonatos.get(opcao1 - 1);

            sspartidas.registarConfiguracao(campeonato, versaoJogo);

            // Visualizar circuitos do campeonato

            List<Circuito> circuitos = campeonato.getCircuitos();
            this.printCircuitos(circuitos);

            // Escolher carro

            boolean bool_opcao2 = false;
            String op_carro = null;

            List<Carro> carros = this.model.getCarros().stream()
                    .collect(Collectors.toList());
            while (!bool_opcao2) {
                this.printCarros(carros);
                int nCarros = carros.size();
                System.out.print("\nNúmero do carro: ");
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

            while (!bool_opcao3) {
                this.printPilotos(pilotos);
                int nPilotos = pilotos.size();
                System.out.print("\nNúmero do piloto: ");
                op_piloto = scin.nextLine();

                bool_opcao3 = this.verificaOpcaoPiloto(nPilotos, op_piloto);
            }

            int opcao3 = Integer.parseInt(op_piloto);
            Piloto piloto = pilotos.get(opcao3 - 1);

            // Adicionar jogador
            sspartidas.entrarNaPartida(jogador, 1, piloto, carro);

            // Apresentar os outros jogadores "bot"
            sspartidas.criarBots();
            System.out.println("\nEste jogo terá bots durante a simulação!\n");

            // Apresentar a situação meteorologica de cada circuito
            Map<String, Integer> climas = sspartidas.clima();
            imprimirClimas(climas);

            // Perguntar se o jogador pretende alterar a afinação do carro
            // Se sim, alterar downforce

            boolean pode_avancar = false;
            boolean bool_opcao4 = false;
            String op_afinacao = null;

            while (!pode_avancar) { // Enquanto o jogador não fornecer uma opção correta, deve insistir
                System.out.println("\nDeseja alterar a afinação do seu carro? (Escreva o número da opção desejada)\n");
                System.out.println("1 - Sim");
                System.out.println("2 - Não");
                System.out.print("\nOpção: ");
                op_afinacao = scin.nextLine();
                Integer opcao = Integer.parseInt(op_afinacao);
                if (opcao == 1) {
                    pode_avancar = true;
                    bool_opcao4 = true;
                } else if (opcao == 2)
                    pode_avancar = true;
                else
                    System.out.println("\n\nOpção inválida!\n");
            }

            String s_downforce;
            double downforce = -1;
            if (bool_opcao4) { // Quer alterar a downforce do carro (entre 0 e 1)
                while (downforce < 0 || downforce > 1) {
                    System.out.println("\nEscreva o novo valor da downforce  (Entre 0 e 1)\n");
                    System.out.println("Valor: ");
                    s_downforce = scin.nextLine();
                    downforce = Double.parseDouble(s_downforce);

                    if (downforce < 0 || downforce > 1) { // Valor inválido
                        System.out.println("\nO valor está errado!!! (tem de ser dentro de 0 e 1)\n");
                    }
                }
            }

            // Escolher modo dos pneus & modo do motor

            boolean bool_opcao5 = false;
            String op_pneus = null;
            int pneus = -1;

            while (!bool_opcao5) {
                System.out.println("\n||| Modos de Pneus |||");
                System.out.println("1 - Macio;  2 - Duro;  3 - Chuva");
                System.out.print("\nNúmero da opção: ");
                op_pneus = scin.nextLine();
                pneus = Integer.parseInt(op_pneus);

                if (pneus == 1 || pneus == 2 || pneus == 3)
                    bool_opcao5 = true;
                if (!bool_opcao5)
                    System.out.println("\nValor errado!\n");
            }

            boolean bool_opcao6 = false;
            String op_motor = null;
            int motor = -1;

            while (!bool_opcao6) {
                System.out.println("\n||| Modos de Motor |||");
                System.out.println("1 - Conservador;  2 - Normal;  3 - Agressivo");
                System.out.print("\nNúmero da opção: ");
                op_motor = scin.nextLine();
                motor = Integer.parseInt(op_motor);

                if (motor == 1 || motor == 2 || motor == 3)
                    bool_opcao6 = true;
                if (!bool_opcao6)
                    System.out.println("\nValor errado!\n");
            }

            // Correr a simulação
            sspartidas.iniciarPartida();

            // Apresentar os resultados da partida
            List<String> resultados_finais = sspartidas.finalizarPartida();
            System.out.println("\n||| RESULTADOS FINAIS |||\n");
            for (int i = 1; i <= 3; i++) {
                System.out.println(i + "º: " + resultados_finais.get(i - 1));
            }
            System.out.println();

        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    private void trataConsultarCarreira() {
        // LOGIN

        boolean b_utilizador = false;
        String username = null;
        String pass = null;

        System.out.println("\n||| LOGIN |||\n");

        while (!b_utilizador) {
            System.out.print("Nome de utilizador: ");
            username = scin.nextLine();
            System.out.print("\nPassword: ");
            pass = scin.nextLine();
            b_utilizador = this.ssutilizadores.validaAcesso(username, pass);
            if (!b_utilizador) {
                System.out.println("\nUsername/Password errada(s). Por favor, tente novamente.\n");
            }
        }

        // Lista das carreiras
        List<Carreira> carreiras = this.model.getCarreiraJogador(username);
        if (carreiras.size() == 0) {
            System.out.println("\nNão existe histórico para este jogador!\nEntre num Campeonato e começe a jogar!\n");
        } else {
            System.out.println("\n||| CARREIRAS |||\n");
            for (Carreira c : carreiras) {
                System.out.println(c);
            }
            System.out.println();
        }
    }

    private void trataRankingGlobal() {
        List<Carreira> ranking = this.model.getRankingGlobal();

        if (ranking == null) {
            System.out
                    .println("\nAinda não existe ranking na base de dados.\nEntre num Campeonato e começe a jogar!\n");
        } else {
            System.out.println("\n||| RANKING GLOBAL |||\n");

            int i = 1;
            for (Carreira c : ranking) {
                System.out.println(i + "º: " + c);
                i++;
            }
            System.out.println();
        }
    }

    private void printCampeonatos(List<Campeonato> campeonatos) {
        int i = 1;
        System.out.println("\n--- CAMPEONATOS DISPONÍVEIS ---\n");
        for (Campeonato c : campeonatos) {
            System.out.println("Campeonato " + i + ": " + c);
            i += 1;
        }
        System.out.println();
    }

    private boolean verificaOpcaoCampeonato(int nCampeonatos, String op_camp) {
        int opcao = Integer.parseInt(op_camp);
        if (opcao > 0 && opcao <= nCampeonatos)
            return true;
        else {
            System.out.println("\nATENÇÃO: Opção inválida!\n");
            return false;
        }
    }

    private void printCircuitos(List<Circuito> circuitos) {
        System.out.println("\n--- Lista de CIRCUITOS do Campeonato ---\n");
        int i = 1;

        for (Circuito circuito : circuitos) {
            String nome = circuito.getNome();
            System.out.println(i + ": " + nome);
            i += 1;
        }
        System.out.println();
    }

    private void printCarros(List<Carro> carros) {
        System.out.println("\n--- Lista de CARROS disponíveis  ---\n");
        int i = 1;
        for (Carro carro : carros) {
            System.out.println(i + ": " + carro);
            i += 1;
        }
        System.out.println();
    }

    private boolean verificaOpcaoCarro(int nCarros, String op_carro) {
        int opcao = Integer.parseInt(op_carro);
        if (opcao > 0 && opcao <= nCarros)
            return true;
        else {
            System.out.println("\nATENÇÃO: Opção inválida!\n");
            return false;
        }
    }

    private void printPilotos(List<Piloto> pilotos) {
        System.out.println("\n--- Lista de PILOTOS disponíveis  ---\n");
        int i = 1;
        for (Piloto piloto : pilotos) {
            System.out.println("Piloto " + i + ": " + piloto);
            i += 1;
        }
        System.out.println();
    }

    private boolean verificaOpcaoPiloto(int nPilotos, String op_piloto) {
        int opcao = Integer.parseInt(op_piloto);
        if (opcao > 0 && opcao <= nPilotos)
            return true;
        else {
            System.out.println("\nATENÇÃO: Opção inválida!\n");
            return false;
        }
    }

    private void imprimirClimas(Map<String, Integer> climas) {
        Set<String> circuitos = climas.keySet();
        System.out.println("\n||| Clima nos Circuitos\n");
        for (String circuito : circuitos) {
            int clima = climas.get(circuito);
            String s_clima;
            if (clima == 1)
                s_clima = "Chove";
            else
                s_clima = "Sol";
            System.out.println(circuito + " : " + s_clima);
        }
    }

    private void printVersaoJogo(int versaoJogo) {
        // 0 - Base; 1 - Premium
        if (versaoJogo == 0)
            System.out.println("\n Versão do Jogo: BASE\n");
        else if (versaoJogo == 1)
            System.out.println("\n Versão do Jogo: PREMIUM\n");
    }
}
