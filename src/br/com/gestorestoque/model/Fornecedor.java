package br.com.gestorestoque.model;

public class Fornecedor {

    private int idFornecedor;
    private String nome, cnpj="", cpf="";

    public Fornecedor() {
    }

    public Fornecedor(int idFornecedor, String nome, String cpf, String cnpj) {
        this.idFornecedor = idFornecedor;
        this.nome = nome;
        this.cnpj = cnpj;
        this.cpf = cpf;
    }
    
    public Fornecedor(String nome, String cpf, String cnpj) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.cpf = cpf;
    }

    public int getIdFornecedor() {
        return idFornecedor;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return the cnpj
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param idFornecedor the idFornecedor to set
     */
    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @param cnpj the cnpj to set
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    

}
