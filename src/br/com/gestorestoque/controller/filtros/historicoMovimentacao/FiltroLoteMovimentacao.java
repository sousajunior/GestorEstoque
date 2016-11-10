
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
public class FiltroLoteMovimentacao implements Filtro<Movimentacao> {

    
    String lote;
    int selectedIndex;

    public FiltroLoteMovimentacao( String lote, int selectedIndex) {
        
        this.lote = lote;
        this.selectedIndex = selectedIndex;
    }

    @Override
    public List<Movimentacao> filtrar(List<Movimentacao> lista) {

        List<Movimentacao> movimentacaoPesquisa = new ArrayList<>();
        if (FiltroUtil.validarIndex(selectedIndex)) {
            if (selectedIndex == 1) {
                for (Movimentacao movimentacao : lista) {
                    
                    if (movimentacao.getIdProdutoArmazenado().getProduto().isControladoPorLote()) {
                    
                        if (movimentacao.getLote().toUpperCase().contains(lote.toUpperCase())) {
                    
                            movimentacaoPesquisa.add(movimentacao);
                        }
                    }
                }
            } else if (selectedIndex == 2) {
                for (Movimentacao movimentacao : lista) {
                
                    if (movimentacao.getIdProdutoArmazenado().getProduto().isControladoPorLote()) {
                
                        if (movimentacao.getLote().toUpperCase().equalsIgnoreCase(lote.toUpperCase())) {
                
                            movimentacaoPesquisa.add(movimentacao);
                        }
                    }
                }
            }
            return movimentacaoPesquisa;
        }
        return null;
    }

    
}
