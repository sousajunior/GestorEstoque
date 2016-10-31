
package br.com.gestorestoque.controller.filtros.produtoArmazenado;

import br.com.gestorestoque.controller.filtros.Filtro;
import br.com.gestorestoque.model.ProdutoArmazenado;
import br.com.gestorestoque.util.FiltroUtil;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DG
 */
public class FiltroLoteProdutoArmazenado implements Filtro<ProdutoArmazenado> {

    
    String lote;
    int selectedIndex;

    public FiltroLoteProdutoArmazenado( String lote, int selectedIndex) {
        
        this.lote = lote;
        this.selectedIndex = selectedIndex;
    }

    @Override
    public List<ProdutoArmazenado> filtrar(List<ProdutoArmazenado> lista) {

        List<ProdutoArmazenado> produtosArmazenadosPesquisa = new ArrayList<>();
        if (FiltroUtil.validarIndex(selectedIndex)) {
            if (selectedIndex == 1) {
                for (ProdutoArmazenado produto : lista) {
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
                for (ProdutoArmazenado produto : lista) {
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

    
}
