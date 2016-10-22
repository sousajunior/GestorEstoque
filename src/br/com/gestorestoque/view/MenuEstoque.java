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
public class MenuEstoque extends JMenu{

    public MenuEstoque() {
        setText("Estoque");
        add(new JItemMenu("/br/com/gestorestoque/view/Imagens/entrada-16.png", "Entrada", EnumFRM.ENTRADA));
        add(new JItemMenu("/br/com/gestorestoque/view/Imagens/saida-16.png", "Saída", EnumFRM.SAIDA));
        add(new JItemMenu("/br/com/gestorestoque/view/Imagens/inventario-16.png", "Inventário", EnumFRM.INVENTARIO));
        add(new JItemMenu("/br/com/gestorestoque/view/Imagens/estoque-16.png", "Saldos em estoque", EnumFRM.SALDOS));
        add(new JItemMenu("/br/com/gestorestoque/view/Imagens/historicoMovimentacao.png", "Histórico de movimentações", EnumFRM.MOVIMENTACOES));
    }
    
}
