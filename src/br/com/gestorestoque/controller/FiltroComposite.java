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

    @Override
    public List<ProdutoArmazenado> filtrar(List<ProdutoArmazenado> lista, String textoPesquisa, int indiceComboBox) {
            return null;
    }
    
}
