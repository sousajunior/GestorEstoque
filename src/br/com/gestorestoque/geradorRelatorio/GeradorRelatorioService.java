package br.com.gestorestoque.geradorRelatorio;

import br.com.gestorestoque.view.enumerado.Relatorio;
import java.sql.SQLException;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author DG
 */
public interface GeradorRelatorioService {

    JFrame vizualizarRelatorio(Relatorio relatorio,String codigosConsulta) throws JRException,SQLException;
    
}
