

package br.com.gestorestoque.view.menu.fabrica;

//import view.menus.MenuGUI.MenuGUIAtendente;

import br.com.gestorestoque.view.menu.GUI.MenuGuiAdmin;
import br.com.gestorestoque.view.menu.GUI.MenuGuiAlmoxarifado;



/**
 *
 * @author DG
 */
public class CriadorFabricaMenu {


    public static AbstractFabricaMenus getMenuComponent(TipoMenu tipoMenu) {
        switch (tipoMenu) {
            case ADMIN:
                return new MenuGuiAdmin();
            case ALMOXARIFADO:
                return new MenuGuiAlmoxarifado();
            default:
                return null;
        }
    }    
}
