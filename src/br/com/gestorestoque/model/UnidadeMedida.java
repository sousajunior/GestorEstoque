package br.com.gestorestoque.model;

public class UnidadeMedida {

    private Integer codigo;
    private String nome;
    private String abreviacao;

    public UnidadeMedida(String nome, String abreviacao) {
        this.nome = nome;
        this.abreviacao = abreviacao;
    }

    public UnidadeMedida(Integer codigo,String nome, String abreviacao) {
        this.codigo = codigo;
        this.nome = nome;
        this.abreviacao = abreviacao;
    }

    public UnidadeMedida() {
    }

    
    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the abreviacao
     */
    public String getAbreviacao() {
        return abreviacao;
    }

    /**
     * @param abreviacao the abreviacao to set
     */
    public void setAbreviacao(String abreviacao) {
        this.abreviacao = abreviacao;
    }

    /**
     * @return the codigo
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
    
    
    

}
