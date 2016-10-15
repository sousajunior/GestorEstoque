package br.com.gestorestoque.view.menu.itensMenu;

import br.com.gestorestoque.view.FRMCadastroEntradaSaida;
import br.com.gestorestoque.view.FRMPrincipal;
import javax.swing.JMenuItem;

/**
 *
 * @author DG
 */
public class ItemMenuEstoqueEntrada extends JMenuItem {

    public ItemMenuEstoqueEntrada() {
        montarItemMenu();
    }

    public void montarItemMenu() {
        setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gestorestoque/view/Imagens/entrada-16.png"))); // NOI18N
        setText("Entrada");

        addActionListener((e) -> {
            new FRMCadastroEntradaSaida(FRMPrincipal.getInstance(), true,true,"Entrada de produtos").setVisible(true);

        });

    }

}
