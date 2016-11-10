
package br.com.gestorestoque.DAO;

import br.com.gestorestoque.banco.CRUD;
import br.com.gestorestoque.view.enumerado.Relatorio;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author DG
 */
public class DAORelatorios {

    public DAORelatorios() {

    }

    public ResultSet executarConsultaRelatorio(Relatorio relatorio, String codigos) throws SQLException {

        switch (relatorio) {
            case RelatorioGeralMovimentacoes:
                return relatorioGeralMovimentacoes(codigos);
            case RelatorioSaldoEstoque:
                return relatorioGeralProdutoArmazenado(codigos);
            case RelatorioSaldoGeralProdutos:
                return relatorioSaldoGeral(codigos);
            case RelatorioProdutos:
                return relacaoProdutos(codigos);

        }

        return null;
    }

    /**
     * Executa um método que seleciona todos os armazéns cadastrados na base de
     * dados. Método select da classe CRUD.
     *
     * @param codigosMovimentacoes
     * @return rs ResultSet
     * @throws SQLException
     */
    private ResultSet relatorioGeralMovimentacoes(String codigosMovimentacoes) throws SQLException {

        return CRUD.queryCompleta("select case when m.lote IS NULL THEN  ''\n"
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
     * Executa um método que seleciona todos os armazéns cadastrados na base de
     * dados. Método select da classe CRUD.
     *
     * @param codigosProdutosArmazenados
     * @return rs ResultSet
     * @throws SQLException
     */
    public ResultSet relatorioGeralProdutoArmazenado(String codigosProdutosArmazenados) throws SQLException {

        return CRUD.queryCompleta("select p.nome as PRODUTO,\n"
                + "       case when pa.lote IS NULL THEN\n"
                + "                  ''\n"
                + "            else\n"
                + "            pa.lote\n"
                + "            end  as LOTE,\n"
                + "       f.nome as FORNECEDOR,\n"
                + "       pa.quantidade as SALDO,\n"
                + "       um.abreviacao as UM,\n"
                + "       a.descricao as ARMAZEM,\n"
                + "       p.controladoPorLote as CONTROLADO_POR_LOTE,\n"
                + "       pa.notaFiscal as NOTA_FISCAL,\n"
                + "       p.preco as PRECO\n"
                + "from  produtoArmazenado pa,\n"
                + "      fornecedor f,\n"
                + "      produto p,\n"
                + "      unidademedida um,\n"
                + "      armazem a\n"
                + "where pa.fornecedor_idFornecedor = f.idFornecedor\n"
                + "and pa.produto_codigoProduto = p.codigoProduto\n"
                + "and p.unidadeMedida_idunidadeMedida = um.idunidadeMedida\n"
                + "and pa.armazem_codigoArmazem = a.codigoArmazem\n"
                + "and pa.idprodutoArmazenado in(" + codigosProdutosArmazenados
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
    public ResultSet relatorioSaldoGeral(String codigosProdutos) throws SQLException {

        return CRUD.queryCompleta("select p.nome as PRODUTO,\n"
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

        /**
     * Executa um método que seleciona todos os armazéns cadastrados na base de
     * dados. Método select da classe CRUD.
     *
     * @param codigosProdutos
     * @return rs ResultSet
     * @throws SQLException
     */
    public ResultSet relacaoProdutos(String codigosProdutos) throws SQLException {

        return CRUD.queryCompleta("select p.nome as PRODUTO,\n"
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

    
}
