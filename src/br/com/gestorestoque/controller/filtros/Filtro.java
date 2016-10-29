/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestorestoque.controller.filtros;

import java.util.List;


/**
 *
 * @author DG
 */
public interface Filtro<T> {

    
  List<T>  filtrar(List<T> lista);  
}
