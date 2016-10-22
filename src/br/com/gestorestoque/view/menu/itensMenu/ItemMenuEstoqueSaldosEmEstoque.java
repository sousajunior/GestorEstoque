package br.com.gestorestoque.view.menu.itensMenu;

import br.com.gestorestoque.view.FRMPrincipal;
import br.com.gestorestoque.view.FRMSaldoEstoque;
import javax.swing.JMenuItem;

/**
 *
 * @author DG
 */
public class ItemMenuEstoqueSaldosEmEstoque extends JMenuItem {

    public ItemMenuEstoqueSaldosEmEstoque() {
        montarItemMenu();
    }

    public void montarItemMenu() {
        setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gestorestoque/view/Imagens/estoque-16.png"))); // NOI18N
        setText("Saldos em estoque");

        addActionListener((e) -> {
            new FRMSaldoEstoque(FRMPrincipal.getInstance(), true).setVisible(true);

        });

    }

}
