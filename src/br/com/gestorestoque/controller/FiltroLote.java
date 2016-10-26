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
public class FiltroLote implements Filtro<ProdutoArmazenado> {

    List<ProdutoArmazenado> produtos = new ArrayList<>();

    public FiltroLote(List<ProdutoArmazenado> produtos) {
        this.produtos = produtos;
    }
    
    @Override
    public List<ProdutoArmazenado> filtrar(List<ProdutoArmazenado> lista,String lote, int selectedIndex) {

        List<ProdutoArmazenado> produtosArmazenadosPesquisa = lista;
        if (validarIndex(selectedIndex)) {
            if (selectedIndex == 1) {
                for (ProdutoArmazenado produto : produtos) {
                    //verifica se o produto é controlado por lote
                    if (produto.getProduto().isControladoPorLote()) {
                        //verifica se o lote da posição atual contem a expressão que o usuário digitou
                        if (produto.getLote().toUpperCase().contains(lote.toUpperCase())) {
                            //adiciona na lista quando encontra um lote que contenha o que o usuário digitou
                            produtosArmazenadosPesquisa.add(produto);
                        }
                    }
                }
            } else if (selectedIndex == 2) {
                for (ProdutoArmazenado produto : produtos) {
                    //verifica se o produto é controlado por lote
                    if (produto.getProduto().isControladoPorLote()) {
                        //verifica se o lote da posição atual contem a expressão que o usuário digitou
                        if (produto.getLote().toUpperCase().equalsIgnoreCase(lote.toUpperCase())) {
                            //adiciona na lista quando encontra um lote que contenha o que o usuário digitou
                            produtosArmazenadosPesquisa.add(produto);
                        }
                    }
                }
            }
            return produtosArmazenadosPesquisa;
        }
        return null;
    }

    private boolean validarIndex(int index) {
        return index > 0 ? true : false;
    }
    
}
