package br.com.gestorestoque.util;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author DG Geam Felipe Dos Santos
 */
public class FRMUtil {

    /**
     * Metodo que seta os ícones nos cantos da tela.
     * É obrigatório passar por parâmetro pelo menos um, JFrame ou um JDialog.
     * @param frame
     * @param dialog
     * 
     */
    public void setarIcone(JFrame frame, JDialog dialog) {

        // coloca uma figura na barra de título da janela  
        URL url = getClass().getResource("/br/com/gestorestoque/view/imagens/icone.png");
        Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);
        
        if (frame != null) {
            frame.setIconImage(imagemTitulo);
        }

        if (dialog != null) {
            dialog.setIconImage(imagemTitulo);
        }

    }
    
    /**
     * Método altera o lookAndFeel da aplicação, na chamado do método, 
     * é necessário passar a String do lookAndFeel e o Frame.
     * @param lookAndFeel String
     * @param frame JFrame
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws UnsupportedLookAndFeelException 
     */
    public static void alterarLookAndFeel(String lookAndFeel, JFrame frame) throws ClassNotFoundException, IllegalAccessException, InstantiationException, UnsupportedLookAndFeelException {

        try {
            UIManager.setLookAndFeel(lookAndFeel);
            SwingUtilities.updateComponentTreeUI(frame);

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException erro) {
            throw erro;
        }

    }

}
