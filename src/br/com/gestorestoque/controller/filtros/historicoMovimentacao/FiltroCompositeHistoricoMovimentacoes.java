/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestorestoque.controller.filtros.historicoMovimentacao;

import br.com.gestorestoque.controller.filtros.Filtro;
import br.com.gestorestoque.model.Movimentacao;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DG
 */
public class FiltroCompositeHistoricoMovimentacoes implements Filtro<Movimentacao>{

    List<Movimentacao> produtos = new ArrayList<>();
    List<Filtro> filtros = new ArrayList<>();

    public FiltroCompositeHistoricoMovimentacoes(List<Filtro> filtros) {
        this.filtros = filtros;
    }

    public FiltroCompositeHistoricoMovimentacoes() {
        
    }
    

    @Override
    public List<Movimentacao> filtrar(List<Movimentacao> listaProdutosArmazenados) {
        
        List<Movimentacao> lista = listaProdutosArmazenados;
        List<Movimentacao> lista2 = new ArrayList<>();        
        
        for (Filtro filtro : filtros) {
            
            lista2 = filtro.filtrar(lista);
            lista = lista2;
            
        }
    
        return lista;
    }
    
    /**
     * Adiciona um filtro a lista de filtros da classe FiltroCompositeProdutoArmazenado.
     * @param filtro (Um filtro que implemente a interface filtro).
     */
    public void add(Filtro filtro){
        filtros.add(filtro);
    }
}
