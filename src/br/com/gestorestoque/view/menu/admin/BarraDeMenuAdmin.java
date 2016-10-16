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
        
        
        add(new BarraDeMenuAlmoxarifado().criaItensDoMenu());//cria o menu de atendente
        JMenu menu = new JMenu("Admin Menu");
        menu.add( new JMenuItem("Logoff"));
        menu.add( new JMenuItem("Acesso Grupos"));
        menu.add( new JMenuItem("Cadastro de usuários"));
        add(menu);
        
        
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
