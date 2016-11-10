package br.com.gestorestoque.view.menu.GUI;

import br.com.gestorestoque.view.menu.admin.BarraDeMenuAdmin;
import br.com.gestorestoque.view.menu.fabrica.AbstractFabricaMenus;
import br.com.gestorestoque.view.menu.fabrica.BarraMenu;


/**
 *
 * @author DG
 */
public class MenuGuiAdmin implements AbstractFabricaMenus{

    @Override
    public BarraMenu criaBarraMenu() {
        
        return new BarraDeMenuAdmin();
        
    }

//    @Override
//    public PainelMenu criaPainelMenu() {
//        return new PanelMenuAdmin(new Controlador(new FormPrincipal(), new Loja()));
//    }

   
}
