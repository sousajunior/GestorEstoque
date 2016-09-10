package br.com.gestorestoque.model;

import java.util.ArrayList;
import java.util.List;

public class Armazem {

    private Integer codigo;

    private String descricao;

    private List<ProdutoArmazenado> produtosArmazenados;

    public Armazem(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
        produtosArmazenados = new ArrayList<>();
    }
    
    public Armazem( String descricao) {
        
        this.descricao = descricao;
        
    }

    public Armazem() {

    }

    public void DarEntrada(ProdutoArmazenado produto) {
        ProdutoArmazenado produtoEncontrado = verificaExistencia(produto);
        
        //Produto já existe na lista    
        if (produtoEncontrado != null) {
            //Nao e controlado por lote
            if (!produtoEncontrado.getProduto().isControladoPorLote()) {
                produtoEncontrado.aumentarQuantidade(produto);
            } else {
                this.getProdutosArmazenados().add(produto);
            }
        } else {
            this.getProdutosArmazenados().add(produto);
        }
//registrar entrada no banco
    }

    public void DarBaixa(ProdutoArmazenado produto, double qtd) {
        ProdutoArmazenado produtoEncontrado = verificaExistencia(produto);
        
            if (produtoEncontrado != null) {
                //registrar no banco
                produtoEncontrado.setQuantidade(produto.getQuantidade() - qtd);
            }
    }

    private ProdutoArmazenado verificaExistencia(ProdutoArmazenado produto) {
        for (ProdutoArmazenado p : getProdutosArmazenados()) {
            if (produto.getProduto().getCodigo() == p.getProduto().getCodigo()) {
                return p;
            }
        }
        return null;
    }

    /**
     * @return the codigo
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the produtosArmazenados
     */
    public List<ProdutoArmazenado> getProdutosArmazenados() {
        return produtosArmazenados;
    }

    /**
     * @param produtosArmazenados the produtosArmazenados to set
     */
    public void setProdutosArmazenados(List<ProdutoArmazenado> produtosArmazenados) {
        this.produtosArmazenados = produtosArmazenados;
    }
    
    
    
    
    
    
    
}
