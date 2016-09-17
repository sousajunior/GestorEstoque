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
    private final ProdutoArmazenado idProdutoArmazenado;
    private final Armazem idArmazem;

    public Movimentacao(int id, String lote, double qtd, int notaFiscal, char tipoMovimentacao, Date data, ProdutoArmazenado idProdutoArmazenado, Armazem idArmazem) {
        this.id = id;
        this.lote = lote;
        this.qtd = qtd;
        this.notaFiscal = notaFiscal;
        this.tipoMovimentacao = tipoMovimentacao;
        this.data = data;
        this.idProdutoArmazenado = idProdutoArmazenado;
        this.idArmazem = idArmazem;
    }
    
    public Movimentacao(int id, String lote, double qtd, int notaFiscal, String tipoMovimentacao, Date data, ProdutoArmazenado idProdutoArmazenado, Armazem idArmazem) {
        this.id = id;
        this.lote = lote;
        this.qtd = qtd;
        this.notaFiscal = notaFiscal;
        this.tipoMovimentacao = tipoMovimentacao.charAt(0);
        this.data = data;
        this.idProdutoArmazenado = idProdutoArmazenado;
        this.idArmazem = idArmazem;
    }

    public Movimentacao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the lote
     */
    public String getLote() {
        return lote;
    }

    /**
     * @return the qtd
     */
    public double getQtd() {
        return qtd;
    }

    /**
     * @return the notaFiscal
     */
    public int getNotaFiscal() {
        return notaFiscal;
    }

    /**
     * @return the tipoMovimentacao
     */
    public char getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    /**
     * @return the data
     */
    public Date getData() {
        return data;
    }

    /**
     * @return the idProdutoArmazenado
     */
    public ProdutoArmazenado getIdProdutoArmazenado() {
        return idProdutoArmazenado;
    }

    /**
     * @return the idArmazem
     */
    public Armazem getIdArmazem() {
        return idArmazem;
    }

    
    
    
    
}
