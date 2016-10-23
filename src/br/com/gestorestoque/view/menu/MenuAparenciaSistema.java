/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestorestoque.view.menu;

import java.awt.Component;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.plaf.basic.BasicTextUI;
import javax.swing.text.DefaultCaret;

/**
 *
 * @author DG
 */
public class MenuAparenciaSistema extends JMenu {

    public MenuAparenciaSistema() {
        setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gestorestoque/view/Imagens/looknfeel.png")));
        setText("Aparência");
        
        
        add(new JRadioButtonMenuItemLAF("com.sun.java.swing.plaf.windows.WindowsLookAndFeel", "Windows", ""));
        add(new JRadioButtonMenuItemLAF("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel", "Windows Classic", ""));
        add(new JRadioButtonMenuItemLAF("com.sun.java.swing.plaf.motif.MotifLookAndFeel", "Motif", ""));
        add(new JRadioButtonMenuItemLAF("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel", "Nimbus", ""));
        add(new JRadioButtonMenuItemLAF("com.birosoft.liquid.LiquidLookAndFeel", "Liquid", ""));
        add(new JRadioButtonMenuItemLAF("javax.swing.plaf.metal.MetalLookAndFeel", "Metal", ""));
        add(new JRadioButtonMenuItemLAF("de.javasoft.plaf.synthetica.SyntheticaStandardLookAndFeel", "Synthetica", ""));
        add(new JRadioButtonMenuItemLAF("com.alee.laf.WebLookAndFeel", "WebLaf", ""));
        add(new JRadioButtonMenuItemLAF("org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel", "Substance - AutumnSkin", "org.pushingpixels.substance.api.skin.AutumnSkin"));
        add(new JRadioButtonMenuItemLAF("org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel", "Substance - BusinessBlackSteelSkin", "org.pushingpixels.substance.api.skin.BusinessBlackSteelSkin"));
        add(new JRadioButtonMenuItemLAF("org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel", "Substance - BusinessBlueSteelSkin", "org.pushingpixels.substance.api.skin.BusinessBlueSteelSkin"));
        add(new JRadioButtonMenuItemLAF("org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel", "Substance - BusinessSkin", "org.pushingpixels.substance.api.skin.BusinessSkin"));
        add(new JRadioButtonMenuItemLAF("org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel", "Substance - ChallengerDeepSkin", "org.pushingpixels.substance.api.skin.ChallengerDeepSkin"));
        add(new JRadioButtonMenuItemLAF("org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel", "Substance - CremeCoffeeSkin", "org.pushingpixels.substance.api.skin.CremeCoffeeSkin"));
        add(new JRadioButtonMenuItemLAF("org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel", "Substance - CremeSkin", "org.pushingpixels.substance.api.skin.CremeSkin"));
        add(new JRadioButtonMenuItemLAF("org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel", "Substance - DustCoffeeSkin", "org.pushingpixels.substance.api.skin.DustCoffeeSkin"));
        add(new JRadioButtonMenuItemLAF("org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel", "Substance - DustSkin", "org.pushingpixels.substance.api.skin.DustSkin"));
        add(new JRadioButtonMenuItemLAF("org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel", "Substance - EmeraldDuskSkin", "org.pushingpixels.substance.api.skin.EmeraldDuskSkin"));
        add(new JRadioButtonMenuItemLAF("org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel", "Substance - GeminiSkin", "org.pushingpixels.substance.api.skin.GeminiSkin"));
        add(new JRadioButtonMenuItemLAF("org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel", "Substance - GraphiteAquaSkin", "org.pushingpixels.substance.api.skin.GraphiteAquaSkin"));
        add(new JRadioButtonMenuItemLAF("org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel", "Substance - GraphiteGlassSkin", "org.pushingpixels.substance.api.skin.GraphiteGlassSkin"));
        add(new JRadioButtonMenuItemLAF("org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel", "Substance - GraphiteSkin", "org.pushingpixels.substance.api.skin.GraphiteSkin"));
        add(new JRadioButtonMenuItemLAF("org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel", "Substance - MagellanSkin", "org.pushingpixels.substance.api.skin.MagellanSkin"));
        add(new JRadioButtonMenuItemLAF("org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel", "Substance - MarinerSkin", "org.pushingpixels.substance.api.skin.MarinerSkin"));
        add(new JRadioButtonMenuItemLAF("org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel", "Substance - MistAquaSkin", "org.pushingpixels.substance.api.skin.MistAquaSkin"));
        add(new JRadioButtonMenuItemLAF("org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel", "Substance - MistSilverSkin", "org.pushingpixels.substance.api.skin.MistSilverSkin"));
        add(new JRadioButtonMenuItemLAF("org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel", "Substance - ModerateSkin", "org.pushingpixels.substance.api.skin.ModerateSkin"));
        add(new JRadioButtonMenuItemLAF("org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel", "Substance - NebulaBrickWallSkin", "org.pushingpixels.substance.api.skin.NebulaBrickWallSkin"));
        add(new JRadioButtonMenuItemLAF("org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel", "Substance - NebulaSkin", "org.pushingpixels.substance.api.skin.NebulaSkin"));
        add(new JRadioButtonMenuItemLAF("org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel", "Substance - OfficeBlack2007Skin", "org.pushingpixels.substance.api.skin.OfficeBlack2007Skin"));
        add(new JRadioButtonMenuItemLAF("org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel", "Substance - OfficeBlue2007Skin", "org.pushingpixels.substance.api.skin.OfficeBlue2007Skin"));
        add(new JRadioButtonMenuItemLAF("org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel", "Substance - OfficeSilver2007Skin", "org.pushingpixels.substance.api.skin.OfficeSilver2007Skin"));
        add(new JRadioButtonMenuItemLAF("org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel", "Substance - RavenSkin", "org.pushingpixels.substance.api.skin.RavenSkin"));
        add(new JRadioButtonMenuItemLAF("org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel", "Substance - SaharaSkin", "org.pushingpixels.substance.api.skin.SaharaSkin"));
        add(new JRadioButtonMenuItemLAF("org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel", "Substance - TwilightSkin", "org.pushingpixels.substance.api.skin.TwilightSkin"));
        
        Component[] componentes = this.getMenuComponents();
        JRadioButtonMenuItem weblaf = (JRadioButtonMenuItem) componentes[7];
        weblaf.setSelected(true);
        

    }

}
