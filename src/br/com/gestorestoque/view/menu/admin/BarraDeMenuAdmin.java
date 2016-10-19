package br.com.gestorestoque.view.menu.admin;

import br.com.gestorestoque.view.menu.almoxarifado.BarraDeMenuAlmoxarifado;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import br.com.gestorestoque.view.menu.fabrica.BarraMenu;


public class BarraDeMenuAdmin extends JMenuBar implements BarraMenu{

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
        menu.add( new JMenuItem("Logoff"));
        menu.add( new JMenuItem("Acesso Grupos"));
        menu.add( new JMenuItem("Cadastro de usuários"));
        
        BarraMenu barraMenu = new BarraDeMenuAlmoxarifado();
        JMenuBar menuBar = barraMenu.montarMenu();// .add(menu);
        menuBar.add(menu);
        
        return menuBar;
    }
    
}
