
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
public class ControladorProdutoArmazenado implements Controlador<ProdutoArmazenado> {
    
    private  final String nomeTabela;
    private  ControladorArmazem ctrlArmazem;

    public ControladorProdutoArmazenado() {
        this.nomeTabela = "produtoArmazenado";
        this.ctrlArmazem = new ControladorArmazem();
    }

    
    
    

    @Override
    public void inserir(ProdutoArmazenado produto) throws SQLException {
        CRUD.insert("produtoarmazenado", "null,'" + produto.getLote() + "'," + produto.getQuantidade() + "," + produto.getNotaFiscal() + "," + produto.getArmazem().getCodigo() + "," + produto.getFornecedor().getIdFornecedor() + "," + produto.getProduto().getCodigo());
    }

    @Override
    public void atualizarPorCodigo(ProdutoArmazenado produtoArmazenado) throws SQLException {
        CRUD.update(nomeTabela, "quantidade = '" + produtoArmazenado.getQuantidade() + "'", "idProdutoArmazenado", "" + produtoArmazenado.getCodigo());
    }

    @Override
    public List<ProdutoArmazenado> selecionarTodos() throws SQLException {
        List<ProdutoArmazenado> produtosArmazenados = new ArrayList<>();

        ResultSet rs = CRUD.select(nomeTabela);

        while (rs.next()) {
            ProdutoArmazenado produtoArmazenado
                    = new ProdutoArmazenado(
                            rs.getInt("idProdutoArmazenado"),
                            rs.getString("lote"),
                            rs.getDouble("quantidade"),
                            rs.getInt("notaFiscal"),
                            new ControladorProduto().selecionarPorCodigo(rs.getInt("produto_codigoProduto")),
                            new ControladorFornecedor().selecionarPorCodigo(rs.getInt("fornecedor_idFornecedor")),
                            ctrlArmazem.selecionarPorCodigo(rs.getInt("armazem_codigoArmazem"))
                    );
            produtosArmazenados.add(produtoArmazenado);
        }

        return produtosArmazenados;
    }

    @Override
    public ProdutoArmazenado selecionarPorCodigo(int id) throws SQLException {
        ProdutoArmazenado produtoArmazenado = new ProdutoArmazenado();

        ResultSet rs = CRUD.select(nomeTabela, "where idProdutoArmazenado = " + id);

        if(rs.first()){
        produtoArmazenado = new ProdutoArmazenado(
                rs.getInt("idProdutoArmazenado"),
                rs.getString("lote"),
                rs.getDouble("quantidade"),
                rs.getInt("notaFiscal"),
                new ControladorProduto().selecionarPorCodigo(rs.getInt("produto_codigoProduto")),
                new ControladorFornecedor().selecionarPorCodigo(rs.getInt("fornecedor_idFornecedor")),
                ctrlArmazem.selecionarPorCodigo(rs.getInt("armazem_codigoArmazem"))
        );
        }

        return produtoArmazenado;
    }
    
    public ProdutoArmazenado selecionarUltimoRegistro() throws SQLException {
        ProdutoArmazenado produtoArmazenado = new ProdutoArmazenado();

        ResultSet rs = CRUD.queryCompleta("SELECT * FROM produtoarmazenado ORDER BY idprodutoarmazenado DESC LIMIT 1");

        if(rs.first()){
        produtoArmazenado = new ProdutoArmazenado(
                rs.getInt("idProdutoArmazenado"),
                rs.getString("lote"),
                rs.getDouble("quantidade"),
                rs.getInt("notaFiscal"),
                new ControladorProduto().selecionarPorCodigo(rs.getInt("produto_codigoProduto")),
                new ControladorFornecedor().selecionarPorCodigo(rs.getInt("fornecedor_idFornecedor")),
                ctrlArmazem.selecionarPorCodigo(rs.getInt("armazem_codigoArmazem"))
        );
        }

        return produtoArmazenado;
    }
    

    @Override
    public void deletar(ProdutoArmazenado produto) throws SQLException {
        CRUD.delete(nomeTabela, "idProdutoArmazenado", "" + produto.getCodigo());
    }

