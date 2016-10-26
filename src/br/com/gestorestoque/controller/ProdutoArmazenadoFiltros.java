/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestorestoque.controller;

import br.com.gestorestoque.model.ProdutoArmazenado;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Familia
 */
public class ProdutoArmazenadoFiltros {

    List<ProdutoArmazenado> produtos = new ArrayList<>();

    public ProdutoArmazenadoFiltros(List<ProdutoArmazenado> produtos) {
        this.produtos = produtos;
    }
    
    public void atualizarLista(List<ProdutoArmazenado> produtos){
        this.produtos = produtos;
    }
    
    public void limparFiltros() {
        this.produtos = new ArrayList<>();
    }

    public List<ProdutoArmazenado> filtrarPorLote(String lote, int selectedIndex) {
        List<ProdutoArmazenado> produtosArmazenadosPesquisa = new ArrayList<>();
        if (validarIndex(selectedIndex)) {
            if (selectedIndex == 1) {
                for (ProdutoArmazenado produto : produtos) {
                    //verifica se o produto é controlado por lote
                    if (produto.getProduto().isControladoPorLote()) {
                        //verifica se o lote da posição atual contem a expressão que o usuário digitou
                        if (produto.getLote().toUpperCase().contains(lote.toUpperCase())) {
                            //adiciona na lista quando encontra um lote que contenha o que o usuário digitou
                            produtosArmazenadosPesquisa.add(produto);
                        }
                    }
                }
            } else if (selectedIndex == 2) {
                for (ProdutoArmazenado produto : produtos) {
                    //verifica se o produto é controlado por lote
                    if (produto.getProduto().isControladoPorLote()) {
                        //verifica se o lote da posição atual contem a expressão que o usuário digitou
                        if (produto.getLote().toUpperCase().equalsIgnoreCase(lote.toUpperCase())) {
                            //adiciona na lista quando encontra um lote que contenha o que o usuário digitou
                            produtosArmazenadosPesquisa.add(produto);
                        }
                    }
                }
            }
            return produtosArmazenadosPesquisa;
        }
        return null;
    }

    public List<ProdutoArmazenado> filtrarPorProduto(String produtoCod, int selectedIndex) {
        List<ProdutoArmazenado> produtosArmazenadosPesquisa = new ArrayList<>();
        if (validarIndex(selectedIndex)) {
            if (selectedIndex == 1) {
                for (ProdutoArmazenado produto : produtos) {
                    if (produto.getProduto().getNome().toUpperCase().contains(produtoCod.toUpperCase())) {
                        produtosArmazenadosPesquisa.add(produto);
                    }
                }
            } else if (selectedIndex == 2) {
                for (ProdutoArmazenado produto : produtos) {
                    if (produto.getProduto().getNome().toUpperCase().equalsIgnoreCase(produtoCod.toUpperCase())) {
                        produtosArmazenadosPesquisa.add(produto);
                    }
                }
            }
            return produtosArmazenadosPesquisa;
        }
        return null;
    }

    public List<ProdutoArmazenado> filtrarPorUnidadeDeMedida(String unidadeAbreviada, int selectedIndex) {
        List<ProdutoArmazenado> produtosArmazenadosPesquisa = new ArrayList<>();
        if (validarIndex(selectedIndex)) {
            if (selectedIndex == 1) {
                for (ProdutoArmazenado produto : produtos) {
                    if (produto.getProduto().getUnidadeMedida().getAbreviacao().toUpperCase().contains(unidadeAbreviada.toUpperCase())) {
                        produtosArmazenadosPesquisa.add(produto);
                    }
                }
            } else if (selectedIndex == 2) {
                for (ProdutoArmazenado produto : produtos) {
                    if (produto.getProduto().getUnidadeMedida().getAbreviacao().toUpperCase().equalsIgnoreCase(unidadeAbreviada.toUpperCase())) {
                        produtosArmazenadosPesquisa.add(produto);
                    }
                }
            }
            return produtosArmazenadosPesquisa;
        }
        return null;
    }

    public List<ProdutoArmazenado> filtrarPorFornecedor(String fornNome, int selectedIndex) {
        List<ProdutoArmazenado> produtosArmazenadosPesquisa = new ArrayList<>();
        if (validarIndex(selectedIndex)) {
            if (selectedIndex == 1) {
                for (ProdutoArmazenado produto : produtos) {
                    if (produto.getFornecedor().getNome().toUpperCase().contains(fornNome.toUpperCase())) {
                        produtosArmazenadosPesquisa.add(produto);
                    }
                }
            } else if (selectedIndex == 2) {
                for (ProdutoArmazenado produto : produtos) {
                    if (produto.getFornecedor().getNome().toUpperCase().equalsIgnoreCase(fornNome.toUpperCase())) {
                        produtosArmazenadosPesquisa.add(produto);
                    }
                }
            }
            return produtosArmazenadosPesquisa;
        }
        return null;
    }

