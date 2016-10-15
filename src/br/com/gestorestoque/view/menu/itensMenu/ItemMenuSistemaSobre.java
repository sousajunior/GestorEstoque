package br.com.gestorestoque.view.menu.itensMenu;

import br.com.gestorestoque.view.FRMPrincipal;
import br.com.gestorestoque.view.FRMSobre;
import javax.swing.JMenuItem;

/**
 *
 * @author DG
 */
public class ItemMenuSistemaSobre extends JMenuItem {

    public ItemMenuSistemaSobre() {
        montarItemMenu();
    }

    public void montarItemMenu() {
        setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gestorestoque/view/Imagens/sobre.png"))); // NOI18N
        setText("Sobre");

        addActionListener((e) -> {
            new FRMSobre(FRMPrincipal.getInstance(), true).setVisible(true);

        });

    }

}
