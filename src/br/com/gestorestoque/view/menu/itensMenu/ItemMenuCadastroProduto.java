/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestorestoque.view.menu.itensMenu;

import br.com.gestorestoque.view.FRMCadastroProduto;
import br.com.gestorestoque.view.FRMPrincipal;
import javax.swing.JMenuItem;

/**
 *
 * @author DG
 */
public class ItemMenuCadastroProduto extends JMenuItem {

    public ItemMenuCadastroProduto() {
        montarItemMenu();
    }

    public void montarItemMenu() {
        setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gestorestoque/view/Imagens/estoque-16.png"))); // NOI18N
        setText("Produto");

        addActionListener((e) -> {
            new FRMCadastroProduto(FRMPrincipal.getInstance(), true).setVisible(true);
        });

    }

}
