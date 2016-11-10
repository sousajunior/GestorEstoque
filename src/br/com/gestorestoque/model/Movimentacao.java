package br.com.gestorestoque.model;

import java.util.Date;

/**
 *
 * @author Matheus
 */
public class Movimentacao {
    private  int id;
    private  String lote;
    private  double qtd;
    private  int notaFiscal;
    private  char tipoMovimentacao;
    private  Date data;
    private  ProdutoArmazenado idProdutoArmazenado;
    private  Armazem idArmazem;

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
    
    public Movimentacao( String lote, double qtd, int notaFiscal, String tipoMovimentacao, Date data, ProdutoArmazenado idProdutoArmazenado, Armazem idArmazem) {
        
        this.lote = lote;
        this.qtd = qtd;
        this.notaFiscal = notaFiscal;
        this.tipoMovimentacao = tipoMovimentacao.charAt(0);
        this.data = data;
        this.idProdutoArmazenado = idProdutoArmazenado;
        this.idArmazem = idArmazem;
    }

    public Movimentacao() {
        
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