    /**
     * Seleciona uma quantidade especifica de produtos armazenados.Se a base tiver menos registros que a quantidade especificada, todos os registros serão listados
     * @param n quantidade de elementos desejados
     * @return List - Retorna uma lista contendo os n primeiros produtos armazenados
     * @throws SQLException
     */
    public List<ProdutoArmazenado> selecionarConjunto(int n) throws SQLException {
        List<ProdutoArmazenado> produtosArmazenados = new ArrayList<>();

        ResultSet rs = CRUD.select(nomeTabela);
        int cont = 0;
        while (rs.next() && cont++ < n) {
            ProdutoArmazenado produtoArmazenado
                    = new ProdutoArmazenado(
                            rs.getInt("idProdutoArmazenado"),
                            rs.getString("lote"),
                            rs.getDouble("quantidade"),
                            rs.getInt("notaFiscal"),
                            new ControladorProduto().selecionarPorCodigo(rs.getInt("produto_codigoProduto")),
                            new ControladorFornecedor().selecionarPorCodigo(rs.getInt("fornecedor_idFornecedor")),
                            ctrlArmazem.selecionarPorCodigo(rs.getInt("armazem_codigoArmazem"))
                    );
            produtosArmazenados.add(produtoArmazenado);
        }

        return produtosArmazenados;
    }
    

   /*
    //public ControladorProdutoArmazenado() {
    //  this.nomeTabela = "produtoArmazenado";
    //}
    /**
     * Executa um método que seleciona todos os armazéns cadastrados na base de
     * dados. Método select da classe CRUD.
     *
     * @return List< Produto >
     * @throws SQLException
     
    public static List<ProdutoArmazenado> selecionarTodosProdutosArmazenados() throws SQLException {

        List<ProdutoArmazenado> produtosArmazenados = new ArrayList<>();

        ResultSet rs = CRUD.select(nomeTabela);

        while (rs.next()) {
            ProdutoArmazenado produtoArmazenado
                    = new ProdutoArmazenado(
                            rs.getInt("idProdutoArmazenado"),
                            rs.getString("lote"),
                            rs.getDouble("quantidade"),
                            rs.getInt("notaFiscal"),
                            new ControladorProduto().selecionarPorCodigo(rs.getInt("produto_codigoProduto")),
                            new ControladorFornecedor().selecionarPorCodigo(rs.getInt("fornecedor_idFornecedor")),
                            ctrlArmazem.selecionarPorCodigo(rs.getInt("armazem_codigoArmazem"))
                    );
            produtosArmazenados.add(produtoArmazenado);
        }

        return produtosArmazenados;

    }

    public static ProdutoArmazenado selecionarPorLote() throws SQLException {
        ProdutoArmazenado p = new ProdutoArmazenado();
        return p;
    }

    

    /**
     * Executa o método insert da classe CRUD, passando tabela produto e os
     * valores necessários para, inserir um produto na base de dados. É
     * necessário passar por parâmetro um produto.
     *
     * @param produto
     * @throws SQLException
     
    public static void inserirProduto(ProdutoArmazenado produto) throws SQLException {
        CRUD.insert("produtoarmazenado", "null,'" + produto.getLote() + "'," + produto.getQuantidade() + "," + produto.getNotaFiscal() + "," + produto.getArmazem().getCodigo() + "," + produto.getFornecedor().getIdFornecedor() + "," + produto.getProduto().getCodigo());
    }

    /**
     * Executa o método update da classe CRUD, passando a tabela de produto, e
     * os valores necessários para atualizar um produto que já está cadastrado
     * na base de dados. é necessário informar via parâmetro a coluna a ser
     * alterada, o valor para esta coluna, e a coluna e valor para a clausula
     * where.
     *
     * @param produtoArmazenado
     * @throws SQLException
     
    public static void updateSaldoProdutoArmazenado(ProdutoArmazenado produtoArmazenado) throws SQLException {

        CRUD.update(nomeTabela, "quantidade = '" + produtoArmazenado.getQuantidade() + "'", "idProdutoArmazenado", "" + produtoArmazenado.getCodigo());

    }

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
//*/
}
