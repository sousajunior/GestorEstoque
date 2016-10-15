package br.com.gestorestoque.view.menu.itensMenu;

import br.com.gestorestoque.view.FRMCadastroArmazem;
import br.com.gestorestoque.view.FRMPrincipal;
import javax.swing.JMenuItem;

/**
 *
 * @author DG
 */
public class ItemMenuCadastroArmazem extends JMenuItem {

    public ItemMenuCadastroArmazem() {
        montarItemMenu();
    }

    public void montarItemMenu() {
        setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gestorestoque/view/Imagens/armazem.png"))); // NOI18N
        setText("Armazém");

        addActionListener((e) -> {
            new FRMCadastroArmazem(FRMPrincipal.getInstance(), true).setVisible(true);

        });

    }

}
