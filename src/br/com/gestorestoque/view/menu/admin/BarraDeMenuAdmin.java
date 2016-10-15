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

    
    protected void criaItensDoMenu() {
        
        
        
        JMenu menu = new JMenu("Admin Menu");
        menu.add( new JMenuItem("logoff"));
        menu.add( new JMenuItem("Cadastrar produto"));
        menu.add( new JMenuItem("Cadastrar atendente"));
        add(menu);
        add(new BarraDeMenuAlmoxarifado().criaItensDoMenu());//cria o menu de atendente
        
    }

    /**
     *
     * @return
     */
    @Override
    public JMenuBar montarMenu() {
        criaItensDoMenu();
        return this;
    }
    
}
