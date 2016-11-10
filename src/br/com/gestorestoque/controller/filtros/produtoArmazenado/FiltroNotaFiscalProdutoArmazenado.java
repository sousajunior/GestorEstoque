
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
public class FiltroNotaFiscalProdutoArmazenado implements Filtro<ProdutoArmazenado>{

    private int selectedIndex;
    private String notaFiscal;

    public FiltroNotaFiscalProdutoArmazenado(int selectedIndex, String notaFiscal) {
        this.selectedIndex = selectedIndex;
        this.notaFiscal = notaFiscal;
    }
   
    
    
    @Override
    public List<ProdutoArmazenado> filtrar(List<ProdutoArmazenado> lista) {
        List<ProdutoArmazenado> produtosArmazenadosPesquisa = new ArrayList<>();
        if (FiltroUtil.validarIndex(selectedIndex)) {
            if (selectedIndex == 1) {
                for (ProdutoArmazenado produto : lista) {
                    String nf = "" + produto.getNotaFiscal();
                    if (nf.contains(notaFiscal)) {
                        produtosArmazenadosPesquisa.add(produto);
                    }
                }
            } else if (selectedIndex == 2) {
                for (ProdutoArmazenado produto : lista) {
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
    
}   
