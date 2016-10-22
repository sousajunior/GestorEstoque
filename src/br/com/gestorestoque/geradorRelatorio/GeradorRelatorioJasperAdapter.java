/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestorestoque.geradorRelatorio;

import br.com.gestorestoque.DAO.DAORelatorios;
import br.com.gestorestoque.util.RelatorioUtil;
import br.com.gestorestoque.view.enumerado.Relatorio;
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
        

        JasperPrint jasperPrint = JasperFillManager.fillReport("src/br/com/gestorestoque/relatorio/jasper/" + RelatorioUtil.retornarNomeRelatorio(relatorio) + ".jasper",
                                                               new HashMap(),
                                                               new JRResultSetDataSource(new DAORelatorios().executarConsultaRelatorio(relatorio, codigosConsulta)));
                                                               JasperViewer jrViewer = new JasperViewer(jasperPrint, true);
        jrViewer.setSize(1200, 700);

        return jrViewer;
    }

}
