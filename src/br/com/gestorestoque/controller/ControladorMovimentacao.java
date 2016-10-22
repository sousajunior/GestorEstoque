/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestorestoque.controller;

import br.com.gestorestoque.banco.CRUD;
import br.com.gestorestoque.model.Movimentacao;
import br.com.gestorestoque.util.EstoqueDataUtil;
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
                + movimentacao.getNotaFiscal() + "','"
                + movimentacao.getTipoMovimentacao() + "','"
                + EstoqueDataUtil.converterDataEmString(movimentacao.getData()) + "',"
                + movimentacao.getIdProdutoArmazenado().getCodigo() + ","
                + movimentacao.getIdArmazem().getCodigo());
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
     * @deprecated 
     * @param movimentacao
     * @throws SQLException 
     */
    @Override
    public void deletar(Movimentacao movimentacao) throws SQLException {
        CRUD.delete(nomeTabela, "idmovimentacao", "" + movimentacao.getId());
    }

}
