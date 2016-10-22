/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestorestoque.view;

import br.com.gestorestoque.view.JItemMenu;
import br.com.gestorestoque.view.menu.fabrica.EnumFRM;
import javax.swing.JMenu;

/**
 *
 * @author DG
 */
public class MenuCadastros extends JMenu{

    public MenuCadastros() {
        setText("Cadastros");
        add(new JItemMenu("/br/com/gestorestoque/view/Imagens/estoque-16.png", "Produto", EnumFRM.PRODUTO));
        add(new JItemMenu("/br/com/gestorestoque/view/Imagens/fornecedor.png", "Fornecedor", EnumFRM.FORNECEDOR));
        add(new JItemMenu("/br/com/gestorestoque/view/Imagens/armazem.png", "Armazém", EnumFRM.ARMAZEM));
        add(new JItemMenu("/br/com/gestorestoque/view/Imagens/medida.png", "Unidade de Medida", EnumFRM.UNIDADE_MEDIDA));
    }
    
   
    
}
