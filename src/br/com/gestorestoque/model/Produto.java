package br.com.gestorestoque.model;

public class Produto {

    private int codigo;

    private String nome;

    private boolean controladoPorLote;

    private double quantidadeMinima;

    private double preco;

    private UnidadeMedida unidadeMedida;

    public Produto(int codigo, String nome, boolean controladoPorLote, double quantidadeMinima, double preco, UnidadeMedida unidadeMedida) {
        this.codigo = codigo;
        this.controladoPorLote = controladoPorLote;
        this.nome = nome;
        this.preco = preco;
        this.quantidadeMinima = quantidadeMinima;
        this.unidadeMedida = unidadeMedida;
    }

    public Produto(String nome, boolean controladoPorLote, double quantidadeMinima, double preco, UnidadeMedida unidadeMedida) {

        this.controladoPorLote = controladoPorLote;
        this.nome = nome;
        this.preco = preco;
        this.quantidadeMinima = quantidadeMinima;
        this.unidadeMedida = unidadeMedida;
    }

    public Produto() {
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return the controladoPorLote
     */
    public boolean isControladoPorLote() {
        return controladoPorLote;
    }

    /**
     * @return the quantidadeMinima
     */
    public double getQuantidadeMinima() {
        return quantidadeMinima;
    }

    /**
     * @return the preco
     */
    public double getPreco() {
        return preco;
    }

    /**
     * @return the unidadeMedida
     */
    public UnidadeMedida getUnidadeMedida() {
        return unidadeMedida;
    }

    /**
     * @param unidadeMedida the unidadeMedida to set
     */
    public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }


    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @param controladoPorLote the controladoPorLote to set
     */
    public void setControladoPorLote(boolean controladoPorLote) {
        this.controladoPorLote = controladoPorLote;
    }

    /**
     * @param quantidadeMinima the quantidadeMinima to set
     */
    public void setQuantidadeMinima(double quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    /**
     * @param preco the preco to set
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }

    
    
    
}
