package br.com.gestorestoque.model;

import java.util.List;

public class UsuarioGrupo {

    private static UsuarioGrupo usuarioGrupo;

    private Usuario idUsuario;

    private List<GrupoAcesso> idGrupos;

    public static synchronized UsuarioGrupo getInstance() {

        if (usuarioGrupo == null) {
            usuarioGrupo = new UsuarioGrupo();
        }
        return usuarioGrupo;
    }

    public UsuarioGrupo(Usuario idUsuario, List<GrupoAcesso> idGrupo) {
        this.idUsuario = idUsuario;
        this.idGrupos = idGrupo;
    }

    public UsuarioGrupo() {
    }

    /**
     * @return the idUsuario
     */
    public Usuario getIdUsuario() {
        return idUsuario;
    }

    /**
     * @return the idGrupos
     */
    public List<GrupoAcesso> getIdGrupos() {
        return idGrupos;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @param idGrupos the idGrupos to set
     */
    public void setIdGrupos(List<GrupoAcesso> idGrupos) {
        this.idGrupos = idGrupos;
    }

    
    
}
