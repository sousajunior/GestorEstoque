package br.com.gestorestoque.view.menu.GUI;

import br.com.gestorestoque.view.menu.almoxarifado.BarraDeMenuAlmoxarifado;
import br.com.gestorestoque.view.menu.fabrica.AbstractFabricaMenus;
import br.com.gestorestoque.view.menu.fabrica.BarraMenu;


/**
 *
 * @author DG
 */
public class MenuGuiAlmoxarifado implements AbstractFabricaMenus{

    @Override
    public BarraMenu criaBarraMenu() {
        
        return new BarraDeMenuAlmoxarifado();
        
    }

//    @Override
//    public PainelMenu criaPainelMenu() {
//        return new PanelMenuAdmin(new Controlador(new FormPrincipal(), new Loja()));
//    }

   
}
