package br.com.gestorestoque.model;
public class ProdutoArmazenado {

	private String Lote;

	private double Quantidade;

	private int NotaFiscal;

	private Produto produto;
        
        private Fornecedor fornecedor;

    public ProdutoArmazenado(String Lote, double Quantidade, int NotaFiscal, Produto produto,Fornecedor fornecedor) {
        this.Lote = Lote;
        this.Quantidade = Quantidade;
        this.NotaFiscal = NotaFiscal;
        this.produto = produto;
        this.fornecedor = fornecedor;
    }

    /**
     * @return the Lote
     */
    public String getLote() {
        return Lote;
    }

    /**
     * @param Lote the Lote to set
     */
    public void setLote(String Lote) {
        this.Lote = Lote;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
    

    /**
     * @return the Quantidade
     */
    public double getQuantidade() {
        return Quantidade;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }
    
    
    /**
     * @param Quantidade the Quantidade to set
     */
    public void setQuantidade(double Quantidade) {
        this.Quantidade = Quantidade;
    }

    /**
     * @return the NotaFiscal
     */
    public int getNotaFiscal() {
        return NotaFiscal;
    }

    /**
     * @param NotaFiscal the NotaFiscal to set
     */
    public void setNotaFiscal(int NotaFiscal) {
        this.NotaFiscal = NotaFiscal;
    }

    /**
     * @return the produto
     */
    public Produto getProduto() {
        return produto;
    }

    /**
     * @param produto the produto to set
     */
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    public void aumentarQuantidade(ProdutoArmazenado pa)
    {
        this.Quantidade += pa.getQuantidade();
    }
     public void diminuirQuantidade(ProdutoArmazenado pa)
    {
        this.Quantidade -= pa.getQuantidade();
    }
        
        
}
