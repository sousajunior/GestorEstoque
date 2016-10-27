/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestorestoque.controller;

import br.com.gestorestoque.model.ProdutoArmazenado;
import br.com.gestorestoque.util.FiltroUtil;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 5927161
 */
public class FiltroPreco implements Filtro<ProdutoArmazenado>{
    final private int selectedIndex;
    final private String preco;

    public FiltroPreco(int index, String preco) {
        this.selectedIndex = index;
        this.preco = preco;
    }
    
    
    
    @Override
    public List<ProdutoArmazenado> filtrar(List<ProdutoArmazenado> lista) {
        List<ProdutoArmazenado> produtosArmazenadosPesquisa = new ArrayList<>();
        if (FiltroUtil.validarIndex(selectedIndex)) {
            if (selectedIndex == 1) {
                for (ProdutoArmazenado produto : lista) {
                    if (produto.getProduto().getPreco() == Double.parseDouble(preco)) {
                        produtosArmazenadosPesquisa.add(produto);
                    }
                }
            } else if (selectedIndex == 2) {
                for (ProdutoArmazenado produto : lista) {
                    if (produto.getProduto().getPreco() > Double.parseDouble(preco)) {
                        produtosArmazenadosPesquisa.add(produto);
                    }
                }
            } else if (selectedIndex == 3) {
                for (ProdutoArmazenado produto : lista) {
                    if (produto.getProduto().getPreco() >= Double.parseDouble(preco)) {
                        produtosArmazenadosPesquisa.add(produto);
                    }
                }
            } else if (selectedIndex == 4) {
                for (ProdutoArmazenado produto : lista) {
                    if (produto.getProduto().getPreco() < Double.parseDouble(preco)) {
                        produtosArmazenadosPesquisa.add(produto);
                    }
                }
            } else if (selectedIndex == 5) {
                for (ProdutoArmazenado produto : lista) {
                    if (produto.getProduto().getPreco() <= Double.parseDouble(preco)) {
                        produtosArmazenadosPesquisa.add(produto);
                    }
                }
            }
            return produtosArmazenadosPesquisa;
        }
        return null;
    }

}
