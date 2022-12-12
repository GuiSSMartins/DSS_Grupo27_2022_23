package  DSS_Grupo27_2022_23.FaseIII;

import DSS_Grupo27_2022_23.FaseIII.ui.TextUI;

public class Main {

    /**
     * O método main cria a aplicação e invoca o método run()
     */
    public static void main(String[] args) {
        try {
            new TextUI().run();
        }
        catch (Exception e) {
            System.out.println("Não foi possível arrancar: "+e.getMessage());
        }
    }


}
