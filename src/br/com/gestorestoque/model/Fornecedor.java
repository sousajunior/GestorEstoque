package br.com.gestorestoque.model;
public class Fornecedor {

	private String nome,cnpj,cpf;

    public Fornecedor(String nome, String cnpj, String cpf) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.cpf = cpf;
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
        
        

}
