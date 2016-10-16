
package br.com.gestorestoque.controller;

import br.com.gestorestoque.banco.CRUD;
import br.com.gestorestoque.model.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DG
 */

public class ControladorUsuario implements Controlador<Usuario> {
   

    @Override
    public void inserir(Usuario usuario) throws SQLException {
        CRUD.insert("usuario", "null,'"+usuario.getNomeCompleto()+"','"+
                usuario.getNomeUsuario()+"','"+usuario.getSenha()+"'");   
    }

    @Override
    public void atualizarPorCodigo(Usuario usuario) throws SQLException {
        CRUD.update("usuario", "nome_completo = '" + usuario.getNomeCompleto() + "'," +
                "nome_usuario = '" + usuario.getNomeUsuario() + "'," +
                "senha = '" + usuario.getSenha() + "'",
                "id", String.valueOf(usuario.getId()));
    }

    @Override
    public List<Usuario> selecionarTodos() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        ResultSet rs = CRUD.select("usuario");
        while (rs.next()) {
            Usuario usuario = new Usuario(
                    rs.getInt("id"), 
                    rs.getString("nome_completo"),
                    rs.getString("nome_usuario"),
                    rs.getString("senha")
            );
            usuarios.add(usuario);
        }
       return usuarios;
    }

    @Override
    public Usuario selecionarPorCodigo(int id) throws SQLException {
        Usuario usuario = new Usuario();
        ResultSet rs = CRUD.select("usuario","id",id);
        while (rs.next()) {
            usuario = new Usuario(rs.getInt("id"), rs.getString("nome_completo"),rs.getString("nome_usuario"),rs.getString("senha"));
            
        }
       return usuario;
    }
    
    public Usuario selecionarPorUsuarioSenha(String nomeUsuario, String senha) throws SQLException {
        
        Usuario usuario = new Usuario();
        ResultSet rs = CRUD.select("usuario","where nome_usuario = '"+nomeUsuario+"' and senha = '"+senha+"'");
        while (rs.next()) {
            usuario = new Usuario(rs.getInt("id"), rs.getString("nome_completo"),rs.getString("nome_usuario"),rs.getString("senha"));
            
        }
       return usuario;
    }

    @Override
    public void deletar(Usuario usuario) throws SQLException {
         CRUD.delete("usuario","id",Integer.toString(usuario.getId()));
    }
}
