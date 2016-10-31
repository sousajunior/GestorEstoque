
package br.com.gestorestoque.controller.filtros.produtoArmazenado;

import br.com.gestorestoque.controller.filtros.Filtro;
import br.com.gestorestoque.model.ProdutoArmazenado;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DG
 */
public class FiltroCompositeProdutoArmazenado implements Filtro<ProdutoArmazenado>{

    List<ProdutoArmazenado> produtos = new ArrayList<>();
    List<Filtro> filtros = new ArrayList<>();

    public FiltroCompositeProdutoArmazenado(List<Filtro> filtros) {
        this.filtros = filtros;
    }

    public FiltroCompositeProdutoArmazenado() {
        
    }
    
    
    
    
    @Override
    public List<ProdutoArmazenado> filtrar(List<ProdutoArmazenado> listaProdutosArmazenados) {
        
        List<ProdutoArmazenado> lista = listaProdutosArmazenados;
        List<ProdutoArmazenado> lista2 = new ArrayList<>();        
        
        for (Filtro filtro : filtros) {
            
            lista2 = filtro.filtrar(lista);
            lista = lista2;
            
        }
    
        return lista;
    }
    
    /**
     * Adiciona um filtro a lista de filtros da classe FiltroCompositeProdutoArmazenado.
     * @param filtro (Um filtro que implemente a interface filtro).
     */
    public void add(Filtro filtro){
        filtros.add(filtro);
    }
}
