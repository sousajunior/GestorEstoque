
package br.com.gestorestoque.relatorio.excel;

/**
 *
 * @author DG
 */
public class AtributosRelatorioProdutos {
    
    private String produto;
    private String controladoPorLote;
    private String unidadeMedida;
    private String preco;
    private String qtdMinima;

    
    
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
     * @return the unidadeMedida
     */
    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    /**
     * @param unidadeMedida the unidadeMedida to set
     */
    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    /**
     * @return the controladoPorLote
     */
    public String getControladoPorLote() {
        return controladoPorLote;
    }

    /**
     * @param controladoPorLote the controladoPorLote to set
     */
    public void setControladoPorLote(String controladoPorLote) {
        this.controladoPorLote = controladoPorLote;
    }


    /**
     * @return the preco
     */
    public String getPreco() {
        return preco;
    }

    /**
     * @param preco the preco to set
     */
    public void setPreco(String preco) {
        this.preco = preco;
    }

    /**
     * @return the qtdMinima
     */
    public String getQtdMinima() {
        return qtdMinima;
    }

    /**
     * @param qtdMinima the qtdMinima to set
     */
    public void setQtdMinima(String qtdMinima) {
        this.qtdMinima = qtdMinima;
    }
            
    
    
}
