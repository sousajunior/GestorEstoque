/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestorestoque.controller;

import br.com.gestorestoque.model.Movimentacao;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author DG
 */
public class ControladorMovimentacao implements Controlador<Movimentacao>{

    @Override
    public void inserir(Movimentacao movimentacao) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizarPorCodigo(Movimentacao movimentacao) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Movimentacao> selecionarTodos() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Movimentacao selecionarPorCodigo(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletar(Movimentacao movimentacao) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
