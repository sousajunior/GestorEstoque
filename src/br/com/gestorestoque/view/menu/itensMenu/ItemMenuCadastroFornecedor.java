/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestorestoque.view.menu.itensMenu;

import br.com.gestorestoque.view.FRMCadastroFornecedor;
import br.com.gestorestoque.view.FRMPrincipal;
import javax.swing.JMenuItem;

/**
 *
 * @author DG
 */
public class ItemMenuCadastroFornecedor extends JMenuItem {

    public ItemMenuCadastroFornecedor() {
        montarItemMenu();
    }

    public void montarItemMenu() {
        setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gestorestoque/view/Imagens/fornecedor.png"))); // NOI18N
        setText("Fornecedor");

        addActionListener((e) -> {
            new FRMCadastroFornecedor(FRMPrincipal.getInstance(), true).setVisible(true);

        });

    }

}
