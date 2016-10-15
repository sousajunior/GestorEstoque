package br.com.gestorestoque.view.menu.itensMenu;

import br.com.gestorestoque.util.FRMUtil;
import br.com.gestorestoque.view.FRMPrincipal;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author DG
 */
public class RadioButtonItemMenuWindows extends JRadioButtonMenuItem {

    public RadioButtonItemMenuWindows() {
        montarItemMenu();
    }

    public void montarItemMenu() {
        
        setText("Windows");

        addActionListener((e) -> {
               try {
        
                FRMUtil.alterarLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel", FRMPrincipal.getInstance());

            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
                JOptionPane.showMessageDialog(this, "Ocorreu um erro ao alterar a aparência do sistema!\nErro: " + ex.getMessage(), "GGlass - Erro", 0);
            }

        });

    }

}
