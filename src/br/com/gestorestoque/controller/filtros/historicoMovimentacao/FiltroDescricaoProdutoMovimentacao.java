
package br.com.gestorestoque.controller.filtros.historicoMovimentacao;

import br.com.gestorestoque.controller.filtros.Filtro;
import br.com.gestorestoque.model.Movimentacao;
import br.com.gestorestoque.util.FiltroUtil;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DG
 */
public class FiltroDescricaoProdutoMovimentacao implements Filtro<Movimentacao>{

    
    String produtoCod;
    int selectedIndex;

    public FiltroDescricaoProdutoMovimentacao( String produtoCod, int selectedIndex) {
        
        this.produtoCod = produtoCod;
        this.selectedIndex = selectedIndex;
    }

    @Override
    public List<Movimentacao> filtrar(List<Movimentacao> lista) {
        List<Movimentacao> movimentacaoPesquisa = new ArrayList<>();
        if (FiltroUtil.validarIndex(selectedIndex)) {
            if (selectedIndex == 1) {
                for (Movimentacao movimentacao : lista) {
                    if (movimentacao.getIdProdutoArmazenado().getProduto().getNome().toUpperCase().contains(produtoCod.toUpperCase())) {
                        movimentacaoPesquisa.add(movimentacao);
                    }
                }
            } else if (selectedIndex == 2) {
                for (Movimentacao produto : lista) {
                    if (produto.getIdProdutoArmazenado().getProduto().getNome().toUpperCase().equalsIgnoreCase(produtoCod.toUpperCase())) {
                        movimentacaoPesquisa.add(produto);
                    }
                }
            }
            return movimentacaoPesquisa;
        }
        return null;
    }

}
