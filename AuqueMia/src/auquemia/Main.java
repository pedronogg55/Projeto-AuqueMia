package auquemia;

import auquemia.view.MenuJFrame;
import auquemia.DAO.*;

public class Main {

    public static void main(String[] args) {
       
       BancoDeDados banco = new BancoDeDados();
       banco.conectar();
         
       MenuJFrame menu = new MenuJFrame();
       menu.setVisible(true);
       
    }
}