    public List<ProdutoArmazenado> filtrarPorNotaFical(String notaFiscal, int selectedIndex) {
        List<ProdutoArmazenado> produtosArmazenadosPesquisa = new ArrayList<>();
        if (validarIndex(selectedIndex)) {
            if (selectedIndex == 1) {
                for (ProdutoArmazenado produto : produtos) {
                    String nf = "" + produto.getNotaFiscal();
                    if (nf.contains(notaFiscal)) {
                        produtosArmazenadosPesquisa.add(produto);
                    }
                }
            } else if (selectedIndex == 2) {
                for (ProdutoArmazenado produto : produtos) {
                    String nf = "" + produto.getNotaFiscal();
                    if (nf.equalsIgnoreCase(notaFiscal)) {
                        produtosArmazenadosPesquisa.add(produto);
                    }
                }
            }
            return produtosArmazenadosPesquisa;
        }
        return null;
    }

    public List<ProdutoArmazenado> filtrarPorSaldo(String saldo, int selectedIndex) {
        List<ProdutoArmazenado> produtosArmazenadosPesquisa = new ArrayList<>();
        if (validarIndex(selectedIndex)) {
            if (selectedIndex == 1) {
                for (ProdutoArmazenado produto : produtos) {
                    if (produto.getQuantidade() == Double.parseDouble(saldo)) {
                        produtosArmazenadosPesquisa.add(produto);
                    }
                }
            } else if (selectedIndex == 2) {
                for (ProdutoArmazenado produto : produtos) {
                    if (produto.getQuantidade() > Double.parseDouble(saldo)) {
                        produtosArmazenadosPesquisa.add(produto);
                    }
                }
            } else if (selectedIndex == 3) {
                for (ProdutoArmazenado produto : produtos) {
                    if (produto.getQuantidade() >= Double.parseDouble(saldo)) {
                        produtosArmazenadosPesquisa.add(produto);
                    }
                }
            } else if (selectedIndex == 4) {
                for (ProdutoArmazenado produto : produtos) {
                    if (produto.getQuantidade() < Double.parseDouble(saldo)) {
                        produtosArmazenadosPesquisa.add(produto);
                    }
                }
            } else if (selectedIndex == 5) {
                for (ProdutoArmazenado produto : produtos) {
                    if (produto.getQuantidade() <= Double.parseDouble(saldo)) {
                        produtosArmazenadosPesquisa.add(produto);
                    }
                }
            }
            return produtosArmazenadosPesquisa;
        }
        return null;
    }

    public List<ProdutoArmazenado> filtrarPorPreco(String preco, int selectedIndex) {
        List<ProdutoArmazenado> produtosArmazenadosPesquisa = new ArrayList<>();
        if (validarIndex(selectedIndex)) {
            if (selectedIndex == 1) {
                for (ProdutoArmazenado produto : produtos) {
                    if (produto.getProduto().getPreco() == Double.parseDouble(preco)) {
                        produtosArmazenadosPesquisa.add(produto);
                    }
                }
            } else if (selectedIndex == 2) {
                for (ProdutoArmazenado produto : produtos) {
                    if (produto.getProduto().getPreco() > Double.parseDouble(preco)) {
                        produtosArmazenadosPesquisa.add(produto);
                    }
                }
            } else if (selectedIndex == 3) {
                for (ProdutoArmazenado produto : produtos) {
                    if (produto.getProduto().getPreco() >= Double.parseDouble(preco)) {
                        produtosArmazenadosPesquisa.add(produto);
                    }
                }
            } else if (selectedIndex == 4) {
                for (ProdutoArmazenado produto : produtos) {
                    if (produto.getProduto().getPreco() < Double.parseDouble(preco)) {
                        produtosArmazenadosPesquisa.add(produto);
                    }
                }
            } else if (selectedIndex == 5) {
                for (ProdutoArmazenado produto : produtos) {
                    if (produto.getProduto().getPreco() <= Double.parseDouble(preco)) {
                        produtosArmazenadosPesquisa.add(produto);
                    }
                }
            }
            return produtosArmazenadosPesquisa;
        }
        return null;
    }

    public List<ProdutoArmazenado> filtrarPorArmazem(String armazem, int selectedIndex) {
        List<ProdutoArmazenado> produtosArmazenadosPesquisa = new ArrayList<>();
        if (validarIndex(selectedIndex)) {
            if (selectedIndex == 1) {
                for (ProdutoArmazenado produto : produtos) {
                    if (produto.getArmazem().getDescricao().toUpperCase().contains(armazem.toUpperCase())) {
                        produtosArmazenadosPesquisa.add(produto);
                    }
                }
            } else if (selectedIndex == 2) {
                for (ProdutoArmazenado produto : produtos) {
                    if (produto.getArmazem().getDescricao().toUpperCase().equalsIgnoreCase(armazem.toUpperCase())) {
                        produtosArmazenadosPesquisa.add(produto);
                    }
                }
            }
            return produtosArmazenadosPesquisa;
        }
        return null;
    }

    private boolean validarIndex(int index) {
        return index > 0 ? true : false;
    }
}