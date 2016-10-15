package br.com.gestorestoque.view.menu.itensMenu;

import br.com.gestorestoque.view.FRMInventario;
import br.com.gestorestoque.view.FRMPrincipal;
import javax.swing.JMenuItem;

/**
 *
 * @author DG
 */
public class ItemMenuEstoqueInventario extends JMenuItem {

    public ItemMenuEstoqueInventario() {
        montarItemMenu();
    }

    public void montarItemMenu() {
        setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gestorestoque/view/Imagens/inventario-16.png"))); // NOI18N
        setText("Inventário");

        addActionListener((e) -> {
            new FRMInventario(FRMPrincipal.getInstance(), true).setVisible(true);

        });

    }

}
