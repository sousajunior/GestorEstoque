package br.com.gestorestoque.model;

public class Usuario implements Comparable<Usuario>{

    private Integer id;

    private String nomeCompleto;

    private String nomeUsuario;
    
    private String senha;

   
    public Usuario(Integer id, String nomeCompleto, String nomeUsuario, String senha) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
    }

    
    public Usuario( String nomeUsuario) {
        
        this.nomeUsuario = nomeUsuario;
        
    }

    public Usuario() {

    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return the nomeCompleto
     */
    public String getNomeCompleto() {
        return nomeCompleto;
    }

    /**
     * @return the nomeUsuario
     */
    public String getNomeUsuario() {
        return nomeUsuario;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }


    @Override
    public int compareTo(Usuario u) {
        return this.id.compareTo(u.getId());
    }
    
}
