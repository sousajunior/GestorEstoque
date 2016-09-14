/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestorestoque.controller;

/**
 *
 * @author DG
 */
public interface Controlador {
    //Interface ainda não implementada, os controladores devem deixar de serem 
    //estáticos e devem passar a implementar esta interface
    public void inserir();
    public void atualizarPorCodigo();
    public void selecionarTudo();
    public void selecionarPorCodigo();
    public void deletar();
}
