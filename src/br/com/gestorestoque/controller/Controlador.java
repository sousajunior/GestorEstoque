/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestorestoque.controller;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author DG
 */
public interface Controlador<T> {
    //Interface ainda não implementada, os controladores devem deixar de serem 
    //estáticos e devem passar a implementar esta interface

    public void inserir(T modelo) throws SQLException;

    public void atualizarPorCodigo(T modelo) throws SQLException;

    public List<T> selecionarTodos() throws SQLException;

    public T selecionarPorCodigo(int id) throws SQLException;

    public void deletar(T modelo) throws SQLException;
}
