/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestorestoque.view.menu;

import br.com.gestorestoque.view.menu.JRadioButtonMenuItemLAF;
import java.awt.Component;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;

/**
 *
 * @author DG
 */
public class MenuAparenciaSistema extends JMenu{

    public MenuAparenciaSistema() {
        setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gestorestoque/view/Imagens/looknfeel.png")));
        setText("Aparência");
        
        add(new JRadioButtonMenuItemLAF("com.sun.java.swing.plaf.windows.WindowsLookAndFeel", "Windows"));
        add(new JRadioButtonMenuItemLAF("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel", "Windows Classic"));
        add(new JRadioButtonMenuItemLAF("com.sun.java.swing.plaf.motif.MotifLookAndFeel", "Motif"));
        add(new JRadioButtonMenuItemLAF("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel", "Nimbus"));
        add(new JRadioButtonMenuItemLAF("com.birosoft.liquid.LiquidLookAndFeel", "Liquid"));
        add(new JRadioButtonMenuItemLAF("javax.swing.plaf.metal.MetalLookAndFeel", "Metal"));
        add(new JRadioButtonMenuItemLAF("de.javasoft.plaf.synthetica.SyntheticaStandardLookAndFeel", "Synthetica"));
        add(new JRadioButtonMenuItemLAF("com.alee.laf.WebLookAndFeel", "WebLaf"));
        Component[] componentes = this.getMenuComponents();        
        JRadioButtonMenuItem weblaf = (JRadioButtonMenuItem)componentes[7];
        weblaf.setSelected(true);
        
      
    }

}
