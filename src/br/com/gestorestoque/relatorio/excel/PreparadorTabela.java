
package br.com.gestorestoque.relatorio.excel;

import br.com.gestorestoque.view.enumerado.Relatorio;
import java.sql.SQLException;
import javax.swing.JTable;

/**
 *
 * @author DG
 */
public interface PreparadorTabela {
    public JTable prepararTabela(Relatorio relatorio, String codigosConsulta)throws SQLException ;
}
