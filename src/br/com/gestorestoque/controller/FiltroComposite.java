/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestorestoque.controller;

import br.com.gestorestoque.model.ProdutoArmazenado;
import java.util.List;

/**
 *
 * @author DG
 */
public class FiltroComposite implements Filtro<ProdutoArmazenado>{

    List<Filtro> filtros ;

    public FiltroComposite(List<Filtro> filtros) {
        this.filtros = filtros;
    }
    
    
    
    @Override
    public List<ProdutoArmazenado> filtrar() {
        for (Filtro filtro : filtros) {
            filtro.filtrar();
        }
    
        return null;
    }
    
}
