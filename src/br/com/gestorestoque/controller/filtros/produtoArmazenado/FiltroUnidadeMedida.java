
package br.com.gestorestoque.controller.filtros.produtoArmazenado;

import br.com.gestorestoque.controller.filtros.Filtro;
import br.com.gestorestoque.model.ProdutoArmazenado;
import br.com.gestorestoque.util.FiltroUtil;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 5927161
 */
public class FiltroUnidadeMedida implements Filtro<ProdutoArmazenado> {

    private int selectedIndex;
    private String unidadeAbreviada;

    public FiltroUnidadeMedida(int selectedIndex, String unidadeAbreviada) {
        this.selectedIndex = selectedIndex;
        this.unidadeAbreviada = unidadeAbreviada;
    }

    @Override
    public List<ProdutoArmazenado> filtrar(List<ProdutoArmazenado> lista) {
        List<ProdutoArmazenado> produtosArmazenadosPesquisa = new ArrayList<>();
        if (FiltroUtil.validarIndex(selectedIndex)) {
            if (selectedIndex == 1) {
                for (ProdutoArmazenado produto : lista) {
                    if (produto.getProduto().getUnidadeMedida().getAbreviacao().toUpperCase().contains(unidadeAbreviada.toUpperCase())) {
                        produtosArmazenadosPesquisa.add(produto);
                    }
                }
            } else if (selectedIndex == 2) {
                for (ProdutoArmazenado produto : lista) {
                    if (produto.getProduto().getUnidadeMedida().getAbreviacao().toUpperCase().equalsIgnoreCase(unidadeAbreviada.toUpperCase())) {
                        produtosArmazenadosPesquisa.add(produto);
                    }
                }
            }
            return produtosArmazenadosPesquisa;
        }
        return null;
    }
}
