/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestorestoque.view;

import br.com.gestorestoque.util.FRMUtil;
import javafx.scene.control.RadioButton;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author 5927161
 */
public class RadioButtonMenuItemLAF extends JRadioButtonMenuItem {
    private final String url,nome;

    public RadioButtonMenuItemLAF(String url, String nome) {
        this.url = url;
        this.nome = nome;
        montarItemMenu();
    }
    
    
    
    private void montarItemMenu() {
        
        this.setText(nome);

        addActionListener((e) -> {
               try {
        
                   
                FRMUtil.alterarLookAndFeel(url, FRMPrincipal.getInstance());
                FRMUtil.uncheck((JPopupMenu)this.getParent());
                this.setSelected(true);

            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
                JOptionPane.showMessageDialog(this, "Ocorreu um erro ao alterar a aparência do sistema!\nErro: " + ex.getMessage(), "GGlass - Erro", 0);
            }

        });

    }
}
