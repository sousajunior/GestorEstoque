
package br.com.gestorestoque.controller.filtros;

import java.util.List;


/**
 *
 * @author DG
 */
public interface Filtro<T> {

    
  List<T>  filtrar(List<T> lista);  
}
