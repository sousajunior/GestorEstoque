
package br.com.gestorestoque.controller;

import br.com.gestorestoque.banco.CRUD;
import br.com.gestorestoque.model.GrupoAcesso;
import br.com.gestorestoque.model.Usuario;
import br.com.gestorestoque.model.UsuarioGrupo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DG
 */
public class ControladorGrupoUsuario implements Controlador<UsuarioGrupo> {

    @Override
    /**
     * Método não implementado
     */
    public void inserir(UsuarioGrupo usuarioGrupo) throws SQLException {

    }

    public void inserir(Usuario usuario, GrupoAcesso grupo) throws SQLException {
        CRUD.insert("usuario_grupo", usuario.getId() + "," + grupo.getId());
    }

    @Override
    /**
     * Método não implementado
     */
    public void atualizarPorCodigo(UsuarioGrupo usuarioGrupo) throws SQLException {
//        CRUD.update("usuario_grupo", " = '" + usuarioGrupo.getNome() + "'," +
//                "cnpj = '" + usuarioGrupo.getCnpj() + "'," +
//                "cpf = '" + usuarioGrupo.getCpf() + "'",
//                "idusuarioGrupo", String.valueOf(usuarioGrupo.getIdUsuarioGrupo()));
    }

    @Override
    public List<UsuarioGrupo> selecionarTodos() throws SQLException {

        ControladorUsuario controladorUsuario = new ControladorUsuario();
        ControladorGrupoAcesso controladorGrupoAcesso = new ControladorGrupoAcesso();
        List<GrupoAcesso> listaGruposAcesso = new ArrayList<>();

        List<UsuarioGrupo> usuarioGrupos = new ArrayList<>();
        ResultSet rs = CRUD.select("usuario_grupo");

        while (rs.next()) {

            Usuario usuario = controladorUsuario.selecionarPorCodigo(rs.getInt("id_usuario"));

            rs.first();
            while (rs.next()) {

                if (usuario.getId() == controladorUsuario.selecionarPorCodigo(rs.getInt("id_usuario")).getId()) {
                    listaGruposAcesso.add(controladorGrupoAcesso.selecionarPorCodigo(rs.getInt("id_grupo")));
                }
            }

            UsuarioGrupo usuarioGrupo = new UsuarioGrupo(usuario, listaGruposAcesso);

            usuarioGrupos.add(usuarioGrupo);
            listaGruposAcesso = new ArrayList<>();
        }
        return usuarioGrupos;
    }

    @Override
    /**
     * Seleciona pelo codigo do usuário
     */
    public UsuarioGrupo selecionarPorCodigo(int id) throws SQLException {

        ControladorUsuario controladorUsuario = new ControladorUsuario();
        ControladorGrupoAcesso controladorGrupoAcesso = new ControladorGrupoAcesso();
        List<GrupoAcesso> listaGruposAcesso = new ArrayList<>();

        UsuarioGrupo usuarioGrupo = UsuarioGrupo.getInstance();
        ResultSet rs = CRUD.select("usuario_grupo", "id_usuario", id);
        Usuario usuario = new Usuario();

        while (rs.next()) {

            usuario = controladorUsuario.selecionarPorCodigo(rs.getInt("id_usuario"));

            rs.beforeFirst();
            while (rs.next()) {
                if (usuario.getId() == controladorUsuario.selecionarPorCodigo(rs.getInt("id_usuario")).getId()) {
                    listaGruposAcesso.add(controladorGrupoAcesso.selecionarPorCodigo(rs.getInt("id_grupo")));
                }
            }

        }

        usuarioGrupo.setIdUsuario(usuario);
        usuarioGrupo.setIdGrupos(listaGruposAcesso);
        
        return usuarioGrupo;
    }

    @Override
    /**
     * Método não implementado
     */
    public void deletar(UsuarioGrupo usuarioGrupo) throws SQLException {
        // CRUD.delete("usuario_grupo", "id_usuario", Integer.toString(usuarioGrupo.getIdUsuarioGrupo()));
    }
}
