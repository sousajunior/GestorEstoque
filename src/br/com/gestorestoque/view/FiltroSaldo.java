/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestorestoque.view;

import br.com.gestorestoque.controller.Filtro;
import br.com.gestorestoque.model.ProdutoArmazenado;
import br.com.gestorestoque.util.FiltroUtil;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 5981468
 */
public class FiltroSaldo implements Filtro<ProdutoArmazenado>  {
    
        String saldo;
    int selectedIndex;

    public FiltroSaldo( String saldo, int selectedIndex) {
        
        this.saldo = saldo;
        this.selectedIndex = selectedIndex;
    }

    @Override
    public List<ProdutoArmazenado> filtrar(List<ProdutoArmazenado> lista) {

         List<ProdutoArmazenado> produtosArmazenadosPesquisa = new ArrayList<>();
        if (FiltroUtil.validarIndex(selectedIndex)) {
            if (selectedIndex == 1) {
                for (ProdutoArmazenado produto : lista) {
                    if (produto.getQuantidade() == Double.parseDouble(saldo)) {
                        produtosArmazenadosPesquisa.add(produto);
                    }
                }
            } else if (selectedIndex == 2) {
                for (ProdutoArmazenado produto : lista) {
                    if (produto.getQuantidade() > Double.parseDouble(saldo)) {
                        produtosArmazenadosPesquisa.add(produto);
                    }
                }
            } else if (selectedIndex == 3) {
                for (ProdutoArmazenado produto : lista) {
                    if (produto.getQuantidade() >= Double.parseDouble(saldo)) {
                        produtosArmazenadosPesquisa.add(produto);
                    }
                }
            } else if (selectedIndex == 4) {
                for (ProdutoArmazenado produto : lista) {
                    if (produto.getQuantidade() < Double.parseDouble(saldo)) {
                        produtosArmazenadosPesquisa.add(produto);
                    }
                }
            } else if (selectedIndex == 5) {
                for (ProdutoArmazenado produto : lista) {
                    if (produto.getQuantidade() <= Double.parseDouble(saldo)) {
                        produtosArmazenadosPesquisa.add(produto);
                    }
                }
            }
            return produtosArmazenadosPesquisa;
        }
        return null;
    }

    
}
