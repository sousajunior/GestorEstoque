/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestorestoque.controller;

import br.com.gestorestoque.model.ProdutoArmazenado;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DG
 */
public class FiltroComposite implements Filtro<ProdutoArmazenado>{

    List<ProdutoArmazenado> produtos = new ArrayList<>();
    List<Filtro> filtros = new ArrayList<>();

    public FiltroComposite(List<Filtro> filtros) {
        this.filtros = filtros;
    }

    public FiltroComposite() {
        
    }
    
    
    
    
    @Override
    public List<ProdutoArmazenado> filtrar(List<ProdutoArmazenado> listaProdutosArmazenados) {
        
        List<ProdutoArmazenado> lista = listaProdutosArmazenados;
        List<ProdutoArmazenado> lista2 = new ArrayList<>();        
        
        for (Filtro filtro : filtros) {
            
            lista2 = filtro.filtrar(lista);
            lista = lista2;
            
        }
    
        return lista;
    }
    
    /**
     * Adiciona um filtro a lista de filtros da classe FiltroComposite.
     * @param filtro (Um filtro que implemente a interface filtro).
     */
    public void add(Filtro filtro){
        filtros.add(filtro);
    }
}
