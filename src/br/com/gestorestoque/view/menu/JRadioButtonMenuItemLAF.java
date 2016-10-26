/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestorestoque.view.menu;

import br.com.gestorestoque.util.FRMUtil;
import br.com.gestorestoque.view.FRMPrincipal;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author 5927161
 */
public class JRadioButtonMenuItemLAF extends JRadioButtonMenuItem {

    private final String url, nome, skin;

    public JRadioButtonMenuItemLAF(String url, String nome, String skin) {
        this.url = url;
        this.nome = nome;
        this.skin = skin;
        montarItemMenu();
    }

    private void montarItemMenu() {

        this.setText(nome);

        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                acionarItem();
            }
           
            
            
            @Override
            public void mouseEntered(MouseEvent e) {
                acionarItem();
            }
           
            
        });

        addActionListener((e) -> {
            acionarItem();
        });

    }

    private void acionarItem() {
        try {

            FRMUtil.alterarLookAndFeel(url, FRMPrincipal.getInstance(), skin);
            FRMUtil.uncheck((JPopupMenu) this.getParent());
            this.setSelected(true);

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao alterar a aparência do sistema!\nErro: " + ex.getMessage(), "GGlass - Erro", 0);
        }
    }
}
