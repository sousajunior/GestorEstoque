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

    
    
    
}
