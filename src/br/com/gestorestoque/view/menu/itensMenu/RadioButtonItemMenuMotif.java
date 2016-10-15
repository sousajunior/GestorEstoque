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
public class RadioButtonItemMenuMotif extends JRadioButtonMenuItem {

    public RadioButtonItemMenuMotif() {
        montarItemMenu();
    }

    public void montarItemMenu() {

        setText("Motif");

        addActionListener((e) -> {
            try {
                
                FRMUtil.alterarLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel", FRMPrincipal.getInstance());

            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
                JOptionPane.showMessageDialog(this, "Ocorreu um erro ao alterar a aparência do sistema!\nErro: " + ex.getMessage(), "GGlass - Erro", 0);
            }
        });

    }

}