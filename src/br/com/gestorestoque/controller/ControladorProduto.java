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

    private final String nomeTabela;
    private ControladorUnidadeMedida ctrlUn = new ControladorUnidadeMedida();

    public ControladorProduto() {
        this.nomeTabela = "produto";
    }

    @Override
    public void inserir(Produto produto) throws SQLException {
        CRUD.insert(
                nomeTabela, "null,'" + produto.getNome() + "',"
                + produto.isControladoPorLote() + ","
                + produto.getQuantidadeMinima() + ","
                + produto.getPreco() + ","
                + produto.getUnidadeMedida().getCodigo()
        );
    }

    @Override
    public void atualizarPorCodigo(Produto produto) throws SQLException {
        CRUD.update(
                nomeTabela, "nome = '" + produto.getNome()
                + "', controladoPorLote = " + produto.isControladoPorLote()
                + ", quantidadeMinima = " + produto.getQuantidadeMinima()
                + ", preco = " + produto.getPreco() + ", unidadeMedida_idUnidadeMedida = "
                + produto.getUnidadeMedida().getCodigo(),
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

    /**
     * Executa um método que seleciona todos os armazéns cadastrados na base de
     * dados. Método select da classe CRUD.
     *
     * @param codigosProdutos
     * @return rs ResultSet
     * @throws SQLException
     */
    public ResultSet selecionarParaRelatorio(String codigosProdutos) throws SQLException {

        return CRUD.queryRelatorio("select p.nome as PRODUTO,\n"
                + "       um.abreviacao as UM,\n"
                + "       p.controladoPorLote as CONTROLADO_POR_LOTE,\n"
                + "       p.preco as PRECO,\n"
                + "       case when p.quantidadeMinima IS NULL THEN\n"
                + "                                  ''\n"
                + "                            else\n"
                + "                            p.quantidadeMinima\n"
                + "                            end  as qtdMinima\n"
                + "from  produto p,\n"
                + "      unidademedida um\n"
                + "where p.unidadeMedida_idunidadeMedida = um.idunidadeMedida\n"
                + "and p.codigoProduto in(" + codigosProdutos
                + ")");

    }

    /**
     * Executa um método que seleciona todos os armazéns cadastrados na base de
     * dados. Método select da classe CRUD.
     *
     * @param codigosProdutos
     * @return rs ResultSet
     * @throws SQLException
     */
    public ResultSet selecionarParaRelatorioSaldoGeral(String codigosProdutos) throws SQLException {

        return CRUD.queryRelatorio("select p.nome as PRODUTO,\n"
                + "       sum(pa.quantidade) as SALDO,\n"
                + "       um.abreviacao as UM,\n"
                + "       a.descricao as ARMAZEM,\n"
                + "       p.controladoPorLote as CONTROLADO_POR_LOTE,\n"
                + "       p.preco as PRECO\n"
                + "from  produtoArmazenado pa,\n"
                + "      produto p,\n"
                + "      unidademedida um,\n"
                + "      armazem a\n"
                + "where  pa.produto_codigoProduto = p.codigoProduto\n"
                + "and p.unidadeMedida_idunidadeMedida = um.idunidadeMedida\n"
                + "and pa.armazem_codigoArmazem = a.codigoArmazem\n"
                + "/*and pa.idprodutoArmazenado in(1,2)*/\n"
                + "and p.codigoProduto in(" + codigosProdutos + ")"
                + "group by PRODUTO,UM, ARMAZEM,CONTROLADO_POR_LOTE ");

    }

    @Override
    public void deletar(Produto produto) throws SQLException {
        CRUD.delete(nomeTabela, "codigoProduto", "" + produto.getCodigo());
    }

}
