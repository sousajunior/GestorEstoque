
package br.com.gestorestoque.view.menu.almoxarifado;

import br.com.gestorestoque.view.menu.fabrica.BarraMenu;
import br.com.gestorestoque.view.menu.MenuCadastros;
import br.com.gestorestoque.view.menu.MenuEstoque;
import br.com.gestorestoque.view.menu.MenuSistema;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;



public class BarraDeMenuAlmoxarifado extends JMenuBar implements BarraMenu{

    public static final String BUSCAR_PRODUTOS = "Buscar produtos";
    public static final String LISTAR_PRODUTOS = "Listar todos os produtos cadastrados";
    
    
    public BarraDeMenuAlmoxarifado() {
        super();
 //       criaItensDoMenu();
        //montarMenu();
    }

    @Override
    public JMenuBar montarMenu() {
                //Menu de cadastros
        MenuCadastros menuCadastros = new MenuCadastros();              
        //Menu de estoque
        MenuEstoque menuEstoque = new MenuEstoque();
        //Menu Sistema
        MenuSistema menuSistema = new MenuSistema();
        
        add(menuCadastros);
        add(menuEstoque);
        add(menuSistema);
    
        return this;
    }
    
}
