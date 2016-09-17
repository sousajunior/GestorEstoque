/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestorestoque.controller;

import br.com.gestorestoque.banco.CRUD;
import br.com.gestorestoque.model.Movimentacao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DG
 */
public class ControladorMovimentacao implements Controlador<Movimentacao> {

    private final String nomeTabela;
    private ControladorArmazem ctrlArmazem = new ControladorArmazem();
    private ControladorProdutoArmazenado ctrlProdutoArmazenado = new ControladorProdutoArmazenado();

    public ControladorMovimentacao() {
        this.nomeTabela = "movimentacoes";
    }

    @Override
    public void inserir(Movimentacao movimentacao) throws SQLException {
        CRUD.insert(nomeTabela, "null,'" + movimentacao.getLote() + "',"
                + movimentacao.getQtd() + ",'"
                + movimentacao.getNotaFiscal() + "',"
                + movimentacao.getTipoMovimentacao() + ",'"
                + movimentacao.getData() + "',"
                + movimentacao.getIdProdutoArmazenado() + ","
                + movimentacao.getIdArmazem());
    }
/**
 * @deprecated 
 * @param movimentacao
 * @throws SQLException 
 */

    @Override
    public void atualizarPorCodigo(Movimentacao movimentacao) throws SQLException {
        CRUD.update(
                nomeTabela, "lote = '" + movimentacao.getLote()
                + "', quantidade = " + movimentacao.getQtd()
                + ", notaFiscal = '" + movimentacao.getNotaFiscal()
                + "', tipo = '" + movimentacao.getTipoMovimentacao()
                + "', data = '" + movimentacao.getData()
                + "', produtoArmazenado_idProdutoArmazenado = " + movimentacao.getIdProdutoArmazenado().getCodigo() + ","
                + ", armazem_codigoArmazem = " + movimentacao.getIdArmazem().getCodigo(),
                "idmovimentacao", "" + movimentacao.getId());
    }

    @Override
    public List<Movimentacao> selecionarTodos() throws SQLException {

        List<Movimentacao> movimentacoes = new ArrayList<>();

        ResultSet rs = CRUD.select(nomeTabela);

        while (rs.next()) {
            Movimentacao movimentacao;
            movimentacao = new Movimentacao(rs.getInt("idmovimentacao"),
                    rs.getString("lote"),
                    rs.getDouble("quantidade"),
                    rs.getInt("notaFiscal"),
                    rs.getString("tipo"),
                    rs.getDate("data"),
                    ctrlProdutoArmazenado.selecionarPorCodigo(rs.getInt("produtoArmazenado_idProdutoArmazenado")),
                    ctrlArmazem.selecionarPorCodigo(rs.getInt("armazem_codigoArmazem")));
            movimentacoes.add(movimentacao);
        }
        return movimentacoes;
    }
    
    
    @Override
    public Movimentacao selecionarPorCodigo(int id) throws SQLException {
        Movimentacao movimentacao = new Movimentacao();

        ResultSet rs = CRUD.select(nomeTabela, "where idmovimentacao = " + id);

        if (rs.first()) {
            movimentacao = new Movimentacao(rs.getInt("idmovimentacao"),
                    rs.getString("lote"),
                    rs.getDouble("quantidade"),
                    rs.getInt("notaFiscal"),
                    rs.getString("tipo"),
                    rs.getDate("data"),
                    ctrlProdutoArmazenado.selecionarPorCodigo(rs.getInt("produtoArmazenado_idProdutoArmazenado")),
                    ctrlArmazem.selecionarPorCodigo(rs.getInt("armazem_codigoArmazem")));
        }
        return movimentacao;
    }

    /**
     * Executa um método que seleciona todos os armazéns cadastrados na base de
     * dados. Método select da classe CRUD.
     *
     * @param codigosMovimentacoes
     * @return rs ResultSet
     * @throws SQLException
     */
    public ResultSet selecionarParaRelatorio(String codigosMovimentacoes) throws SQLException {

        return CRUD.queryRelatorio("select case when m.lote IS NULL THEN  ''\n"
                + "		    else m.lote\n"
                + "            end  as LOTE,\n"
                + "	   m.quantidade as QUANTIDADE,\n"
                + "       m.notaFiscal as NOTA_FISCAL,\n"
                + "       UPPER (m.tipo) as TIPO,\n"
                + "       m.data as DATA,\n"
                + "       p.nome as PRODUTO,\n"
                + "       a.descricao as ARMAZEM\n"
                + "from movimentacoes m,\n"
                + "	 produtoarmazenado pa,\n"
                + "     produto p,\n"
                + "     armazem a\n"
                + "where m.produtoArmazenado_idProdutoArmazenado = pa.idProdutoArmazenado\n"
                + "and pa.produto_codigoProduto = p.codigoProduto\n"
                + "and m.armazem_codigoArmazem = a.codigoArmazem\n"
                + "and idmovimentacao in(" + codigosMovimentacoes
                + ")");

    }
    /**
     * @deprecated 
     * @param movimentacao
     * @throws SQLException 
     */
    @Override
    public void deletar(Movimentacao movimentacao) throws SQLException {
        CRUD.delete(nomeTabela, "idmovimentacao", "" + movimentacao.getId());
    }

}
