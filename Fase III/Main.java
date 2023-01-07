
/**
 * @author A97540 Ana Rita Vaz
 * @author A92847 Gilherme Sousa Martins
 * @author A97777 Millena de Freitas Santos
 * @author A96794 Ricardo Alves Oliveira 
 */

import ui.TextUI;
 
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
            e.printStackTrace();
        }
    }


}