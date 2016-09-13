/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestorestoque.model;

import java.util.Date;

/**
 *
 * @author Matheus
 */
public class Movimentacao {
    private final int id;
    private final String lote;
    private final double qtd;
    private final int notaFiscal;
    private final char tipoMovimentacao;
    private final Date data;
    private final int idProdutoArmazenado;
    private final int idArmazem;

    public Movimentacao(int id, String lote, double qtd, int notaFiscal, char tipoMovimentacao, Date data, int idProdutoArmazenado, int idArmazem) {
        this.id = id;
        this.lote = lote;
        this.qtd = qtd;
        this.notaFiscal = notaFiscal;
        this.tipoMovimentacao = tipoMovimentacao;
        this.data = data;
        this.idProdutoArmazenado = idProdutoArmazenado;
        this.idArmazem = idArmazem;
    }

}
