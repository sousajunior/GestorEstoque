package br.com.gestorestoque.geradorRelatorio;

import java.sql.SQLException;
import javax.swing.JPanel;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author DG
 */
public interface GeradorRelatorioService {

    JPanel vizualizarRelatorio(String nomeRelatorio,String codigosConsulta) throws JRException,SQLException;
    
}
