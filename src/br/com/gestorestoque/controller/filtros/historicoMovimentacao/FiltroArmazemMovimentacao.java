
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
public class FiltroArmazemMovimentacao implements Filtro<Movimentacao>{

    final private int selectedIndex;
    final private String armazem;

    public FiltroArmazemMovimentacao(int index,String armazem) {
        this.selectedIndex = index;
        this.armazem = armazem;
    }
    
    
    @Override
    public List<Movimentacao> filtrar(List<Movimentacao> lista) {
        List<Movimentacao> movimentacaoPesquisa = new ArrayList<>();
        if (FiltroUtil.validarIndex(selectedIndex)) {
            if (selectedIndex == 1) {
                for (Movimentacao movimentacao : lista) {
                    if (movimentacao.getIdArmazem().getDescricao().toUpperCase().contains(armazem.toUpperCase())) {
                        movimentacaoPesquisa.add(movimentacao);
                    }
                }
            } else if (selectedIndex == 2) {
                for (Movimentacao produto : lista) {
                    if (produto.getIdArmazem().getDescricao().toUpperCase().equalsIgnoreCase(armazem.toUpperCase())) {
                        movimentacaoPesquisa.add(produto);
                    }
                }
            }
            return movimentacaoPesquisa;
        }
        return null;
    }
    
}
