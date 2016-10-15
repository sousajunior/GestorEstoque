/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestorestoque.view.menu.itensMenu;

import br.com.gestorestoque.view.FRMCadastroUnidadeMedida;
import br.com.gestorestoque.view.FRMPrincipal;
import javax.swing.JMenuItem;

/**
 *
 * @author DG
 */
public class ItemMenuCadastroUnidadeMedida extends JMenuItem {

    public ItemMenuCadastroUnidadeMedida() {
        montarItemMenu();
    }

    public void montarItemMenu() {
        setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gestorestoque/view/Imagens/medida.png"))); // NOI18N
        setText("Unidade de medida");

        addActionListener((e) -> {
            new FRMCadastroUnidadeMedida(FRMPrincipal.getInstance(), true).setVisible(true);
        });

    }

}
