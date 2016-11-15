package br.com.gestorestoque.geradorRelatorio;

import br.com.gestorestoque.DAO.DAORelatorios;
import br.com.gestorestoque.util.FRMUtil;
import br.com.gestorestoque.util.RelatorioUtil;
import br.com.gestorestoque.view.enumerado.Relatorio;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author DG
 */
public class GeradorRelatorioJasperAdapter implements GeradorRelatorioService {

    @Override
    public JFrame vizualizarRelatorio(Relatorio relatorio, String codigosConsulta) throws JRException, SQLException {

        JasperPrint jasperPrint = JasperFillManager.fillReport("src/br/com/gestorestoque/relatorio/jasper/" + 
                                                                RelatorioUtil.retornarNomeRelatorio(relatorio) + ".jasper",
                                                                new HashMap(),
                                                                new JRResultSetDataSource(new DAORelatorios().executarConsultaRelatorio(relatorio, codigosConsulta)));
        
        JasperViewer jrViewer = new JasperViewer(jasperPrint, true);
        

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        jrViewer.setSize(d);

        return jrViewer;
    }

}
