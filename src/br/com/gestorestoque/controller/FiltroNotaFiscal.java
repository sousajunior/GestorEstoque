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
public class FiltroNotaFiscal implements Filtro<ProdutoArmazenado>{

    private int selectedIndex;
    private String notaFiscal;

    public FiltroNotaFiscal(int selectedIndex, String notaFiscal) {
        this.selectedIndex = selectedIndex;
        this.notaFiscal = notaFiscal;
    }
   
    
    
    @Override
    public List<ProdutoArmazenado> filtrar(List<ProdutoArmazenado> lista) {
        List<ProdutoArmazenado> produtosArmazenadosPesquisa = new ArrayList<>();
        if (FiltroUtil.validarIndex(selectedIndex)) {
            if (selectedIndex == 1) {
                for (ProdutoArmazenado produto : lista) {
                    String nf = "" + produto.getNotaFiscal();
                    if (nf.contains(notaFiscal)) {
                        produtosArmazenadosPesquisa.add(produto);
                    }
                }
            } else if (selectedIndex == 2) {
                for (ProdutoArmazenado produto : lista) {
                    String nf = "" + produto.getNotaFiscal();
                    if (nf.equalsIgnoreCase(notaFiscal)) {
                        produtosArmazenadosPesquisa.add(produto);
                    }
                }
            }
            return produtosArmazenadosPesquisa;
        }
        return null;
    }
    
}   
