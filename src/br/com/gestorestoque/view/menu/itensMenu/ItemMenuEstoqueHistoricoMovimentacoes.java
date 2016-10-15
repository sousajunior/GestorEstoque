package br.com.gestorestoque.view.menu.itensMenu;

import br.com.gestorestoque.view.FRMMovimentacao;
import br.com.gestorestoque.view.FRMPrincipal;
import javax.swing.JMenuItem;

/**
 *
 * @author DG
 */
public class ItemMenuEstoqueHistoricoMovimentacoes extends JMenuItem {

    public ItemMenuEstoqueHistoricoMovimentacoes() {
        montarItemMenu();
    }

    public void montarItemMenu() {
        setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gestorestoque/view/Imagens/historicoMovimentacao.png"))); // NOI18N
        setText("Histórico de movimentações");

        addActionListener((e) -> {
            new FRMMovimentacao(FRMPrincipal.getInstance(), true).setVisible(true);

        });

    }

}
