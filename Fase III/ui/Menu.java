package ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import business.subCatálogos.*;
import business.subPartidas.*;

/**
 * Esta classe implementa um menu em modo texto.
 */
public class Menu {

    // Interfaces auxiliares

    /** Functional interface para handlers. */
    public interface Handler {
        void execute();
    }

    /** Functional interface para pré-condições. */
    public interface PreCondition {
        boolean validate();
    }

    // Varíável de classe para suportar leitura

    private static Scanner is = new Scanner(System.in);

    // Variáveis de instância

    private String titulo;                  // Titulo do menu (opcional)
    private List<String> opcoes;            // Lista de opções
    private List<PreCondition> disponivel;  // Lista de pré-condições
    private List<Handler> handlers;         // Lista de handlers (do Menu Principal)

    // Construtor

    /**
     * Constructor vazio para objectos da classe Menu.
     *
     * Cria um menu vazio, ao qual se podem adicionar opções.
     */
    public Menu() {
        this.titulo = "Menu";
        this.opcoes = new ArrayList<>();
        this.disponivel = new ArrayList<>();
        this.handlers = new ArrayList<>();
    }

    /**
     * Constructor para objectos da classe Menu.
     *
     * Cria um menu de opções sem event handlers.
     * Utilização de listas é útil para definir menus dinâmicos.
     *
     * @param titulo O titulo do menu
     * @param opcoes Uma lista de Strings com as opções do menu.
     */
    public Menu(String titulo, List<String> opcoes) {
        this.titulo = titulo;
        this.opcoes = new ArrayList<>(opcoes);
        this.disponivel = new ArrayList<>();
        this.handlers = new ArrayList<>();
        this.opcoes.forEach(s-> {
            this.disponivel.add(()->true);
            this.handlers.add(()->System.out.println("\nATENÇÃO: Opção não implementada!"));
        });
    }

    /**
     * Constructor para objectos da classe Menu.
     *
     * Cria um menu de opções sem event handlers.
     * Utilização de listas é útil para definir menus estáticos. P.e.:
     * Utilização de listas é útil para definir menus dinâmicos.
     *
     * @param opcoes Uma lista de Strings com as opções do menu.
     */
    public Menu(List<String> opcoes) { this("Menu", opcoes); }

    /**
     * Constructor para objectos da classe Menu.
     *
     * Cria um menu de opções sem event handlers.
     * Utilização de arrays é útil para definir menus estáticos. P.e.:
     *
     * new Menu(String[]{
     *     "Opção 1",
     *     "Opção 2",
     *     "Opção 3"
     * })
     *
     * @param titulo O titulo do menu
     * @param opcoes Um array de Strings com as opções do menu.
     */
    public Menu(String titulo, String[] opcoes) {
        this(titulo, Arrays.asList(opcoes));
    }

    /**
     * Constructor para objectos da classe Menu.
     *
     * Cria um menu de opções sem event handlers.
     * Utilização de arrays é útil para definir menus estáticos. P.e.:
     *
     * @param opcoes Um array de Strings com as opções do menu.
     */
    public Menu(String[] opcoes) {
        this(Arrays.asList(opcoes));
    }

    // Métodos de instância

    /**
     * Adicionar opções a um Menu.
     *
     * @param name A opção a apresentar.
     * @param p A pré-condição da opção.
     * @param h O event handler para a opção.
     */
    public void option(String name, PreCondition p, Handler h) {
        this.opcoes.add(name);
        this.disponivel.add(p);
        this.handlers.add(h);
    }

    /**
     * Correr o menu uma vez.
     */
    public void runOnce() {
        int op;
        show();
        op = readOption();
        // testar pré-condição
        if (op>0 && !this.disponivel.get(op-1).validate()) {
            System.out.println("Opção indisponível!");
        } else if (op>0) {
            // executar handler
            this.handlers.get(op-1).execute();
        }
    }

    /**
     * Correr o menu multiplas vezes.
     *
     * Termina com a opção 0 (zero).
     */
    public void run() {
        int op;
        do {
            show();
            op = readOption();
            // testar pré-condição
            if (op>0 && !this.disponivel.get(op-1).validate()) {
                System.out.println("Opção indisponível! Tente novamente.");
            } else if (op>0) {
                // executar handler
                this.handlers.get(op-1).execute();
            }
        } while (op != 0);
    }

    /**
     * Método que regista uma uma pré-condição numa opção do menu.
     *
     * @param i índice da opção (começa em 1)
     * @param b pré-condição a registar
     */
    public void setPreCondition(int i, PreCondition b) {
        this.disponivel.set(i-1,b);
    }

