
package br.com.gestorestoque.controller;

import br.com.gestorestoque.banco.CRUD;
import br.com.gestorestoque.model.GrupoAcesso;
import br.com.gestorestoque.model.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DG
 */

public class ControladorGrupoAcesso implements Controlador<GrupoAcesso> {
   

    @Override
    public void inserir(GrupoAcesso grupoAcesso) throws SQLException {
        CRUD.insert("grupo", "null,'"+grupoAcesso.getNomeGrupo()+"'");   
    }

    @Override
    public void atualizarPorCodigo(GrupoAcesso grupoAcesso) throws SQLException {
        CRUD.update("grupo", "nome_grupo = '" + grupoAcesso.getNomeGrupo()+ "',",
                "id", String.valueOf(grupoAcesso.getId()));
    }

    @Override
    public List<GrupoAcesso> selecionarTodos() throws SQLException {
        List<GrupoAcesso> grupoAcessoes = new ArrayList<>();
        ResultSet rs = CRUD.select("grupo");
        while (rs.next()) {
            GrupoAcesso grupoAcesso = new GrupoAcesso(rs.getInt("id"), 
                                                      rs.getString("nome_grupo")
            );
            grupoAcessoes.add(grupoAcesso);
        }
       return grupoAcessoes;
    }
    
    
    @Override
    public GrupoAcesso selecionarPorCodigo(int id) throws SQLException {
        GrupoAcesso grupoAcesso = new GrupoAcesso();
        ResultSet rs = CRUD.select("grupo","id",id);
        while (rs.next()) {
            grupoAcesso = new GrupoAcesso(rs.getInt("id"), rs.getString("nome_grupo"));
            
        }
       return grupoAcesso;
    }

    
    @Override
    public void deletar(GrupoAcesso grupoAcesso) throws SQLException {
         CRUD.delete("grupo","id",Integer.toString(grupoAcesso.getId()));
    }
}
