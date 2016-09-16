/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//teste
package br.com.gestorestoque.controller;

import br.com.gestorestoque.banco.CRUD;
import br.com.gestorestoque.model.Produto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DG
 */
public class ControladorProduto implements Controlador<Produto> {

    private  final String nomeTabela;
    private ControladorUnidadeMedida ctrlUn = new ControladorUnidadeMedida();
    public ControladorProduto() {
        this.nomeTabela = "produto";
    }

    @Override
    public void inserir(Produto produto) throws SQLException {
        CRUD.insert(
                nomeTabela, "null,'" + produto.getNome() + "'," + 
                produto.isControladoPorLote() + "," + 
                produto.getQuantidadeMinima() + "," + 
                produto.getPreco() + "," + 
                produto.getUnidadeMedida().getCodigo()
        );
    }

    @Override
    public void atualizarPorCodigo(Produto produto) throws SQLException {
        CRUD.update(
                nomeTabela, "nome = '" + produto.getNome() + 
                "', controladoPorLote = " + produto.isControladoPorLote() + 
                ", quantidadeMinima = " + produto.getQuantidadeMinima() + 
                ", preco = " + produto.getPreco() + ", unidadeMedida_idUnidadeMedida = " + 
                produto.getUnidadeMedida().getCodigo(), 
                "codigoProduto", "" + produto.getCodigo()
        );
    }

    @Override
    public List<Produto> selecionarTodos() throws SQLException {
        List<Produto> produtos = new ArrayList<>();

        ResultSet rs = CRUD.select(nomeTabela);

        while (rs.next()) {
            Produto produto = new Produto(rs.getInt("codigoProduto"), rs.getString("nome"), 
                    rs.getBoolean("controladoPorLote"), rs.getDouble("quantidadeMinima"), 
                    rs.getDouble("preco"), 
                    ctrlUn.selecionarPorCodigo(rs.getInt("unidadeMedida_idunidadeMedida")));
            produtos.add(produto);
        }
        return produtos;
    }

    @Override
    public Produto selecionarPorCodigo(int id) throws SQLException {
        Produto produto = new Produto();

        ResultSet rs = CRUD.select(nomeTabela, "where codigoProduto = " + id);

        while (rs.next()) {
            produto = new Produto(rs.getInt("codigoProduto"), 
                    rs.getString("nome"), rs.getBoolean("controladoPorLote"), 
                    rs.getDouble("quantidadeMinima"), rs.getDouble("preco"), 
                    ctrlUn.selecionarPorCodigo(rs.getInt("unidadeMedida_idunidadeMedida")));
        }
        return produto;
    }

    @Override
    public void deletar(Produto produto) throws SQLException {
        CRUD.delete(nomeTabela, "codigoProduto", "" + produto.getCodigo());
    }

}
