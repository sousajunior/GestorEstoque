/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestorestoque.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author DG
 */
public class EstoqueDataUtil {

    public static String converterDataEmString(final Date data) {
        String formato = "yyyy-MM-dd";
        return new SimpleDateFormat(formato).format(data);
    }

    public static String pegarDataAtual() {
        Date d = new Date();
        
        int ano = d.getYear()+1900;
        
        return d.getDate()+"/"+d.getMonth()+"/"+ano;
    }

}
