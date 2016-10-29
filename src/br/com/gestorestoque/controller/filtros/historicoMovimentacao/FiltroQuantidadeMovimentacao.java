/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestorestoque.controller.filtros.historicoMovimentacao;

import br.com.gestorestoque.controller.filtros.Filtro;
import br.com.gestorestoque.model.Movimentacao;
import br.com.gestorestoque.model.ProdutoArmazenado;
import br.com.gestorestoque.util.FiltroUtil;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 5927161
 */
public class FiltroQuantidadeMovimentacao implements Filtro<Movimentacao> {

    final private int selectedIndex;
    final private String quantidade;

    public FiltroQuantidadeMovimentacao(int index, String quantidade) {
        this.selectedIndex = index;
        this.quantidade = quantidade;
    }

    @Override
    public List<Movimentacao> filtrar(List<Movimentacao> lista) {
        List<Movimentacao> movimentacaoPesquisa = new ArrayList<>();
        if (FiltroUtil.validarIndex(selectedIndex)) {

            switch (selectedIndex) {
                case 1:
                    for (Movimentacao movimentacao : lista) {
                        if (movimentacao.getQtd()== Double.parseDouble(quantidade)) {
                            movimentacaoPesquisa.add(movimentacao);
                        }
                    }
                    break;
                case 2:
                    for (Movimentacao movimentacao : lista) {
                        if (movimentacao.getQtd() > Double.parseDouble(quantidade)) {
                            movimentacaoPesquisa.add(movimentacao);
                        }
                    }
                    break;
                case 3:
                    for (Movimentacao movimentacao : lista) {
                        if (movimentacao.getQtd() >= Double.parseDouble(quantidade)) {
                            movimentacaoPesquisa.add(movimentacao);
                        }
                    }
                    break;
                case 4:
                    for (Movimentacao movimentacao : lista) {
                        if (movimentacao.getQtd() < Double.parseDouble(quantidade)) {
                            movimentacaoPesquisa.add(movimentacao);
                        }
                    }
                    break;
                case 5:
                    for (Movimentacao produto : lista) {
                        if (produto.getQtd() <= Double.parseDouble(quantidade)) {
                            movimentacaoPesquisa.add(produto);
                        }
                    }
                    break;
                default:
                    break;
            }
            return movimentacaoPesquisa;
        }
        return null;
    }

}
