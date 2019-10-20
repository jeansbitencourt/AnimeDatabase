/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animedatabase;

import animedatabase.views.Tela;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Jean Bitencourt
 */
public class AnimeDatabase {
    
    public static Tela tela;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        try {
            javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
        tela = new Tela();
        tela.setVisible(true);
    }

}
