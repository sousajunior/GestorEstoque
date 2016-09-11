/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestorestoque.controller;

import br.com.gestorestoque.banco.CRUD;
import br.com.gestorestoque.model.ProdutoArmazenado;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DG
 */
public class ControladorProdutoArmazenado {
    
        public static final String nomeTabela = "produtoArmazenado"; 
    
    
    /**
     * Executa um método que seleciona todos os armazéns cadastrados na base de dados.
     * Método select da classe CRUD.
     * @return List< Produto >
     * @throws SQLException 
     */
    public  static List<ProdutoArmazenado> selecionarTodosProdutosArmazenados() throws SQLException {

        List<ProdutoArmazenado> produtosArmazenados = new ArrayList<>();
        
        ResultSet rs =  CRUD.select(nomeTabela);

        while (rs.next()) {
            ProdutoArmazenado produtoArmazenado = new ProdutoArmazenado(rs.getInt("idProdutoArmazenado"), rs.getString("lote"), rs.getDouble("quantidade"),rs.getInt("notaFiscal"), ControladorProduto.selecionarProdutoPorCodigo(""+rs.getInt("produto_codigoProduto")), ControladorFornecedor.selecionarFornecedorPorCodigo(""+rs.getInt("fornecedor_idFornecedor")), ControladorArmazem.selecionarArmazemPorCodigo(""+rs.getInt("armazem_codigoArmazem")));
            produtosArmazenados.add(produtoArmazenado);
        }

        return produtosArmazenados;

    }
    
    /**
     * Executa o método insert da classe CRUD, passando tabela produto e os valores necessários para, inserir um produto na base de dados.
     * É necessário passar por parâmetro um produto.
     * @param produto
     * @throws SQLException 
     */
//    public static void inserirProduto(Produto produto) throws SQLException{
//        
//        CRUD.insert(nomeTabela, "null,'"+produto.getNome()+"',"+produto.isControladoPorLote()+","+produto.getQuantidadeMinima()+","+produto.getPreco()+","+produto.getUnidadeMedida().getCodigo());
//        
//    }
//    
//    /**
//     * Executa o método update da classe CRUD, passando a tabela de produto, e os valores necessários para atualizar um produto que já está cadastrado na base de dados.
//     * é necessário informar via parâmetro a coluna a ser alterada, o valor para esta coluna, e a coluna e valor para a clausula where.
//     * @param valores
//     * @param valorWhere
//     * @throws SQLException 
//     */
//    public static void updateProdutoPorCodigo(Produto produto) throws SQLException{
//        
//        CRUD.update(nomeTabela, "nome = '"+produto.getNome()+"', controladoPorLote = "+produto.isControladoPorLote()+", quantidadeMinima = "+produto.getQuantidadeMinima()+", preco = "+produto.getPreco()+", unidadeMedida_idUnidadeMedida = "+produto.getUnidadeMedida().getCodigo(),"codigoProduto",""+produto.getCodigo());
//        
//    }
//    
//    /**
//     * Executa o metodo delete da classe CRUD, passando por parâmetro a tabela, a coluna e valor para a clausula where.
//     * É necessário passar um produto por parâmetro, ao invocar este método.
//     * @param produto
//     * @throws SQLException 
//     */
//    public static void deletarProduto(Produto produto) throws SQLException{
//        
//        CRUD.delete(nomeTabela,"codigoProduto" ,""+produto.getCodigo());
//        
//    }
//    
//
//    
//
    
}
