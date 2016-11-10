package br.com.gestorestoque.view.menu.admin;

import br.com.gestorestoque.view.FRMPrincipal;
import br.com.gestorestoque.view.menu.almoxarifado.BarraDeMenuAlmoxarifado;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import br.com.gestorestoque.view.menu.fabrica.BarraMenu;
import java.awt.Component;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

public class BarraDeMenuAdmin extends JMenuBar implements BarraMenu {

    public BarraDeMenuAdmin() {
        super();

        //  montarMenu();
    }

    /**
     *
     * @return
     */
    @Override
    public JMenuBar montarMenu() {
        JMenu menu = new JMenu("Admin Menu");
        menu.add(new JMenuItem("Acesso Grupos"));
        menu.add(new JMenuItem("Cadastro de usuários"));

        Component menuItens[] = menu.getMenuComponents();
        for (int i = 0; i < menuItens.length; i++) {

            JMenuItem menuItem = (JMenuItem) menuItens[i];            
            
            menuItem.addActionListener((e) -> {
                JOptionPane.showMessageDialog(null, "Ação ainda não implementada", "Desculpe,", JOptionPane.WARNING_MESSAGE);
            });

        }

        BarraMenu barraMenu = new BarraDeMenuAlmoxarifado();
        JMenuBar menuBar = barraMenu.montarMenu();// .add(menu);
        menuBar.add(menu);

        return menuBar;
    }

}
