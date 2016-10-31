
package br.com.gestorestoque.relatorio.excel;

/**
 *
 * @author DG
 */
public class AtributosRelatorioMovimentacaoEstoque {
    
    private String produto;
    private String lote;
    private String quantidade;
    private String data;
    private String notaFiscal;
    private String tipoMovimentacao;
    private String armazem;

    /**
     * @return the produto
     */
    public String getProduto() {
        return produto;
    }

    /**
     * @param produto the produto to set
     */
    public void setProduto(String produto) {
        this.produto = produto;
    }

    /**
     * @return the lote
     */
    public String getLote() {
        return lote;
    }

    /**
     * @param lote the lote to set
     */
    public void setLote(String lote) {
        this.lote = lote;
    }

    /**
     * @return the quantidade
     */
    public String getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * @return the notaFiscal
     */
    public String getNotaFiscal() {
        return notaFiscal;
    }

    /**
     * @param notaFiscal the notaFiscal to set
     */
    public void setNotaFiscal(String notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    /**
     * @return the tipoMovimentacao
     */
    public String getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    /**
     * @param tipoMovimentacao the tipoMovimentacao to set
     */
    public void setTipoMovimentacao(String tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }
/**
     * @return the armazem
     */
    public String getArmazem() {
        return armazem;
    }

    /**
     * @param armazem the armazem to set
     */
    public void setArmazem(String armazem) {
        this.armazem = armazem;
    }

    
}
