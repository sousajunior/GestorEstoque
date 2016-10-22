/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestorestoque.view.menu;

import br.com.gestorestoque.view.menu.JItemMenu;
import br.com.gestorestoque.view.menu.fabrica.EnumFRM;
import javax.swing.JMenu;

/**
 *
 * @author DG
 */
public class MenuSistema extends JMenu{

    public MenuSistema() {
        setText("Sistema");
        add(new MenuAparenciaSistema());
        add(new JItemMenu("/br/com/gestorestoque/view/Imagens/sobre.png", "Sobre", EnumFRM.SOBRE));
    }
    
            
}