    /**
     * Método para registar um handler numa opção do menu.
     *
     * @param i indice da opção  (começa em 1)
     * @param h handlers a registar
     */
    public void setHandler(int i, Handler h) {
        this.handlers.set(i-1, h);
    }

    // Métodos auxiliares

    /** Apresentar o menu */
    private void show() {
        System.out.println("\n *** "+this.titulo+" *** ");
        for (int i=0; i<this.opcoes.size(); i++) {
            System.out.print(i+1);
            System.out.print(" - ");
            System.out.println(this.disponivel.get(i).validate()?this.opcoes.get(i):"---");
        }
        System.out.println("0 - Sair");
    }

    /** Ler uma opção válida */
    private int readOption() {
        int op;

        System.out.print("Opção: ");
        try {
            String line = is.nextLine();
            op = Integer.parseInt(line);
        }
        catch (NumberFormatException e) { // Não foi inscrito um int
            op = -1;
        }
        if (op<0 || op>this.opcoes.size()) {
            System.out.println("Opção Inválida!!!");
            op = -1;
        }
        return op;
    }

    /**
     * Lista o 1o classificado em cada volta
     */
    private String printPrimeiroVolta(Corrida corrida)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("\n||||| Primeiro carro a cada volta e desistentes |||||");
        List<Progresso> primeiroVolta= corrida.getPrimeiroVolta();
        for(int i=0; i<primeiroVolta.size();i++)
        {
            sb.append("\n");
            sb.append(i+1);sb.append("ª Volta: ");
            sb.append(primeiroVolta.get(i).getCarro().getMarca());sb.append(" ");
            sb.append(primeiroVolta.get(i).getCarro().getModelo());sb.append(" ");
            if(primeiroVolta.get(i).getCarro().checkDNF(i+1, primeiroVolta.size(), corrida.getClima()))
                sb.append("DESISTIU");
        }
        return sb.toString();
    }



    /**
     * Lista os resultados da corrida.
     */ 
   public String printResultados(Corrida corrida, Circuito circuito)
   {
       StringBuilder sb = new StringBuilder();
       int i = 1;
       sb.append("\n||||| ");sb.append(circuito.getNome());sb.append(" |||||");
       sb.append("\n||||| ");sb.append("Voltas: ");sb.append(circuito.getNVoltas());sb.append(" |||||");
       sb.append("\n||||| ");sb.append("Distancia: ");sb.append(circuito.getComprimento());sb.append("km | ");
       sb.append("Condição meteorológica: ");
       if(corrida.getClima() == 0)
        {
            sb.append("Sol");
        }
       else
        {
            sb.append("Chuva");;
        }
       sb.append(" |||||");
       sb.append("\n||||| ");sb.append("Record: ");sb.append(TimeConverter.toTimeFormat(this.circuito.getRecord().getTempo()));
       sb.append(" | Piloto: ");sb.append(corrida.getRecord().getPiloto().getNome());
       sb.append(" Carro: ");sb.append(circuito.getRecord().getCarro().getMarca());
       sb.append(" ");sb.append(circuito.getRecord().getCarro().getModelo());
       sb.append(" Equipa: ");sb.append(circuito.getRecord().getCarro().getEquipa().getNome());
       sb.append("\n\n||||| Classificacoes da corrida |||||");
       for(Carro c : corrida.)
       {
            sb.append("\n");
            sb.append(i);sb.append("º: ");
            sb.append(TimeConverter.toTimeFormat(c.getTempo()));
            sb.append("\t Categoria: "); sb.append(c.getClass().getName()); sb.append(" ");
            sb.append("\t Carro: "); sb.append(c.getMarca()); sb.append(" ");
            sb.append(c.getModelo());
            sb.append("\t Equipa: ");sb.append(c.getEquipa().getNome());
            i++;
       }      
       sb.append("\n\n||||| Classificacoes da corrida Hibridos |||||");
       i=1;
       for(Carro c : this.resultados)
       {
            if(c instanceof Hibrido)
            {
            sb.append("\n");
            sb.append(i);sb.append("º: ");
            sb.append(TimeConverter.toTimeFormat(c.getTempo()));
            sb.append("\t Categoria: "); sb.append(c.getClass().getName()); sb.append(" ");
            sb.append("\t Carro: "); sb.append(c.getMarca()); sb.append(" ");
            sb.append(c.getModelo());
            sb.append("\t Equipa: ");sb.append(c.getEquipa().getNome());
            i++;
            }
       }      
       sb.append(this.printPrimeiroVolta());
       return sb.toString();
   }
}
