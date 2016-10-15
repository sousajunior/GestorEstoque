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
public class RadioButtonItemMenuWebLaf extends JRadioButtonMenuItem {

    public RadioButtonItemMenuWebLaf() {
        montarItemMenu();
    }

    public void montarItemMenu() {
        
        setText("WebLaf");

        addActionListener((e) -> {
                 try {
        
                FRMUtil.alterarLookAndFeel("com.alee.laf.WebLookAndFeel", FRMPrincipal.getInstance());

            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
                JOptionPane.showMessageDialog(this, "Ocorreu um erro ao alterar a aparência do sistema!\nErro: " + ex.getMessage(), "GGlass - Erro", 0);
            }

        });

    }

}
