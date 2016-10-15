/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestorestoque.view.menu.menus;

import br.com.gestorestoque.view.menu.itensMenu.RadioButtonItemMenuLiquid;
import br.com.gestorestoque.view.menu.itensMenu.RadioButtonItemMenuMetal;
import br.com.gestorestoque.view.menu.itensMenu.RadioButtonItemMenuMotif;
import br.com.gestorestoque.view.menu.itensMenu.RadioButtonItemMenuNimbus;
import br.com.gestorestoque.view.menu.itensMenu.RadioButtonItemMenuSynthetica;
import br.com.gestorestoque.view.menu.itensMenu.RadioButtonItemMenuWebLaf;
import br.com.gestorestoque.view.menu.itensMenu.RadioButtonItemMenuWindows;
import br.com.gestorestoque.view.menu.itensMenu.RadioButtonItemMenuWindowsClassic;
import javax.swing.JMenu;

/**
 *
 * @author DG
 */
public class MenuAparenciaSistema extends JMenu{

    public MenuAparenciaSistema() {
    montarMenu();
    }
    
    public void montarMenu(){
        setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gestorestoque/view/Imagens/looknfeel.png"))); // NOI18N
        setText("Aparência");
        
        add(new RadioButtonItemMenuWindows());
        add(new RadioButtonItemMenuWindowsClassic());
        add(new RadioButtonItemMenuMotif());
        add(new RadioButtonItemMenuNimbus());
        add(new RadioButtonItemMenuLiquid());
        add(new RadioButtonItemMenuMetal());
        add(new RadioButtonItemMenuSynthetica());
        add(new RadioButtonItemMenuWebLaf());        
    }
    
}
