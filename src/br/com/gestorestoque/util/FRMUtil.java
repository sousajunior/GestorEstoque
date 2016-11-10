package br.com.gestorestoque.util;

import java.awt.Component;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;

/**
 *
 * @author DG Geam Felipe Dos Santos
 */
public class FRMUtil {

    /**
     * Metodo que seta os ícones nos cantos da tela. É obrigatório passar por
     * parâmetro pelo menos um, JFrame ou um JDialog.
     *
     * @param frame
     * @param dialog
     *
     */
    public void setarIcone(JFrame frame, JDialog dialog) {

        // coloca uma figura na barra de título da janela  
        URL url = getClass().getResource("/br/com/gestorestoque/view/imagens/iconeSistema.png");
        Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);

        if (frame != null) {
            frame.setIconImage(imagemTitulo);
        }

        if (dialog != null) {
            dialog.setIconImage(imagemTitulo);
        }

    }

    /**
     * Método altera o lookAndFeel da aplicação, na chamado do método, é
     * necessário passar a String do lookAndFeel e o Frame.
     *
     * @param lookAndFeel String
     * @param componente JFrame
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws UnsupportedLookAndFeelException
     */
    public static void alterarLookAndFeel(String lookAndFeel, Component componente, String skin) throws ClassNotFoundException, IllegalAccessException, InstantiationException, UnsupportedLookAndFeelException {

        try {
            UIManager.setLookAndFeel(lookAndFeel);
            SwingUtilities.updateComponentTreeUI(componente);
            if (!skin.equalsIgnoreCase("")) {
                SubstanceLookAndFeel.setSkin(skin);
            }
            componente.revalidate();
            componente.repaint();
        } catch (UnsupportedLookAndFeelException erro) {
            throw erro;
        }

    }

    public static void uncheck(JPopupMenu menu) {

        for (Component radio : menu.getComponents()) {

            JRadioButtonMenuItem jr = (JRadioButtonMenuItem) radio;
            if (jr.isSelected()) {
                jr.setSelected(false);
            }

        }
    }

}
