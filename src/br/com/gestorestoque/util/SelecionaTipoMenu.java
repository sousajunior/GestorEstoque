/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestorestoque.util;

import br.com.gestorestoque.model.GrupoAcesso;
import br.com.gestorestoque.model.UsuarioGrupo;
import br.com.gestorestoque.view.menu.fabrica.TipoMenu;

/**
 *
 * @author DG
 */
public class SelecionaTipoMenu {
    
    public static TipoMenu selecionarTipoMenu(){
        
       UsuarioGrupo usuarioGrupo = UsuarioGrupo.getInstance();
        
        for (GrupoAcesso grupos : usuarioGrupo.getIdGrupos()) {
            
            switch(grupos.getNomeGrupo()){
                case "ADMINISTRADORES" :
                    return TipoMenu.ADMIN;
                case "ALMOXARIFADO" :
                    return TipoMenu.ALMOXARIFADO;
                default:   
                    return TipoMenu.ALMOXARIFADO;
            }
        }
        return TipoMenu.ALMOXARIFADO;
    }
    
}
