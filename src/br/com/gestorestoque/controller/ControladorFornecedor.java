/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestorestoque.controller;

import br.com.gestorestoque.banco.CRUD;
import br.com.gestorestoque.model.Fornecedor;
import br.com.gestorestoque.model.Produto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matheus
 */

public class ControladorFornecedor implements Controlador<Fornecedor> {
   

    @Override
    public void inserir(Fornecedor fornecedor) throws SQLException {
        CRUD.insert("fornecedor", "null,'"+fornecedor.getNome()+"','"+
                fornecedor.getCpf()+"','"+fornecedor.getCnpj()+"'");   
    }

    @Override
    public void atualizarPorCodigo(Fornecedor fornecedor) throws SQLException {
        CRUD.update("fornecedor", "nome = '" + fornecedor.getNome() + "'," +
                "cnpj = '" + fornecedor.getCnpj() + "'," +
                "cpf = '" + fornecedor.getCpf() + "'",
                "idfornecedor", String.valueOf(fornecedor.getIdFornecedor()));
    }

    @Override
    public List<Fornecedor> selecionarTodos() throws SQLException {
        List<Fornecedor> fornecedores = new ArrayList<>();
        ResultSet rs = CRUD.select("fornecedor");
        while (rs.next()) {
            Fornecedor fornecedor = new Fornecedor(
                    rs.getInt("idfornecedor"), 
                    rs.getString("nome"),rs.getString("cpf"),
                    rs.getString("cnpj")
            );
            fornecedores.add(fornecedor);
        }
       return fornecedores;
    }

    @Override
    public Fornecedor selecionarPorCodigo(int id) throws SQLException {
        Fornecedor fornecedor = new Fornecedor();
        ResultSet rs = CRUD.select("fornecedor","idFornecedor",id);
        while (rs.next()) {
            fornecedor = new Fornecedor(rs.getInt("idfornecedor"), rs.getString("nome"),rs.getString("cpf"),rs.getString("cnpj"));
            
        }
       return fornecedor;
    }

    @Override
    public void deletar(Fornecedor fornecedor) throws SQLException {
         CRUD.delete("fornecedor","idfornecedor",Integer.toString(fornecedor.getIdFornecedor()));
    }
}
