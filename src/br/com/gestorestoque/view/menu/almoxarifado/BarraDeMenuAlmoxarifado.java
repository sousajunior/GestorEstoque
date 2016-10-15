
package br.com.gestorestoque.view.menu.almoxarifado;

import br.com.gestorestoque.view.menu.fabrica.BarraMenu;
import br.com.gestorestoque.view.menu.itensMenu.ItemMenuCadastroArmazem;
import br.com.gestorestoque.view.menu.itensMenu.ItemMenuCadastroFornecedor;
import br.com.gestorestoque.view.menu.itensMenu.ItemMenuCadastroProduto;
import br.com.gestorestoque.view.menu.itensMenu.ItemMenuCadastroUnidadeMedida;
import br.com.gestorestoque.view.menu.itensMenu.ItemMenuEstoqueEntrada;
import br.com.gestorestoque.view.menu.itensMenu.ItemMenuEstoqueHistoricoMovimentacoes;
import br.com.gestorestoque.view.menu.itensMenu.ItemMenuEstoqueInventario;
import br.com.gestorestoque.view.menu.itensMenu.ItemMenuEstoqueSaida;
import br.com.gestorestoque.view.menu.itensMenu.ItemMenuEstoqueSaldosEmEstoque;
import br.com.gestorestoque.view.menu.itensMenu.ItemMenuSistemaSobre;
import br.com.gestorestoque.view.menu.menus.MenuAparenciaSistema;
import br.com.gestorestoque.view.menu.menus.MenuCadastros;
import br.com.gestorestoque.view.menu.menus.MenuEstoque;
import br.com.gestorestoque.view.menu.menus.MenuSistema;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;



public class BarraDeMenuAlmoxarifado extends JMenuBar implements BarraMenu{

    public static final String BUSCAR_PRODUTOS = "Buscar produtos";
    public static final String LISTAR_PRODUTOS = "Listar todos os produtos cadastrados";
    
    
    public BarraDeMenuAlmoxarifado() {
        super();
 //       criaItensDoMenu();
       // montarMenu();
    }

    
    
    public JMenu criaItensDoMenu() {
        
        //Menu de cadastros
        MenuCadastros menuCadastros = new MenuCadastros();
        menuCadastros.add(new ItemMenuCadastroProduto());
        menuCadastros.add(new ItemMenuCadastroFornecedor());
        menuCadastros.add(new ItemMenuCadastroArmazem());
        menuCadastros.add(new ItemMenuCadastroUnidadeMedida());
        
        //Menu de estoque
        MenuEstoque menuEstoque = new MenuEstoque();
        menuEstoque.add(new ItemMenuEstoqueEntrada());
        menuEstoque.add(new ItemMenuEstoqueSaida());
        menuEstoque.add(new ItemMenuEstoqueInventario());
        menuEstoque.add(new ItemMenuEstoqueSaldosEmEstoque());
        menuEstoque.add(new ItemMenuEstoqueHistoricoMovimentacoes());
        
        //Menu Sistema
        MenuSistema menuSistema = new MenuSistema();
        menuSistema.add(new MenuAparenciaSistema());
        menuSistema.add(new ItemMenuSistemaSobre());
        
        add(menuCadastros);
        add(menuEstoque);
        add(menuSistema);
        return menuCadastros;
        
    }

    @Override
    public JMenuBar montarMenu() {
        criaItensDoMenu();
        return this;
    }
    
}
