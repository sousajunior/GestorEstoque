package br.com.gestorestoque.util;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        int mes = d.getMonth()+1;
        return d.getDate()+"/"+mes+"/"+ano;
    }

}
