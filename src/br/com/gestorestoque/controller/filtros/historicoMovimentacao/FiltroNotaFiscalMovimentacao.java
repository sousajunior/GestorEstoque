/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestorestoque.controller.filtros.historicoMovimentacao;

import br.com.gestorestoque.controller.filtros.Filtro;
import br.com.gestorestoque.model.Movimentacao;
import br.com.gestorestoque.util.FiltroUtil;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 5927161
 */
public class FiltroNotaFiscalMovimentacao implements Filtro<Movimentacao>{

    private int selectedIndex;
    private String notaFiscal;

    public FiltroNotaFiscalMovimentacao(int selectedIndex, String notaFiscal) {
        this.selectedIndex = selectedIndex;
        this.notaFiscal = notaFiscal;
    }
    
    
    @Override
    public List<Movimentacao> filtrar(List<Movimentacao> lista) {
        List<Movimentacao> movimentacaoPesquisa = new ArrayList<>();
        if (FiltroUtil.validarIndex(selectedIndex)) {
            if (selectedIndex == 1) {
                for (Movimentacao movimentacao : lista) {
                    String nf = "" + movimentacao.getNotaFiscal();
                    if (nf.contains(notaFiscal)) {
                        movimentacaoPesquisa.add(movimentacao);
                    }
                }
            } else if (selectedIndex == 2) {
                for (Movimentacao movimentacao : lista) {
                    String nf = "" + movimentacao.getNotaFiscal();
                    if (nf.equalsIgnoreCase(notaFiscal)) {
                        movimentacaoPesquisa.add(movimentacao);
                    }
                }
            }
            return movimentacaoPesquisa;
        }
        return null;
    }
    
}   
