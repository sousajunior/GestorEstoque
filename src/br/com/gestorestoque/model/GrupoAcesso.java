package br.com.gestorestoque.model;

public class GrupoAcesso {

    private Integer id;

    private String nomeGrupo;

    public GrupoAcesso(Integer id, String nomeGrupo) {
        this.id = id;
        this.nomeGrupo = nomeGrupo;
    }


   
    
    public GrupoAcesso( String nomeGrupo) {
        
        this.nomeGrupo = nomeGrupo;
        
    }

    public GrupoAcesso() {

    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return the nomeGrupo
     */
    public String getNomeGrupo() {
        return nomeGrupo;
    }

    
}
