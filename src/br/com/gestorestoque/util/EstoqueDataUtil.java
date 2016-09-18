/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestorestoque.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author DG
 */
public class EstoqueDataUtil {
    
    public static String converteDataEmString(final Date data) {
    String formato = "yyyy-MM-dd";
    return new SimpleDateFormat(formato).format(data);
}
    
}
